if (CFG.deploySSHName) {
    MPLModule("SSH Deploy")

    if (CFG.deployRelease) {
        if (!CFG.deployReleaseVersion) {
            error 'Release deployment requires a properly set up release version'
        } else {
            MPLModule("SSH Release Deploy")
        }
    }
}