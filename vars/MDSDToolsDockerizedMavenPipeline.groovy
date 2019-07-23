def call(body) {
    MPLInit()

    MPLModulesPath('tools/mdsd/devops/pipeline/stages/impl/dockerized')
    MPLModulesPath('tools/mdsd/devops/pipeline/stages/impl/dockerized/maven')
    MPLModulesPath('tools/mdsd/devops/pipeline/stages/impl/constraintBuildTime')
    MPLModulesPath('tools/mdsd/devops/pipeline/stages/impl/dockerized/initialize')

    AbstractMDSDToolsPipeline(body, [
        agent_label: 'docker',
        mavenContainerVersion: '3-jdk-11',
        buildLimitTime: 30,
        buildLimitRAM: '4G',
        buildLimitHDD: '20G',
        deployRootDir: '/home/deploy/html',
        deploySSHName: 'web',
        notificationDefaultRecipient: new String('bWRzZC10b29scy1idWlsZEBpcmEudWthLmRl'.decodeBase64())
    ])
}