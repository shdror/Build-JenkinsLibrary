if (CFG.javadocArtifactsDir) {
    publishHTML([
        allowMissing: false,
        alwaysLinkToLastBuild: false,
        keepAll: false,
        reportDir: "${CFG.javadocArtifactsDir}",
        reportFiles: 'overview-summary.html',
        reportName: 'JavaDoc',
        reportTitles: ''
    ])
} else {
    echo "No Javadoc location specified. Hence, no Javadoc published."
}