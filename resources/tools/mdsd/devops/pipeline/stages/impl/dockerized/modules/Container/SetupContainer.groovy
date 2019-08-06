extendConfiguration([dockerWithRunParameters: """\
        ${CFG.dockerWithRunParameters ?: ""} \
        -u ${CFG.slaveUid} \
        -v ${CFG.workspacePath}:/ws:ro \
        --network proxy \
        -it \
        --entrypoint=/bin/cat \
        """])
        
        