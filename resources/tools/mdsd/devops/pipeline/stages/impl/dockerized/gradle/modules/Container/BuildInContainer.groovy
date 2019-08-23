lock("gradle-cache-${CFG.slaveName}") {
    sh "docker exec ${CFG.containerId} cp -r /.gradle /tmp"
}
sh "docker exec ${CFG.containerId} cp -r /ws /tmp"
sh "docker exec ${CFG.containerId} gradle -g /tmp/.gradle -p /tmp/ws"
sh "docker cp ${CFG.containerId}:/tmp/ws/. ${CFG.workspacePath}"
if (!CFG.isPullRequest) {
    lock("gradle-cache-${CFG.slaveName}") {
        sh "docker cp ${CFG.containerId}:/tmp/.gradle/. ${CFG.slaveHome}/.gradle"
    }
}