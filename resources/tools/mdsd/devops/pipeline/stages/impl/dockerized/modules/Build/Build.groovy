MPLModule('Setup Container')

CFG = getCurrentConfiguration()

if (!CFG.dockerBuildImage) {
    error('Configuration error. Docker Image is expected to be set by specializing Module implementation.')
}

docker.image(CFG.dockerBuildImage).withRun(CFG.dockerWithRunParameters) {c -> 
    extendConfiguration([containerId: c.id])
    MPLModule('Build In Container')
}