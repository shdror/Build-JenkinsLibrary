import com.griddynamics.devops.mpl.Helper
import com.griddynamics.devops.mpl.MPLException

/**
 * Abstract MDSDTools Pipeline
 * Defines pipeline stages of MDSD.tools Build process
 * Relies on concrete Pipeline to provide agent configuration
 */
def call(body, defaults  = [:], overrides = [:]) {
  def config = [
    modules: [
      Prepare: [:],
      Checkout: [:],
      Build: [:],
      Archive: [:],
      "Quality Metrics": [:],
      Deploy: [:],
      Cleanup: [:]
    ]
  ] + defaults

  if( body in Closure ) {
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
  } else if( body in Map ) {
    Helper.mergeMaps(config, body)
  } else
    throw new MPLException("Unsupported MPL pipeline configuration type provided: ${body}")

  def cfgId = extendConfiguration(config, overrides) 

  pipeline {
    agent {
      label 'docker'
    }

    parameters {
      booleanParam(defaultValue: false, name: 'Release', description: 'Set true for release build')
      string(defaultValue: 'nightly', name: 'ReleaseVersion', description: 'Set version to be used for the release')
    }

    options {
      // define log rotation
      skipDefaultCheckout true
      buildDiscarder(logRotator(artifactDaysToKeepStr: '1', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '100'))
    }

    stages { 
      stage( 'Prepare' ) {
        steps {
          extendConfiguration([workspacePath: pwd(),
            isMasterBranch: "$BRANCH_NAME" == 'master',
            isPullRequest: !(env.CHANGE_TARGET == null),
            relativeArtifactsDir: config.updateSiteLocation,
            deployDirectory: config.webserverDir
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
      stage( 'Cleanup' ) {
        when { expression { MPLModuleEnabled() } }
        steps {
          MPLModule()
        }
      }
    }
  }
}  
