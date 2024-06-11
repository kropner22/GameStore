package game_store;

public class Game extends Item{
    private String name;
    private Integer number_players;

    public String get_name(){
        return name;
    }
    public int get_number_players(){
        return number_players;
    }
}
