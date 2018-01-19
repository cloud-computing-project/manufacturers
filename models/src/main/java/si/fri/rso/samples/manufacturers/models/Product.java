package si.fri.rso.samples.manufacturers.models;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name = "products")
@NamedQueries(value =
{
    @NamedQuery(name = "Product.getAll", query = "SELECT p FROM products p")
})
@UuidGenerator(name = "idGenerator")
public class Product {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String title;

    @Column(name = "manufacturer_id")
    private String manufacturerId;

    private String price;

    @Transient
    private List<Sale> sales;

    @Transient
    private List<Shipping> shippings;

    @Transient
    private List<Order> orders;

    @Column(name = "returnpolicy_id")
    private String returnPolicyId;

    @Column(name = "itemspecific_id")
    private String itemSpecificId;

    @Column(name = "category_id")
    private String categoryId;

    @Transient
    private List<Rating> ratings;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public String getReturnPolicyId() {
        return returnPolicyId;
    }

    public void setReturnPolicyId(String returnPolicyId) {
        this.returnPolicyId = returnPolicyId;
    }

    public List<Shipping> getShippings() {
        return shippings;
    }

    public void setShippings(List<Shipping> shippings) {
        this.shippings = shippings;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getItemSpecificId() {
        return itemSpecificId;
    }

    public void setItemSpecificId(String itemSpecificId) {
        this.itemSpecificId = itemSpecificId;
    }


    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
