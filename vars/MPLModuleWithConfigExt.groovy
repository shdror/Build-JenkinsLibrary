def call(String name = env.STAGE_NAME, body) {
    def cfg = MPLManager.instance.moduleConfig(name)

    MPLModule(name, EnhancedConfig(cfg, body))
}