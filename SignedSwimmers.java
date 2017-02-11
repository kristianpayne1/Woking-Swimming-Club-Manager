import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.Statement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/* Each table you wish to access in your database requires a model class, like this example: */
public class SignedSwimmers
{
    /* First, map each of the fields (columns) in your table to some public variables. */

    private final SimpleStringProperty Firstname;
    private final SimpleStringProperty Lastname;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public SignedSwimmers(String Firstname, String Lastname)
    {
        this.Firstname = new SimpleStringProperty(Firstname);
        this.Lastname = new SimpleStringProperty(Lastname);
    }

    public String getFirstname() {
        return Firstname.getValue();
    }
    
     public String getLastname() {
        return Lastname.getValue();
    }


    /*public static void declineById(int OpenMeetID)
    {
    try 
    {

    PreparedStatement statement = Application.database.newStatement("DELETE FROM Galas WHERE GalaID = ?");             
    statement.setInt(1, GalaID);

    if (statement != null)
    {
    Application.database.executeUpdate(statement);
    System.out.println("Gala declined deleted");
    }
    }
    catch (SQLException resultsexception)
    {
    System.out.println("Database result processing error: " + resultsexception.getMessage());
    }

    }

    /* A toString method is vital so that your model items can be sensibly displayed as text. 
    @Override public String toString()
    {
    return name;
    }

    /* Different models will require different read and write methods. Here is an example 'loadAll' method 
     * which is passed the target list object to populate. */

    /*public static void signUp(String to, String subject, String message) throws SQLException
    {
    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    PreparedStatement statement = Application.database.newStatement("INSERT INTO Message (MessageID, RecieverID, Subject, CreaterID, MessageBody, CreateDate, ParentID, Read) VALUES (NULL, ?, ?, ?, ?, ?, 1, 0)");

    try
    {
    statement.setInt(1, Users.getSendToID(to));
    statement.setString(2, subject);
    statement.setInt(3, Users.getActiveUserID());
    statement.setString(4, message);
    statement.setDate(5, sqlDate);
    //statement.setInt(5, ParentID);

    }
    catch(SQLException e)
    {
    System.out.println(e);
    System.out.println("Failed to insert to Message table");
    }
    finally
    {   
    statement.executeUpdate();
    statement.close();
    }
    }
     */

}
