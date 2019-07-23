jacoco([
    execPattern: '**/target/*.exec',
    classPattern: '**/target/classes',
    sourcePattern: '**/src,**/src-gen,**/xtend-gen',
    inclusionPattern: '**/*.class',
    exclusionPattern: '**/*Test*.class'
])