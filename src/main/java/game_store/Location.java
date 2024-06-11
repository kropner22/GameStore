package game_store;
import java.util.*;

public class Location {
    private String name;
    private ArrayList<Item> items;

    public String get_location_name(){
        return(name);
    }
    public ArrayList get_items(){
        return(items) ;
    }
}
