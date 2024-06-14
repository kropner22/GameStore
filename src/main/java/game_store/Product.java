package game_store;

public class Product {
    public Integer product_id, stock;
    public String name, developer;
    public float price, sale_price;
    public boolean sale;

    public int getProduct_id(){
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }
    public int getStock(){ return stock; }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getName(){ return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper(){ return developer; }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public float getPrice(){
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getSale_price(){
        return sale_price;
    }

    public void setSale_price(float sale_price) {
        this.sale_price = sale_price;
    }

    public boolean getSale(){
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public boolean isSale() {
        return sale;
    }
}
