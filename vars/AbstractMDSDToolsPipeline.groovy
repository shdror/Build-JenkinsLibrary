/**
 * Abstract MDSDTools Pipeline
 * Defines pipeline stages of MDSD.tools Build process
 * Relies on concrete Pipeline to provide agent configuration
 */
def call(body, defaults  = [:], overrides = [:]) {
  extendConfiguration([
    skipDeploy: "$BRANCH_NAME" != 'master',
    skipNotification: "$BRANCH_NAME" != 'master',
    deploySubDir: "$BRANCH_NAME" == 'master' ? 'nightly': "branches/$BRANCH_NAME",
    deployProjectDir: scm.userRemoteConfigs[0].url.replaceFirst(/^.*\/([^\/]+?).git$/, '$1').toLowerCase()
  ] + defaults, [    
    deployRelease: params.Release,
    deployReleaseVersion: params.ReleaseVersion
  ] + overrides, body) 

  def config = updateConfiguration()
  
  def modules = ['Prepare', 'Checkout', 'Build', 'Archive', 'Quality Metrics', 'Deploy', 'Cleanup']
  def moduleConfig = [:]

  modules.each {
    if (!config["skip${it.replaceAll("\\s","")}"]) {
      moduleConfig.put(it, [:])
    } else {
      echo "Stage $it will be skipped."
    }
  }
  
  extendConfiguration([
    modules: moduleConfig,
    notificationDefaultRecipient: decodeEmailAddress(config.notificationDefaultRecipient)
  ])

  config = updateConfiguration()

  pipeline {
    agent {
      label config.agent_label
    }

    parameters {
      booleanParam(defaultValue: false, name: 'Release', description: 'Set true for release build')
      string(defaultValue: 'nightly', name: 'ReleaseVersion', description: 'Set version to be used for the release')
    }

    options {
      skipDefaultCheckout true
      buildDiscarder(logRotator(artifactDaysToKeepStr: '1', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '100'))
    }

    stages { 
      stage( 'Prepare' ) {
        steps {
          extendConfiguration([
            workspacePath: pwd(),
            isMasterBranch: "$BRANCH_NAME" == 'master',
            isPullRequest: !(env.CHANGE_TARGET == null),
            relativeArtifactsDir: config.relativeArtifactsDir ? config.relativeArtifactsDir : config.updateSiteLocation ?
              config.updateSiteLocation : error('No artifacts directory specified')
          ])
          MPLModule()
        }
      }
      stage( 'Checkout' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
      stage( 'Build' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
      stage( 'Archive' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
      stage( 'Quality Metrics' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
      stage( 'Deploy' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
    }
    post {
      always {
        emailNotification(config.notificationDefaultRecipient, config.skipNotification)
      }
      cleanup {
        script {
          if (MPLModuleEnabled('Cleanup')) {
            MPLModule ('Cleanup')
          }
        }
      }
    }
  }
}  

def decodeEmailAddress(configParameter) {
	return configParameter.contains('@') ? configParameter : new String(configParameter.decodeBase64())
}
