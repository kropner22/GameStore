package game_store;

public class Book extends Item {
    private String Title, Author, Genre, Size;
    private boolean is_childrens, Paperback;

    public String get_title(){
        return Title;
    }
    public String get_author(){
        return Author;
    }
    public String get_genre(){
        return Genre;
    }
    public String get_size(){
        return Size;
    }
    public boolean get_is_childrens(){
        return is_childrens;
    }
    public boolean get_paperback(){
        return Paperback;
    }
}
