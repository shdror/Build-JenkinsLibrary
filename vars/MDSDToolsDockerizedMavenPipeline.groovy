def call(body) {
    MPLInit()

    MPLModulesPath('tools/mdsd/devops/pipeline/stages/impl/dockerized')
    MPLModulesPath('tools/mdsd/devops/pipeline/stages/impl/dockerized/maven')
    MPLModulesPath('tools/mdsd/devops/pipeline/stages/impl/constraintBuildTime')
    MPLModulesPath('tools/mdsd/devops/pipeline/stages/impl/dockerized/initialize')

    AbstractMDSDToolsPipeline(body, [
        mavenContainerVersion: 'maven:3-jdk-11'
        buildLimitTime: 30
        buildLimitRAM: '4G'
        buildLimitHDD: '20G'
    ])
}