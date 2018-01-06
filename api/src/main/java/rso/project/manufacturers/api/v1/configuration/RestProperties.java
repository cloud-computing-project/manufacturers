package rso.project.manufacturers.api.v1.configuration;
import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import javax.enterprise.context.ApplicationScoped;

/**
 * Created by Amra on 4.1.2018..
 */
@ConfigBundle("rest-properties")
@ApplicationScoped
public class RestProperties {
    private boolean healthy;

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }
}
