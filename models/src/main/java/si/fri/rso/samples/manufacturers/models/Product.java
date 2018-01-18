package si.fri.rso.samples.manufacturers.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

public class Product{

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String title;

    @Column(name = "manufacturer_id")
    private String manufacturerId;

    @Column(name = "itemspecific_id")
    private String itemSpecificId;

    @Column(name = "category_id")
    private String categoryId;

    private String price;

    @Column(name = "returnpolicy_id")
    private String returnPolicyId;

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

    public String getReturnPolicyId() {
        return returnPolicyId;
    }

    public void setReturnPolicyId(String returnPolicyId) {
        this.returnPolicyId = returnPolicyId;
    }

    public String getItemSpecificId() {
        return itemSpecificId;
    }

    public void setItemSpecificId(String itemSpecificId) {
        this.itemSpecificId = itemSpecificId;
    }
}