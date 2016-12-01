import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/* Each table you wish to access in your database requires a model class, like this example: */
public class Users
{
    /* First, map each of the fields (columns) in your table to some public variables. */
    public int UserID;
    public String UserName;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Users(int UserID, String UserName)
    {
        this.UserID = UserID;        
        this.UserName = UserName;
    }

    /* A toString method is vital so that your model items can be sensibly displayed as text. 
    @Override public String toString()
    {
    return name;
    }

    /* Different models will require different read and write methods. Here is an example 'loadAll' method 
     * which is passed the target list object to populate. */
    public static void readAll(List<Users> list)
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Application.database.newStatement("SELECT UserID, UserName FROM Users ORDER BY UserID"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        list.add( new Users(results.getInt("UserID"), results.getString("UserName")));
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

    public static Integer getSendToID(String to){
        Integer receiverID = null;
        PreparedStatement statement1 = Application.database.newStatement("SELECT UserID FROM Users WHERE UserName = ?"); 
        try{
            if (statement1 != null)
            {
                statement1.setString(1, to);
                ResultSet results = Application.database.runQuery(statement1);

                if (results != null)
                {
                    receiverID = new Integer (results.getInt("UserID"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
            System.out.println("UserID was probably not found");
        }
        return receiverID;
    }

    public static Integer getActiveUserID(){
        Integer userID = null;
        PreparedStatement statement2 = Application.database.newStatement("SELECT UserID FROM Users WHERE Active = ?"); 
        try{
            if (statement2 != null)
            {
                statement2.setInt(1, 1);
                ResultSet results = Application.database.runQuery(statement2);

                if (results != null)
                {
                    userID = new Integer (results.getInt("UserID"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
            System.out.println("No active users???");
        }
        return userID;
    }

    public static String getUsername(Integer CreaterID)
    {
        String username = null;
        PreparedStatement statement3 = Application.database.newStatement("SELECT UserName FROM Users WHERE UserID IN ('?')"); 
        try{
            if (statement3 != null)
            {
                statement3.setInt(1, CreaterID);
                ResultSet results = Application.database.runQuery(statement3);

                if (results != null)
                {
                    username = new String (results.getString("UserName"));
                }
            }
        }
        catch (SQLException resultsexception)
        {
            System.out.println("Database result processing error: " + resultsexception.getMessage());
        }
        return username;
    }

    public static boolean isLoginValid(String username, String password) throws SQLException
    {
        boolean loginGranted = false;
        username = username;
        System.out.println(username);
        String hash = null;
        int salt = getSalt(username);
        String passwordSalt = password + salt;
        String passwordHash = convertToHash(passwordSalt);

        PreparedStatement statement = Application.database.newStatement("SELECT Hash FROM Users WHERE UserName = ?");
        if (statement != null)
        {
            statement.setString(1, username);
            ResultSet results = Application.database.runQuery(statement);
            if (results != null)
            {
                try {
                    while (results.next() ){
                        hash = new String (results.getString("Hash"));
                    }
                }
                catch (SQLException resultsexception)
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        } 

        if (passwordHash == hash)
        {
            System.out.println("Login successfull");
            loginGranted = true;
            return loginGranted;
        }else{
            System.out.println("Login failed");
            return loginGranted;
        }
    }

    public static String convertToHash(String passwordSalt)
    {
        try {
            MessageDigest hasher = MessageDigest.getInstance("MD5");        
            hasher.update(passwordSalt.getBytes());           
            return DatatypeConverter.printHexBinary(hasher.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException nsae) {
            return nsae.getMessage();
        }   
    }

    public static int getSalt(String username) throws SQLException
    {
        int salt = 0;
        PreparedStatement statement = Application.database.newStatement("SELECT Salt FROM Users WHERE UserName = ?");

        if (statement != null)
        {
            statement.setString(1, username);
            ResultSet results = Application.database.runQuery(statement);
            if (results != null)
            {
                System.out.println("1");
                try {
                    while (results.next() ){
                        salt = new Integer (results.getInt("Salt"));
                    }
                }
                catch (SQLException resultsexception)
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }
        System.out.println(salt);
        return salt;
    }
}