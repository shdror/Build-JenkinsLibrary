import tools.mdsd.devops.ConfigurationManager

def call (int id) {
    ConfigurationManager.instance.removeConfiguration(id)
}