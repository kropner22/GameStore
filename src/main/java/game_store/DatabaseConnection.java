package game_store;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
public class DatabaseConnection
{
    static Session databaseSession = null;
    static SessionFactory sessionFactory = null;

    //opens database
    private static void openDBSession()
    {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        databaseSession = sessionFactory.openSession();
    }

    //closes database
    private static void closeDBSession()
    {
        sessionFactory.close();
        databaseSession.close();
        sessionFactory = null;
        databaseSession = null;
    }

    //retrieves games table
    public static List<?> getGames()
    {
        openDBSession();
        Query query = databaseSession.createQuery("from Game");
        List<?> list = query.list();
        closeDBSession();
        return list;
    }

    //retrieves employee with a given name and password
    public static List<?> getEmployee(String username, String password)
    {
        openDBSession();
        Query query = databaseSession.createQuery("from Employee where username = '"+ username + "' and password = '" + password + "'");
        List<?> list = query.list();
        closeDBSession();
        return list;
    }

    //returns lowest stock game
    public static List<?> getLowestStockGame()
    {
        openDBSession();
        Query query = databaseSession.createQuery("select name,stock from game_store.Game order by stock");
        query.setMaxResults(1);
        List<?> list = query.list();
        closeDBSession();
        return list;
    }

    //returns lowest stock console
    public static List<?> getLowestStockConsole()
    {
        openDBSession();
        Query query = databaseSession.createQuery("select name,stock from game_store.Console order by stock");
        query.setMaxResults(1);
        List<?> list = query.list();
        closeDBSession();
        return list;
    }
}