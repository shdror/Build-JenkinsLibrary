def emptyDir = "/tmp/${env.BUILD_TAG}"
try {
    MPLModuleWithConfigExt('Setup Container') {
        emptySlaveDir = emptyDir
    }
    // run maven build in docker container
    docker.image(CFG.dockerBuildImage).withRun(CFG.dockerWithRunParameters) {c -> 
        MPLModuleWithConfigExt('Build In Container') {
            containerId = c.id
        }
    }
finally {
    MPLModuleWithConfigExt('Tear Down Container') {
        emptySlaveDir = emptyDir
    }
}