package game_store;
import javax.persistence.*;

@Entity
public class Employee
{
    private int employee_id;
    private String username, password;
    @Id
    public int getEmployee_id()
    {
        return employee_id;
    }
    public void setEmployee_id(int newID)
    {
        employee_id = newID;
    }
    public String getusername()
    {
        return username;
    }
    public void setUsername(String newName)
    {
        username = newName;
    }
    public String getPassword() { return password; }
    public void setPassword(String newPassword)
    {
        password = newPassword;
    }

}

