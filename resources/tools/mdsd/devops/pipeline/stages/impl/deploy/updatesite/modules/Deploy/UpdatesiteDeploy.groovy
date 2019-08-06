if (CFG.deployUpdatesiteSshName) {
    if (!CFG.deployUpdatesiteArtifactsDir) {
        error 'No updatesite location specified. Hence, no updatesite could be deployed.'
        //TODO lookup updatesite in project
    }
    MPLModule("SSH Updatesite Deploy")
}