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

}
