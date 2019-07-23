sshPublisher(
    alwaysPublishFromMaster: true,
    failOnError: true,
    publishers: [
        sshPublisherDesc(
            configName: CFG.deploySSHName,
            transfers: [
                sshTransfer(
                    sourceFiles: "${CFG.relativeArtifactsDir}/**/*",
                    cleanRemote: true,
                    removePrefix: "${CFG.relativeArtifactsDir}",
                    remoteDirectory: "${CFG.deployProjectDir}/${CFG.deploySubDir}"
                )
            ]
        )
    ]
)