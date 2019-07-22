configFileProvider(
    [configFile(fileId: 'fba2768e-c997-4043-b10b-b5ca461aff54', variable: 'MAVEN_SETTINGS')]) {
    extendConfiguration([
        mavenSettingsFile: MAVEN_SETTINGS,
        emptySlaveDir: "/tmp/${env.BUILD_TAG}"])
    MPLModule("Build")
}