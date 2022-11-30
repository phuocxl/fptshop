package fa.training.dto;

public class ProductDTO {
    private long id;
    private String productName;
    private String desc;
    private long price;
    private String color;
    private String categoryName;

    public ProductDTO() {
    }

    public ProductDTO(long id, String productName, String desc, long price, String color, String categoryName) {
        this.id = id;
        this.productName = productName;
        this.desc = desc;
        this.price = price;
        this.color = color;
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
