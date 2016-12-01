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

    public static String stringDate(java.sql.Date CreateDate) 
    {
        String date = CreateDate.toString();
        return date;
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

    /*public static void sendMessageRec(String to, String subject) throws SQLException
    {
    PreparedStatement statement2 = Application.database.newStatement("INSERT INTO MessageRec (RecieverID, MessageID, Read) VALUES (?, ?, 0) SELECT MessageID FROM Message");

    try
    {
    statement2.setInt(1, Users.getSendToID(to));
    System.out.println("Message " + subject + " was sent successfully");
    }
    catch (SQLException e)
    {
    System.out.println(e);
    System.out.println("Failed to insert to MessageRec table");
    }
    finally
    {

    statement2.executeUpdate();
    statement2.close();

    }
    }
    /*
    public static String getLastMessageID() throws SQLException
    {
    String messageID = null;
    PreparedStatement statement3 = Application.database.newStatement("SELECT last_insert-id() AS last_id FROM Message"); 
    try{
    if (statement3 != null)
    {
    ResultSet results1 = Application.database.runQuery(statement3);
    if (results1 != null)
    {
    messageID = results1.getString("last_id");
    }
    }
    }
    catch (SQLException resultsexception)
    {
    System.out.println("Database result processing error: " + resultsexception.getMessage());
    }
    finally
    {
    statement3.close();
    }
    return messageID;
    }
     */
    /*public static Message getById(int MessageID)
    {
        String to = null;
        String subject = null;

        PreparedStatement statement = Application.database.newStatement("SELECT Subject FROM Message WHERE MessageID = ?"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            statement.setInt(1, MessageID);
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                              
                        subject = results.getString("Subject");
                        to = Users.getUsername(results.getInt("CreaterID"));

                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }
        return Message;
    }

    /*
    public static void deleteById(int id)
    {
    try 
    {

    PreparedStatement statement = Application.database.newStatement("DELETE FROM things WHERE id = ?");             
    statement.setInt(1, id);

    if (statement != null)
    {
    Application.database.executeUpdate(statement);
    }
    }
    catch (SQLException resultsexception)
    {
    System.out.println("Database result processing error: " + resultsexception.getMessage());
    }

    }
     */
    /*
    public void save()    
    {
    PreparedStatement statement;

    try 
    {

    if (id == 0)
    {

    statement = Application.database.newStatement("SELECT id FROM things ORDER BY id DESC");             

    if (statement != null)  
    {
    ResultSet results = Application.database.runQuery(statement);
    if (results != null)
    {
    id = results.getInt("id") + 1;
    }
    }

    statement = Application.database.newStatement("INSERT INTO things (id, name, categoryId) VALUES (?, ?, ?)");             
    statement.setInt(1, id);
    statement.setString(2, name);
    statement.setInt(3, categoryId);         

    }
    else
    {
    statement = Application.database.newStatement("UPDATE things SET name = ?, categoryId = ? WHERE id = ?");             
    statement.setString(1, name);
    statement.setInt(2, categoryId);   
    statement.setInt(3, id);
    }

    if (statement != null)
    {
    Application.database.executeUpdate(statement);
    }
    }
    catch (SQLException resultsexception)
    {
    System.out.println("Database result processing error: " + resultsexception.getMessage());
    }

    }
     */
}
