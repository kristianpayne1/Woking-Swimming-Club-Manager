import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;

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
    @FXML private Button DeclineButton;
    @FXML private Button InfoButton;
    @FXML private Label TitleLabel;
    @FXML private Label NameLabel;
    @FXML private Label StartLabel;
    @FXML private Label FinishLabel;
    @FXML private Label LevelLabel;
    @FXML private Label SupervisionLabel;
    @FXML private Label AddressLabel;
    @FXML private Label SignedLabel;
    @FXML private TableView <OpenMeet> CompetitionTable;
    @FXML private TableView SwimmersTable;
    @FXML private TableColumn <OpenMeet, String> NameColumn;
    @FXML private TableColumn <OpenMeet, String> StartdateColumn;
    @FXML private TableColumn <OpenMeet, Integer> LevelColumn;
    @FXML private TableColumn FirstnameColumn;
    @FXML private TableColumn LastnameColumn;

    public ObservableList<OpenMeet> list = FXCollections.observableArrayList();
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
            assert DeclineButton != null :"Can't find decline button";
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
            assert SwimmersTable != null :"Can't find swimmers label";
            assert NameColumn != null :"Can't find name column";
            assert StartdateColumn != null :"Can't find start date column";
            assert LevelColumn != null :"Can't find level column";
            assert FirstnameColumn != null :"Can't find firstname column";
            assert LastnameColumn != null :"Can't find last name column";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        System.out.println("Populating scene with items from the database...");       

        NameColumn.setCellValueFactory(new PropertyValueFactory<OpenMeet, String>("Name"));
        StartdateColumn.setCellValueFactory(new PropertyValueFactory<OpenMeet, String>("StartDate"));
        LevelColumn.setCellValueFactory(new PropertyValueFactory<OpenMeet, Integer>("Level"));

        list.clear();
    }

    public void setParent1(HomeSceneController parent)
    {
        this.parent = parent;
    }
    
    public  void readAll() throws SQLException
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Application.database.newStatement("SELECT OpenMeetID, Name, StartDate, FinishDate, Level, Supervision, Address FROM Message WHERE RecieverID = ?"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            statement.setInt(1, Users.getActiveUserID());
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        list.add( new OpenMeet(
                                results.getInt("MessageID"),
                                results.getString("Subject"), 
                                Users.getUsername(results.getInt("CreaterID")), 
                                results.getString("MessageBody"),
                                Message.stringDate(results.getDate("CreateDate"))));
                        tableView.setItems(list);
                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
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
            TitleLabel.setText("Open Meets");
            DeclineButton.setDisable(true);
        }
    }
}

