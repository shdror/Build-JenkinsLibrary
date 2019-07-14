def call() {
    library('mpl@release')

    MPLEnforce([])

    MPLModulesPath('tools/mdsd/devops/pipeline/stages')
    MPLModulesPath('tools/mdsd/devops/pipeline/impl/generic')
}