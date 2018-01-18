package si.fri.rso.samples.manufacturers.models;

public class Shipping {

    private String id;

    private String productId;

    private String shippingTo;

    private String service;

    private String delivery;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getShippingTo() {
        return shippingTo;
    }

    public void setShippingTo(String shippingTo) {
        this.shippingTo = shippingTo;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
