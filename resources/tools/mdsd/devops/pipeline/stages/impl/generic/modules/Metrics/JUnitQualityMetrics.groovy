// This module makes assumptions about folder names which are typical for Maven
// TODO: Generalize this by delegating to build tool specific implementation
// if necessary.
junit([
    testResults: '**/surefire-reports/*.xml',
    allowEmptyResults: true
])