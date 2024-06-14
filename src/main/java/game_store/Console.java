package game_store;

public class Console extends Product {

    private int console_id, storage_gb;
    private boolean hybrid;

    public int getConsole_id() {
        return console_id;
    }

    public void setConsole_id(int console_id) {
        this.console_id = console_id;
    }

    public int getStorage_gb() {
        return storage_gb;
    }

    public void setStorage_gb(int storage_gb) {
        this.storage_gb = storage_gb;
    }

    public boolean getHybrid() {return hybrid;}

    public boolean isHybrid() {
        return hybrid;
    }

    public void setHybrid(boolean hybrid) {
        this.hybrid = hybrid;
    }
}