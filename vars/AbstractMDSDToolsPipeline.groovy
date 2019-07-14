/**
 * Abstract MDSDTools Pipeline
 * Defines pipeline stages of MDSD.tools Build process
 * Relies on concrete Pipeline to provide agent configuration
 */
def call(body, defaults  = [:], overrides = [:]) {
  def MPL = MPLPipelineConfig(body, [
    modules: [
      Prepare: [:],
      Checkout: [:],
      Build: [:],
      Archive: [:],
      "Quality Metrics": [:],
      Deploy: [:],
      Cleanup: [:]
    ] + defaults,
    overrides
  ])

  pipeline {
			properties([
				// define parameters for parameterized builds
				parameters([
					booleanParam(defaultValue: false, name: 'Release', description: 'Set true for release build'),
					string(defaultValue: 'nightly', name: 'ReleaseVersion', description: 'Set version to be used for the release')
				]),
				// define log rotation
				buildDiscarder(logRotator(artifactDaysToKeepStr: '1', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '100')),
			])

    MPLModule('Pipeline')
  }
}
