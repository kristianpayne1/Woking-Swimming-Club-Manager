import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class TimetableSceneController
{

    private Stage stage;
    private HomeSceneController parent;

    @FXML   private Label  TimetableLabel;
    @FXML   private Label  SquadLabel;
    @FXML   private Label  MonLabel;
    @FXML   private Label  TueLabel;
    @FXML   private Label  WedLabel;
    @FXML   private Label  ThurLabel;
    @FXML   private Label  FriLabel;
    @FXML   private Label  SatLabel;
    @FXML   private Label  SunLabel;
    @FXML   private Label  MorningLabel;
    @FXML   private Label  EveningLabel;
    @FXML   private Label poolLabel1;
    @FXML   private Label poolLabel2;
    @FXML   private Label coachLabel1;
    @FXML   private Label coachLabel2;
    @FXML   private Button BackButton;
    @FXML   private Rectangle TimeBox1, TimeBox2, TimeBox3, TimeBox4, TimeBox5, TimeBox6, TimeBox7, TimeBox8, TimeBox9, TimeBox10, TimeBox11, TimeBox12, TimeBox13, TimeBox14, TimeBox15, TimeBox16, TimeBox17, TimeBox18, TimeBox19, TimeBox20, TimeBox21, TimeBox22, TimeBox23, TimeBox24, TimeBox25, TimeBox26, TimeBox27, TimeBox28;
    @FXML   private Label TimeLabel1, TimeLabel2, TimeLabel3, TimeLabel4, TimeLabel5, TimeLabel6, TimeLabel7, TimeLabel8, TimeLabel9, TimeLabel10, TimeLabel11, TimeLabel12, TimeLabel13, TimeLabel14, TimeLabel15, TimeLabel16, TimeLabel17, TimeLabel18, TimeLabel19, TimeLabel20, TimeLabel21, TimeLabel22, TimeLabel23, TimeLabel24, TimeLabel25, TimeLabel26, TimeLabel27, TimeLabel28;

    public TimetableSceneController()
    {
        System.out.println("Initialising controllers...");
    } 

    public void prepareStageEvents(Stage stage3)
    {
        System.out.println("Preparing stage events...");

        this.stage = stage3;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");
                    stage.close();
                }
            });
    }         

    @FXML   void initialize() throws SQLException
    {            
        System.out.println("Asserting controls...");
        try
        {
            assert   TimetableLabel != null : "Can't find TimetableLabel";
            assert   SquadLabel != null : "Can't find SquadLabel";
            assert   MonLabel != null : "Can't find MonLabel";
            assert   TueLabel != null : "Can't find TueLabel";
            assert   WedLabel != null : "Can't find WedLabel";
            assert   ThurLabel != null : "Can't find ThurLabel";
            assert   FriLabel != null : "Can't find FriLabel";
            assert   SatLabel != null : "Can't find SatLabel";
            assert   SunLabel != null : "Can't find SunLabel";
            assert   MorningLabel != null : "Can't find MorningLabel";
            assert   EveningLabel != null : "Can't find EveningLabel";
            assert  BackButton != null : "Can't find BackButton";
            assert  TimeBox1 != null : "Can't find Timebox";assert  TimeBox2 != null : "Can't find Timebox";assert  TimeBox3 != null : "Can't find Timebox";assert  TimeBox4 != null : "Can't find Timebox";assert  TimeBox5 != null : "Can't find Timebox";assert  TimeBox6 != null : "Can't find Timebox";assert  TimeBox7 != null : "Can't find Timebox";assert  TimeBox8 != null : "Can't find Timebox";assert  TimeBox9 != null : "Can't find Timebox";assert  TimeBox10 != null : "Can't find Timebox";assert  TimeBox11 != null : "Can't find Timebox";assert  TimeBox12 != null : "Can't find Timebox";assert  TimeBox13 != null : "Can't find Timebox";assert  TimeBox14 != null : "Can't find Timebox";assert  TimeBox15 != null : "Can't find Timebox";assert  TimeBox16 != null : "Can't find Timebox";assert  TimeBox17 != null : "Can't find Timebox";assert  TimeBox18 != null : "Can't find Timebox";assert  TimeBox19 != null : "Can't find Timebox";assert  TimeBox20 != null : "Can't find Timebox";assert  TimeBox21 != null : "Can't find Timebox";assert  TimeBox22 != null : "Can't find Timebox";assert  TimeBox23 != null : "Can't find Timebox";assert  TimeBox24 != null : "Can't find Timebox";assert  TimeBox25 != null : "Can't find Timebox";assert  TimeBox26 != null : "Can't find Timebox";assert  TimeBox27 != null : "Can't find Timebox";assert  TimeBox28 != null : "Can't find Timebox";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        loadTimeTable();
        
        SquadLabel.setText("Squad: " + Users.getSquadName(Users.getSwimmerID(Users.getActiveUserID())));

    }

    public void setParent1(HomeSceneController parent)
    {
        this.parent = parent;
    }

    @FXML void backButtonClicked()
    {
        System.out.println("Back button clicked");
        stage.close();
    }

    public void loadTimeTable() throws SQLException{
        System.out.println("Loading timetable...");

        PreparedStatement statement = Application.database.newStatement("SELECT Timetables.P1, Timetables.P2, Timetables.P3, Timetables.P4, Timetables.P5, Timetables.P6, Timetables.P7, Timetables.P8, Timetables.P9, Timetables.P10, Timetables.P11, Timetables.P12, Timetables.P13, Timetables.P14 FROM Timetables INNER JOIN Squads ON Timetables.TimetableID = Squads.TimetableID WHERE Squads.TimetableID = ?"); 
        PreparedStatement statement2 = Application.database.newStatement("SELECT Timetables.C1, Timetables.C2, Timetables.C3, Timetables.C4, Timetables.C5, Timetables.C6, Timetables.C7, Timetables.C8, Timetables.C9, Timetables.C10, Timetables.C11, Timetables.C12, Timetables.C13, Timetables.C14 FROM Timetables INNER JOIN Squads ON Timetables.TimetableID = Squads.TimetableID WHERE Squads.TimetableID = ?"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            statement.setInt(1, Users.getTimetableID(Users.getSwimmerID(Users.getActiveUserID())));
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        TimeLabel1.setText(results.getString("P1"));
                        TimeLabel3.setText(results.getString("P2"));
                        TimeLabel5.setText(results.getString("P3"));
                        TimeLabel7.setText(results.getString("P4"));
                        TimeLabel9.setText(results.getString("P5"));
                        TimeLabel11.setText(results.getString("P6"));
                        TimeLabel13.setText(results.getString("P7"));
                        TimeLabel15.setText(results.getString("P8"));
                        TimeLabel17.setText(results.getString("P9"));
                        TimeLabel19.setText(results.getString("P10"));
                        TimeLabel21.setText(results.getString("P11"));
                        TimeLabel23.setText(results.getString("P12"));
                        TimeLabel25.setText(results.getString("P13"));
                        TimeLabel27.setText(results.getString("P14"));
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

        if (statement != null)      // Assuming the statement correctly initated...
        {
            statement2.setInt(1, Users.getTimetableID(Users.getSwimmerID(Users.getActiveUserID())));
            ResultSet results2 = Application.database.runQuery(statement2);       // ...run the query!

            if (results2 != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results2.next()) {                                               
                        TimeLabel2.setText(results2.getString("C1"));
                        TimeLabel4.setText(results2.getString("C2"));
                        TimeLabel6.setText(results2.getString("C3"));
                        TimeLabel8.setText(results2.getString("C4"));
                        TimeLabel10.setText( results2.getString("C5"));
                        TimeLabel12.setText(results2.getString("C6"));
                        TimeLabel14.setText(results2.getString("C7"));
                        TimeLabel16.setText(results2.getString("C8"));
                        TimeLabel18.setText(results2.getString("C9"));
                        TimeLabel20.setText(results2.getString("C10"));
                        TimeLabel22.setText(results2.getString("C11"));
                        TimeLabel24.setText(results2.getString("C12"));
                        TimeLabel26.setText( results2.getString("C13"));
                        TimeLabel28.setText( results2.getString("C14"));
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }
    }
}
