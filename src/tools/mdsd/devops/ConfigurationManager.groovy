package tools.mdsd.devops

import com.griddynamics.devops.mpl.Helper
import com.griddynamics.devops.mpl.MPLManager


@Singleton
class ConfigurationManager implements Serializable {
    private Map configurationExtensions = [:]
    private Map configurationOverrides = [:]
    private int counter = 0

    public int appendConfiguration (Map configuration = [:], Map overrides = [:]) {
        def id = ++counter
        configurationExtensions[id] = configuration
        configurationOverrides[id] = overrides
        updateConfiguration()
        id
    }

    public void removeConfiguration (int id) {
        configurationExtensions.remove(id)
        configurationOverrides.remove(id)
        updateConfiguration()
    }

    protected void updateConfiguration() {
        def config = [:]
        configurationExtensions.each{key, val ->
            if (val)
                Helper.mergeMaps(config, val)
        }

        configurationOverrides.reverseEach {key, val ->
            if (val)
                Helper.mergeMaps(config, val)
        }
        MPLManager.instance.init(config)
    }
}