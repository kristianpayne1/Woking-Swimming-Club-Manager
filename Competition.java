import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.Statement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/* Each table you wish to access in your database requires a model class, like this example: */
public class Competition
{
    /* First, map each of the fields (columns) in your table to some public variables. */

    private final SimpleIntegerProperty CompetitionID;
    private final SimpleStringProperty Name;
    private final SimpleStringProperty StartDate;
    private final SimpleStringProperty FinishDate;
    private final SimpleIntegerProperty Level;
    private final SimpleStringProperty Supervision;
    private final SimpleStringProperty Address;

    /* Next, prepare a constructor that takes each of the fields as arguements. */
    public Competition(Integer CompetitionID, String Name, String StartDate, String FinishDate, Integer Level, String Supervision, String Address)
    {
        this.CompetitionID = new SimpleIntegerProperty(CompetitionID);
        this.Name = new SimpleStringProperty(Name);
        this.StartDate = new SimpleStringProperty(StartDate);
        this.FinishDate = new SimpleStringProperty(FinishDate);
        this.Level = new SimpleIntegerProperty(Level);
        this.Supervision = new SimpleStringProperty(Supervision);
        this.Address = new SimpleStringProperty(Address);

    }

    public String getName() {
        return Name.get();
    }

    public String getStartDate() {
        return StartDate.get();
    }

    public String getFinishDate() {
        return FinishDate.get();
    }
    
    public Integer getLevel() {
        return Level.get();
    }

    public String getSupervision()  {
        return Supervision.get();
    }

    public String getAddress() {
        return Address.get();
    }
    
    public Integer getCompetitionID() {
        return CompetitionID.get();
    }

    public static String stringDate(java.sql.Date CreateDate) 
    {
        String date = CreateDate.toString();
        return date;
    }

}
