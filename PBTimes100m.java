import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.Statement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/* Each table you wish to access in your database requires a model class, like this example: */
public class PBTimes100m
{
    /* First, map each of the fields (columns) in your table to some public variables. */

    private final SimpleIntegerProperty PBID;
    private final SimpleStringProperty Free;
    private final SimpleStringProperty Back;
    private final SimpleStringProperty Breast;
    private final SimpleStringProperty Fly;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public PBTimes100m(Integer PBID, String Free, String Back, String Breast, String Fly)
    {
        this.PBID = new SimpleIntegerProperty(PBID);
        this.Free = new SimpleStringProperty(Free);
        this.Back = new SimpleStringProperty(Back);
        this.Breast = new SimpleStringProperty(Breast);
        this.Fly = new SimpleStringProperty(Fly);
    }

    public String getFree() {
        return Free.get();
    }

    public String getBack() {
        return Back.get();
    }

    public String getBreast() {
        return Breast.get();
    }

    public String getFly() {
        return Fly.get();
    }

    public Integer getPBID(){
        return PBID.get();
    }

}
