def gradleVersion = CFG.gradleVersion ?: '5.5.1'
def gradleJdkVersion = CFG.gradleJdkVersion ?: '11'

extendConfiguration([
    dockerBuildImage: "gradle:${gradleVersion}-jdk${gradleJdkVersion}",
    dockerWithRunParameters: """\
    ${CFG.dockerWithRunParameters ?: ""} \
    -v ${CFG.slaveHome}/.gradle:/.gradle:ro
    """])

MPLModule('Setup Container')