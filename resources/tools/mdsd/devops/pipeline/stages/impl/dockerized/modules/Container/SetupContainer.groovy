extendConfiguration([dockerWithRunParameters: """\
        ${CFG.dockerWithRunParameters ?: ""} \
        -u ${CFG.slaveUid} \
        -v ${CFG.workspacePath}:/ws:ro \
        -e "RELEASE=${CFG.deployRelease}" \
        --network proxy \
        -it \
        --entrypoint=/bin/cat \
        """])
        
        
