if (CFG.deployUpdatesiteSshName) {
    MPLModule("SSH Updatesite Release Deploy")
}

currentBuild.rawBuild.keepLog(true)