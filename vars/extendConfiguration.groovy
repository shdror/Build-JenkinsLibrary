import tools.mdsd.devops.ConfigurationManager
import com.griddynamics.devops.mpl.MPLException

def call (Map configurations = [:], Map overrides = [:]) {
    ConfigurationManager.instance.appendConfiguration(configurations, overrides)
}