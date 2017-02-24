import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.Statement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/* Each table you wish to access in your database requires a model class, like this example: */
public class Message
{
    /* First, map each of the fields (columns) in your table to some public variables. */

    private final SimpleStringProperty Subject;
    private final SimpleStringProperty From;
    private final SimpleStringProperty Message;
    private final SimpleStringProperty Date;
    private final SimpleIntegerProperty MessageID;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Message(Integer MessageID, String From, String Subject, String Message, String Date)
    {
        this.MessageID = new SimpleIntegerProperty(MessageID);
        this.Subject = new SimpleStringProperty(From);
        this.From = new SimpleStringProperty(Subject);
        this.Message = new SimpleStringProperty(Message);
        this.Date = new SimpleStringProperty(Date);

    }

    public String getSubject() {
        return Subject.get();
    }

    public String getFrom() {
        return From.get();
    }

    public String getMessage() {
        return Message.get();
    }

    public String getDate()  {
        return Date.get();
    }

    public Integer getMessageID() {
        return MessageID.get();
    }

    public static void deleteById(int MessageID)
    {
        try 
        {

            PreparedStatement statement = Application.database.newStatement("DELETE FROM Message WHERE MessageID = ?");             
            statement.setInt(1, MessageID);

            if (statement != null)
            {
                Application.database.executeUpdate(statement);
                System.out.println("Message deleted");
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

    public static void sendMessage(String to, String subject, String message) throws SQLException
    {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        PreparedStatement statement = Application.database.newStatement("INSERT INTO Message (MessageID, RecieverID, Subject, CreaterID, MessageBody, CreateDate) VALUES (NULL, ?, ?, ?, ?, ?)");

        try
        {
            statement.setInt(1, Users.getSendToID(to));
            statement.setString(2, subject);
            statement.setInt(3, Users.getActiveUserID());
            statement.setString(4, message);
            statement.setDate(5, sqlDate);

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

}
