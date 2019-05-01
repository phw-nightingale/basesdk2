package xyz.frt.basesdk2.entity;

public class OrderDetail extends BaseEntity {

    private Integer orderId;

    private Integer productId;

    private Product product;

    private Integer count;

    private Integer sumMoney;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSumMoney() {
        if (product != null) {
            return product.getPrice1() * count;
        } else {
            return sumMoney;
        }
    }

    public void setSumMoney(Integer sumMoney) {
        this.sumMoney = sumMoney;
    }

}