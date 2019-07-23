import tools.mdsd.devops.ConfigurationManager
import com.griddynamics.devops.mpl.MPLException

def call (Map configurations = [:], Map overrides = [:], body = null) {
    if (body) {
        if (body in Closure) {
            body.resolveStrategy = Closure.DELEGATE_FIRST
            body.delegate = configurations
            body()
        } else {
            throw new MPLException("Unsupported MPL pipeline configuration type provided: ${body}")
        }
    }
    ConfigurationManager.instance.appendConfiguration(configurations, overrides)
}