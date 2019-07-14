node('docker') {
    MPLModuleWithConfigExt('Pipeline') {
        slaveHome = "${env.SLAVE_HOME}"
        slaveUid = "${env.SLAVE_USER_ID}"
        slaveName = "${env.NODE_NAME}"
    }
}