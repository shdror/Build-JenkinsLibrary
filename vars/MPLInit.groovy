def call() {
    library('mpl@master')

    MPLEnforce([])

    MPLModulesPath('tools/mdsd/devops/pipeline/stages')
    MPLModulesPath('tools/mdsd/devops/pipeline/stages/impl/generic')
}