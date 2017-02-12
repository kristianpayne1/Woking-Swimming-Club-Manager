import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.Statement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/* Each table you wish to access in your database requires a model class, like this example: */
public class PBTimes50m
{
    /* First, map each of the fields (columns) in your table to some public variables. */

    private final SimpleIntegerProperty PBTimesID;
    private final SimpleStringProperty Free50m;
    private final SimpleStringProperty Back50m;
    private final SimpleStringProperty Breast50m;
    private final SimpleStringProperty Fly50m;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public PBTimes50m(Integer PBTimesID, String Free50m, String Back50m, String Breast50m, String Fly50m)
    {
        this.PBTimesID = new SimpleIntegerProperty(PBTimesID);
        this.Free50m = new SimpleStringProperty(Free50m);
        this.Back50m = new SimpleStringProperty(Back50m);
        this.Breast50m = new SimpleStringProperty(Breast50m);
        this.Fly50m = new SimpleStringProperty(Fly50m);
    }

    public String getFree50m() {
        return Free50m.getValue();
    }

    public String getBack50m() {
        return Back50m.getValue();
    }
    
    public String getBreast50m() {
        return Breast50m.getValue();
    }
    
    public String getFly50m() {
        return Fly50m.getValue();
    }

}
