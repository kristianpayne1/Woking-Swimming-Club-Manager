import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.sql.SQLException;

public class HomeSceneController 
{
    private Stage stage;
    private LoginSceneController parent;

    @FXML   private Label homeTitle;
    @FXML   private Label ActiveUserLabel;
    @FXML   private Button timetableButton;
    @FXML   private Button PBButton;
    @FXML   private Button openMeetButton;
    @FXML   private Button galasButton;
    @FXML   private Button inboxButton;
    @FXML   private Button exitButton;

    public HomeSceneController()
    {
        System.out.println("Initialising controllers...");
    } 

    public void prepareStageEvents(Stage stage2) throws SQLException
    {
        System.out.println("Preparing stage events...");

        this.stage = stage2;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() 
            {
                public void handle(WindowEvent we)
                {
                    System.out.println("Close button was clicked!");
                    stage.close();
                    try{
                        Users.logOffUser();
                    }
                    catch (SQLException se)
                    {
                        se.printStackTrace();
                    }
                    Application.terminate();
                }
            });
    }         

    @FXML   void initialize()
    {            
        System.out.println("Asserting controls...");
        try
        {
            assert homeTitle != null : "Can't find homeTitle";
            assert timetableButton != null : "Can't find timetableButton";
            assert PBButton != null : "Can't find PBButton";
            assert openMeetButton != null : "Can't find openMeetButton";
            assert galasButton != null : "Can't find galasButton";
            assert inboxButton != null : "Can't find inboxButton";
            assert exitButton != null : "Can't find exitButton";
            assert ActiveUserLabel != null : "Can't find Active User Label";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        System.out.println("Populating scene with items from the database...");       

    }

    public void setParent(LoginSceneController parent)
    {
        this.parent = parent;
    }

    @FXML   void inboxButtonClicked() {
        System.out.println("Inbox button clicked");
        openNewSceneInbox();
    }

    @FXML void exitButtonClicked() {
        System.out.println("Exit button was clicked!");
        stage.close();
        try{
            Users.logOffUser();
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        Application.terminate();
    }

    void openNewSceneInbox()
    {

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("inboxGUI.fxml"));

        try
        {
            Stage stage3 = new Stage();
            stage3.setTitle("Inbox");
            stage3.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage3.show();           
            InboxSceneController controller3 = loader.getController();
            controller3.prepareStageEvents(stage3);

            controller3.setParent1(this);

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    @FXML   void timetableButtonClicked(){
        System.out.println("Timetable button clicked");

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("timetableGUI.fxml"));

        try
        {
            Stage stage3 = new Stage();
            stage3.setTitle("Timetable");
            stage3.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage3.show();           
            TimetableSceneController controller3 = loader.getController();
            controller3.prepareStageEvents(stage3);

            controller3.setParent1(this);

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML   void PBTimesButtonClicked(){
        System.out.println("PBTimes button clicked");

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("PBTimesGUI.fxml"));

        try
        {
            Stage stage3 = new Stage();
            stage3.setTitle("PBTimes");
            stage3.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage3.show();           
            PBTimesSceneController controller3 = loader.getController();
            controller3.prepareStageEvents(stage3);

            controller3.setParent1(this);

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
