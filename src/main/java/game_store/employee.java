package game_store;
import javax.persistence.*;

@Entity
public class employee
{
    private int employeeID;
    private String username, password;
    @Id
    public int getEmployeeID()
    {
        return employeeID;
    }
    public void setEmployeeID(int newID)
    {
        employeeID = newID;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String newName)
    {
        username = newName;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String newPassword)
    {
        password = newPassword;
    }

}

