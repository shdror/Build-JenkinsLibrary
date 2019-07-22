import tools.mdsd.devops.ConfigurationManager

def call (Map configurations = [:], Map overrides = [:]) {
    ConfigurationManager.instance.appendConfiguration(configurations, overrides)
}