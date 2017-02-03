import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;

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
    @FXML private TableView CompetitionTable;
    @FXML private TableView SwimmersTable;
    @FXML private TableColumn NameColumn;
    @FXML private TableColumn StartdateColumn;
    @FXML private TableColumn LevelColumn;
    @FXML private TableColumn FirstnameColumn;
    @FXML private TableColumn LastnameColumn;

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
}

