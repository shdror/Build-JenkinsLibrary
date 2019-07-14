if (!CFG.dockerBuildImage) {
    error('Configuration error. Docker Image is expected to be set by specializing Module implementation.')
}
CFG.dockerWithRunParameters = """\
    ${CFG.dockerWithRunParameters ?: ""} \
    -u ${slaveUid} \
    -v ${workspace}:/ws:ro \
    -m $buildLimitRam \
    --storage-opt size=$buildLimitHdd \
    --network proxy \
    -it \
    --entrypoint=/bin/cat \
    """

sh "mkdir -p ${CFG.emptySlaveDir}"
        
        