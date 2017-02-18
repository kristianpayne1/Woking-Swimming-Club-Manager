import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.sql.SQLException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;
import javafx.scene.control.ButtonType;

public class HomeSceneController 
{
    private Stage stage;
    private LoginSceneController parent;

    @FXML   private Label homeTitle;
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
                    we.consume();
                    System.out.println("Close button was clicked!");
                    exitButtonClicked();
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
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        System.out.println("Populating scene with items from the database...");

        Image imageTimetable = new Image (getClass().getResourceAsStream("Images/timetable.png"));
        timetableButton.setGraphic(new ImageView(imageTimetable));

        Image imagePb = new Image (getClass().getResourceAsStream("Images/pb.png"));
        PBButton.setGraphic(new ImageView(imagePb));

        Image imageOpenMeet = new Image (getClass().getResourceAsStream("Images/openmeets.png"));
        openMeetButton.setGraphic(new ImageView(imageOpenMeet));

        Image imageGalas = new Image (getClass().getResourceAsStream("Images/galas.png"));
        galasButton.setGraphic(new ImageView(imageGalas));

        Image imageInbox = new Image (getClass().getResourceAsStream("Images/inbox.png"));
        inboxButton.setGraphic(new ImageView(imageInbox));

        Image imageExit = new Image (getClass().getResourceAsStream("Images/logoff.png"));
        exitButton.setGraphic(new ImageView(imageExit));
        
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

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("By exiting, you will be logged off");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            stage.close();
            try{
                Users.logOffUser();
            }
            catch (SQLException se)
            {
                se.printStackTrace();
            }
            Application.terminate();
        }else{
        }// Call the terminate method in the main Application class.

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

    @FXML   void GalasButtonClicked(){
        System.out.println("Galas button clicked");
        int sceneType = 1;

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("GalasGUI.fxml"));

        try
        {
            Stage stage3 = new Stage();
            stage3.setTitle("Galas");
            stage3.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage3.show();           
            CompetitionSceneController controller3 = loader.getController();
            controller3.prepareStageEvents(stage3);

            controller3.setParent1(this);
            controller3.loadItem(sceneType);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @FXML   void OpenMeetButtonClicked(){
        System.out.println("Open meet button clicked");
        int sceneType = 2;

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("GalasGUI.fxml"));

        try
        {
            Stage stage3 = new Stage();
            stage3.setTitle("Open Meets");
            stage3.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage3.show();           
            CompetitionSceneController controller3 = loader.getController();
            controller3.prepareStageEvents(stage3);

            controller3.setParent1(this);
            controller3.loadItem(sceneType);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
