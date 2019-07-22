
//try {
    MPLModule('Setup Container')

    CFG = updateConfiguration()

    if (!CFG.dockerBuildImage) {
        error('Configuration error. Docker Image is expected to be set by specializing Module implementation.')
    }

    // run maven build in docker container
    docker.image(CFG.dockerBuildImage).withRun(CFG.dockerWithRunParameters) {c -> 
        extendConfiguration([containerId: c.id])
        MPLModule('Build In Container')
    }

//} finally {
    MPLModule('Tear Down Container')
//}