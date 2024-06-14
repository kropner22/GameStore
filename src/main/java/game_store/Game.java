package game_store;

public class Game extends Product{

    private int game_id, console_id;
    private String genre;

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getConsole_id(){
        return console_id;
    }
    public void setConsole_id(int console_id) {
        this.console_id = console_id;
    }
    public String getGenre() {return genre; }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
