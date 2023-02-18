package fa.training.dto;

import java.util.Set;

public class AddProductDTO {
    private long id;
    private String productName;
    private String desc;
    private long price;
    private String color;
    private Set<Integer> idCategoryName;

    public AddProductDTO() {
    }

    public AddProductDTO(long id, String productName, String desc, long price, String color, Set<Integer> idCategoryName) {
        this.id = id;
        this.productName = productName;
        this.desc = desc;
        this.price = price;
        this.color = color;
        this.idCategoryName = idCategoryName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<Integer> getIdCategoryName() {
        return idCategoryName;
    }

    public void setIdCategoryName(Set<Integer> idCategoryName) {
        this.idCategoryName = idCategoryName;
    }
}
