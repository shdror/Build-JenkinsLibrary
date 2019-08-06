sshPublisher(
    alwaysPublishFromMaster: true,
    failOnError: true,
    publishers: [
        sshPublisherDesc(
            configName: CFG.deployUpdatesiteSshName,
            transfers: [
                sshTransfer(
                    sourceFiles: "${CFG.deployUpdatesiteArtifactsDir}/**/*",
                    cleanRemote: true,
                    removePrefix: "${CFG.deployUpdatesiteArtifactsDir}",
                    remoteDirectory: "${CFG.deployUpdatesiteProjectDir}/${CFG.deployUpdatesiteSubDir}"
                )
            ]
        )
    ]
)