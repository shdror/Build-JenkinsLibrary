extendConfiguration([
    dockerWithRunParameters: """\
        ${CFG.dockerWithRunParameters ?: ""} \
        -m ${CFG.buildLimitRAM} \
        --storage-opt size=${CFG.buildLimitHDD} \
        """])
MPLModule('Setup Container')