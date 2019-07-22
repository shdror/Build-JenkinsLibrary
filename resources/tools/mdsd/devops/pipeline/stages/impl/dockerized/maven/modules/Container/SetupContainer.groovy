extendConfiguration([
    dockerBuildImage: "maven:${CFG.mavenContainerVersion}",
    dockerWithRunParameters: """\
    ${CFG.dockerWithRunParameters ?: ""} \
    -v ${CFG.slaveHome}/.m2:/.m2:ro \
    -v ${CFG.emptySlaveDir}:/root/.m2:ro \
    -v ${CFG.mavenSettingsFile}:/settings.xml:ro \
    -e MAVEN_CONFIG=/tmp/.m2 \
    -e MAVEN_OPTS=-Duser.home=/tmp \
    """])

sh "mkdir -p ${CFG.emptySlaveDir}"

MPLModule('Setup Container')