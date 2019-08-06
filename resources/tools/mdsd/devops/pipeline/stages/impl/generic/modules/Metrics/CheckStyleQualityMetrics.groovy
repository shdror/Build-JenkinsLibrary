// This module makes assumptions about folder names which are typical for Maven
// TODO: Generalize this by delegating to build tool specific implementation
// if necessary.
recordIssues([
    tool: checkStyle([
        pattern: '**/target/checkstyle-result.xml'
    ])
])
