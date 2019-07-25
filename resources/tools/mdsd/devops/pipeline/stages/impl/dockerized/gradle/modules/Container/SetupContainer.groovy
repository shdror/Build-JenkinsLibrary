def gradleVersion = CFG.gradleVersion ?: '5.5.1'
def gradleJdkVersion = CFG.gradleJdkVersion ?: '11'

extendConfiguration([
    dockerBuildImage: "gradle:${CFG.gradleVersion}-jdk${CFG.mavenJdkVersion}",
    dockerWithRunParameters: """\
    ${CFG.dockerWithRunParameters ?: ""} \
    -v ${CFG.slaveHome}/.gradle:/.gradle:ro
    """])

MPLModule('Setup Container')