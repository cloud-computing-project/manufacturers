package si.fri.rso.samples.manufacturers.services.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("rest-properties")
public class RestProperties {

    @ConfigValue(value = "external-services.product-service.enabled", watch = true)
    private boolean productServiceEnabled;

    public boolean isProductServiceEnabled() {
        return productServiceEnabled;
    }

    public void setProductServiceEnabled(boolean productServiceEnabled) {
        this.productServiceEnabled = productServiceEnabled;
    }
}
