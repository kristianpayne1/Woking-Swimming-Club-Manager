import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;
import java.util.ArrayList;

import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompetitionSceneController
{

    private Stage stage;
    private HomeSceneController parent;

    @FXML private Button BackButton;
    @FXML private Button SignupButton;
    @FXML private Button InfoButton;
    @FXML private Label TitleLabel;
    @FXML private Label NameLabel;
    @FXML private Label StartLabel;
    @FXML private Label FinishLabel;
    @FXML private Label LevelLabel;
    @FXML private Label SupervisionLabel;
    @FXML private Label AddressLabel;
    @FXML private Label SignedLabel;
    @FXML private TableView <Competition> CompetitionTable;
    @FXML private TableView <SignedSwimmers> SignedSwimmersTable;
    @FXML private TableColumn <Competition, String> NameColumn;
    @FXML private TableColumn <Competition, String> StartdateColumn;
    @FXML private TableColumn <Competition, Integer> LevelColumn;
    @FXML private TableColumn <SignedSwimmers, String> FirstNameColumn;
    @FXML private TableColumn <SignedSwimmers, String> LastNameColumn;

    public ObservableList<Competition> list = FXCollections.observableArrayList();
    public ObservableList<SignedSwimmers> list1 = FXCollections.observableArrayList();
    public int sceneTypeOpenMeet = 0;
    public List signedUpCompetitions = new ArrayList();

    public CompetitionSceneController()
    {
        System.out.println("Initialising controllers...");
    } 

    public void prepareStageEvents(Stage stage)
    {
        System.out.println("Preparing stage events...");

        this.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");
                    stage.close();
                }
            });
    }         

    @FXML   void initialize()  
    {            
        System.out.println("Asserting controls...");
        try
        {
            assert BackButton != null: "can't find back button";
            assert SignupButton != null :"Can't find Sign up button";
            assert InfoButton != null :"Can't find info button";
            assert TitleLabel != null :"Can't find title label";
            assert NameLabel != null :"Can't find name label";
            assert StartLabel != null :"Can't find start label";
            assert FinishLabel != null :"Can't find finish label";
            assert LevelLabel != null :"Can't find level label";
            assert SupervisionLabel != null :"Can't find supervision label";
            assert AddressLabel != null :"Can't find address label";
            assert SignedLabel != null :"Can't find signed label";
            assert CompetitionTable != null :"Can't find competition label";
            assert SignedSwimmersTable != null :"Can't find swimmers label";
            assert NameColumn != null :"Can't find name column";
            assert StartdateColumn != null :"Can't find start date column";
            assert LevelColumn != null :"Can't find level column";

        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        System.out.println("Populating scene with items from the database...");       

        NameColumn.setCellValueFactory(new PropertyValueFactory<Competition, String>("Name"));
        StartdateColumn.setCellValueFactory(new PropertyValueFactory<Competition, String>("StartDate"));
        LevelColumn.setCellValueFactory(new PropertyValueFactory<Competition, Integer>("Level"));

        FirstNameColumn.setCellValueFactory(new PropertyValueFactory<SignedSwimmers, String>("Firstname"));
        LastNameColumn.setCellValueFactory(new PropertyValueFactory<SignedSwimmers, String>("Lastname"));

        list.clear();
        list1.clear();
    }

    public void setParent1(HomeSceneController parent)
    {
        this.parent = parent;
    }

    public  void readAllOpenMeets() throws SQLException
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Application.database.newStatement("SELECT OpenMeetID, Name, StartDate, FinishDate, Level, CoachID, Address FROM OpenMeet"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        list.add( new Competition(
                                results.getInt("OpenMeetID"),
                                results.getString("Name"), 
                                Application.stringDate(results.getDate("StartDate")), 
                                Application.stringDate(results.getDate("FinishDate")),
                                results.getInt("Level"),
                                Users.getCoachName(results.getInt("CoachID")),
                                results.getString("Address")));
                        CompetitionTable.setItems(list);
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

    public  void readAllGalas() throws SQLException
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Application.database.newStatement("SELECT GalaID, Name, StartDate, FinishDate, Level, CoachID, Address FROM Galas"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        list.add( new Competition(
                                results.getInt("GalaID"),
                                results.getString("Name"), 
                                Application.stringDate(results.getDate("StartDate")), 
                                Application.stringDate(results.getDate("FinishDate")),
                                results.getInt("Level"),
                                Users.getCoachName(results.getInt("CoachID")),
                                results.getString("Address")));
                        CompetitionTable.setItems(list);
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

    public  void readAllGalaSignedSwimmers(int GalaID) throws SQLException
    {
        list1.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Application.database.newStatement("SELECT Firstname, Lastname FROM Swimmers INNER JOIN SignedUpGalaSwimmers ON Swimmers.SwimmerID = SignedUpGalaSwimmers.SwimmerID WHERE SignedUpGalaSwimmers.GalaID = ?;"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            statement.setInt(1, GalaID);
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) { 
                        list1.add( new SignedSwimmers(
                                results.getString("Firstname"),
                                results.getString("Lastname")));
                        SignedSwimmersTable.setItems(list1);
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

    public  void readAllOpenMeetSignedSwimmers(int OpenMeetID) throws SQLException
    {
        list1.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Application.database.newStatement("SELECT Firstname, Lastname FROM Swimmers INNER JOIN SignedUpOpenMeetSwimmers ON Swimmers.SwimmerID = SignedUpOpenMeetSwimmers.SwimmerID WHERE SignedUpOpenMeetSwimmers.OpenMeetID = ?;"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            statement.setInt(1, OpenMeetID);
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) { 
                        list1.add( new SignedSwimmers(
                                results.getString("Firstname"),
                                results.getString("Lastname")));
                        SignedSwimmersTable.setItems(list1);
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

    }

    @FXML   void tableViewClicked() throws SQLException
    {
        if (sceneTypeOpenMeet == 2){
            int OpenMeetID = 0;
            Competition selectedItem = (Competition) CompetitionTable.getSelectionModel().getSelectedItem();

            if (selectedItem == null)
            {
                System.out.println("Nothing selected!");
            }
            else
            {   
                NameLabel.setText("Name: " + selectedItem.getName());
                StartLabel.setText("StartDate: " + selectedItem.getStartDate());
                FinishLabel.setText("FinishDate: " + selectedItem.getFinishDate());
                LevelLabel.setText("Level: " + selectedItem.getLevel());
                SupervisionLabel.setText("Supervision: " + selectedItem.getSupervision());
                AddressLabel.setText("Address: " + selectedItem.getAddress());

                System.out.println(selectedItem.getName());
                OpenMeetID = selectedItem.getCompetitionID();
                readAllOpenMeetSignedSwimmers(OpenMeetID);

                if (signedUpCompetitions.contains(OpenMeetID) == true){
                    SignupButton.setDisable(true);
                }else{
                    SignupButton.setDisable(false);
                }
            }
        }else{
            int GalaID = 0;
            Competition selectedItem = (Competition) CompetitionTable.getSelectionModel().getSelectedItem();

            if (selectedItem == null)
            {
                System.out.println("Nothing selected!");
            }
            else
            {   
                NameLabel.setText("Name: " + selectedItem.getName());
                StartLabel.setText("StartDate: " + selectedItem.getStartDate());
                FinishLabel.setText("FinishDate: " + selectedItem.getFinishDate());
                LevelLabel.setText("Level: " + selectedItem.getLevel());
                SupervisionLabel.setText("Supervision: " + selectedItem.getSupervision());
                AddressLabel.setText("Address: " + selectedItem.getAddress());

                System.out.println(selectedItem.getName());
                GalaID = selectedItem.getCompetitionID();
                readAllGalaSignedSwimmers(GalaID);

                if (signedUpCompetitions.contains(GalaID) == true){
                    SignupButton.setDisable(true);
                }else{
                    SignupButton.setDisable(false);
                }
            }
        }
    }

    @FXML void backButtonClicked()
    {
        System.out.println("Back button clicked");
        stage.close();
    }

    public void loadItem(int sceneType) throws SQLException
    {      
        if (sceneType == 2){
            sceneTypeOpenMeet = sceneType;
            TitleLabel.setText("Open Meets");
            readAllOpenMeets();
        }else{
            readAllGalas();
        }
    }

    @FXML void signUpButtonClicked() throws SQLException
    {
        System.out.println("Sign up button clicked");
        Competition selectedItem = (Competition) CompetitionTable.getSelectionModel().getSelectedItem();

        if (selectedItem == null)
        {
            System.out.println("Nothing selected!");
        }
        else
        {   
            if (sceneTypeOpenMeet == 2)
            {
                int OpenMeetID = selectedItem.getCompetitionID();
                PreparedStatement statement = Application.database.newStatement("INSERT INTO SignedUpOpenMeetSwimmers (OpenMeetID, SwimmerID) VALUES (?, ?)");

                try
                {
                    statement.setInt(1, OpenMeetID);
                    statement.setInt(2, Users.getActiveUserID());

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
                    signedUpCompetitions.add(OpenMeetID);
                    SignupButton.setDisable(true);
                    readAllOpenMeetSignedSwimmers(OpenMeetID);
                }
            }else{
                int GalaID = selectedItem.getCompetitionID();
                PreparedStatement statement = Application.database.newStatement("INSERT INTO SignedUpGalaSwimmers (GalaID, SwimmerID) VALUES (?, ?)");

                try
                {
                    statement.setInt(1, GalaID);
                    statement.setInt(2, Users.getActiveUserID());

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
                    signedUpCompetitions.add(GalaID);
                    SignupButton.setDisable(true);
                    readAllGalaSignedSwimmers(GalaID);
                }
            }
        }
    }

    @FXML void infoButtonClicked()
    {
        System.out.println("More info button clicked");

    }

}

