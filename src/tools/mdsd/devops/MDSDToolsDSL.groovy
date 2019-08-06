package tools.mdsd.devops

class MDSDToolsDSL implements Serializable {
    Map configs = [:]
    Set deploymentModules = []
    String buildTool = ''
    boolean usingDocker = true
    boolean constraintBuild = false
    int deploymentCounter = 0

    def DEPLOY_PREFIX = 'deploy'
    def BUILD_WITH_PREFIX = 'buildWith'
    def CONSTRAINT_BUILD_PREFIX = 'constraintBuild'

    def deployUpdatesite(String folderName) {
        this.deployUpdatesite {
            artifactsDir = folderName
        }
    }

    def methodMissing(String name, args) {
        def config = [:]
        if (args.length == 1) {
            if (args[0] instanceof Closure) {
                args[0].delegate = config
                args[0].resolveStrategy = Closure.DELEGATE_FIRST
                args[0]()   
            
                if (name.startsWith(DEPLOY_PREFIX)) {
                    def deploymentName = "deploy${deploymentCounter++}"
                    configs << ["${deploymentName}Module": name[DEPLOY_PREFIX.length()..-1].toLowerCase()]
                    configs += config.collectEntries { ["${deploymentName}${it.key.capitalize()}", it.value]}
                    if (config.artifactsDir) {
                        if (!configs.archiveArtifactsDir) {
                            configs.archiveArtifactsDir = config.artifactsDir
                        }
                        if (!configs.javadocArtifactsDir) {
                            configs.javadocArtifactsDir = "${config.artifactsDir}/javadoc"
                        }
                    }
                    deploymentModules << name[DEPLOY_PREFIX.length()..-1].toLowerCase()
                } else if (name.startsWith(BUILD_WITH_PREFIX)) {
                    buildTool = name[BUILD_WITH_PREFIX.length()..-1].toLowerCase()
                    configs += config.collectEntries { ["${buildTool}${it.key.capitalize()}", it.value]}
                } else if (name == CONSTRAINT_BUILD_PREFIX) {
                    constraintBuild = true
                    configs += config.collectEntries { ["constraintBuild${it.key.capitalize()}", it.value]}
                }

            } else {
                configs[name] = args[0]
            }
        } else if (args.length == 0) {
               configs[name] = true
        } else {
            throw new MissingMethodException(name, this.class, args)
        }
    }

    def propertyMissing(String name) {
        configs[name]
    }

    def propertyMissing(String name, value) {
        configs[name] = value
    }  
}
