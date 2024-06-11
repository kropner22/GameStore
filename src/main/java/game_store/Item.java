package game_store;

public class Item {
    protected Integer Item_ID, Age;
    protected float Price;


    public int get_id(){
        return Item_ID;
    }
    public int get_age(){
        return Age;
    }
    public float get_price(){
        return Price;
    }
}
