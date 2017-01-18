import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class PBTimesSceneController
{

    private Stage stage;
    private HomeSceneController parent;

    @FXML   private Label  PBTimesTitle;
    @FXML   private Label   labe50m;
    @FXML   private Label   label00m;
    @FXML   private Button backButton;
    @FXML   private Button  ViewProgressionButton;
    @FXML   private TableView /*<PBTimes50m>*/   tableView50m;
    @FXML   private TableView /*<PBTImes100m>*/   tableView100m;
    @FXML   private TableColumn /*<PBTimes50m, Time>*/ tableColumnFree50m;
    @FXML   private TableColumn /*<PBTimes50m, Time>*/ tableColumnBack50m;
    @FXML   private TableColumn /*<PBTimes50m, Time>*/ tableColumnBreast50m;
    @FXML   private TableColumn /*<PBTimes50m, Time>*/ tableColumnFly50m;
    @FXML   private TableColumn /*<PBTimes100m, Time>*/ tableColumnFree100m;
    @FXML   private TableColumn /*<PBTimes100m, Time>*/ tableColumnBack100m;
    @FXML   private TableColumn /*<PBTimes100m, Time>*/ tableColumnBreast100m;
    @FXML   private TableColumn /*<PBTimes100m, Time>*/ tableColumnFly100m;

    //public ObservableList<PBTImes50m> list50m = FXCollections.observableArrayList();
    //public ObservableList<PBTImes100m> list100m = FXCollections.observableArrayList();
    public PBTimesSceneController()
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
            assert  PBTimesTitle != null : "Can't find TimetableLabel";
            assert  backButton != null : "Can't find BackButton";
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

    @FXML void progressionButtonClicked(){
        System.out.println("progression button clicked");

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("ProgressionGraphGUI.fxml"));

        try
        {
            Stage stage4 = new Stage();
            stage4.setTitle("Progression Graph");
            stage4.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage4.show();           
            ProgressionGraphSceneController controller4 = loader.getController();
            controller4.prepareStageEvents(stage4);

            controller4.setParent2(this);

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
