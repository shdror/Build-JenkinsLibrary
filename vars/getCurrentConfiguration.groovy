import com.griddynamics.devops.mpl.MPLManager
import com.griddynamics.devops.mpl.Helper

def call(String name = env.STAGE_NAME) {
    Helper.flatten(MPLManager.instance.moduleConfig(name))
}