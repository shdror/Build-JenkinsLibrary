publishHTML([
    allowMissing: false,
    alwaysLinkToLastBuild: false,
    keepAll: false,
    reportDir: "${CFG.relativeArtifactsDir}/javadoc",
    reportFiles: 'overview-summary.html',
    reportName: 'JavaDoc',
    reportTitles: ''
])