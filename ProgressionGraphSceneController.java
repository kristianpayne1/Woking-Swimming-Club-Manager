import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.chart.LineChart;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgressionGraphSceneController
{

    private Stage stage;
    private PBTimesSceneController parent;

    @FXML   private Label GraphLabel;
    @FXML   private Button backButton;
    @FXML   private Button Button50m;
    @FXML   private Button Button100m;
    @FXML   private Button FreeButton;
    @FXML   private Button BackButton;
    @FXML   private Button BreastButton;
    @FXML   private Button FlyButton;
    @FXML   private LineChart ProgressionGraph;

    public ProgressionGraphSceneController()
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
            assert GraphLabel != null : "Can't find graph label";
            assert backButton != null : "Can't find back button";
            assert Button50m != null : "Can't find 50m button";
            assert Button100m != null : "Can't find 100m button";
            assert FreeButton != null : "Can't find free button";
            assert BackButton != null : "Can't find back button";
            assert BreastButton != null : "Can't find breast button";
            assert FlyButton != null : "Can't find fly button";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        System.out.println("Populating scene with items from the database...");       

    }

    public void setParent2(PBTimesSceneController parent2)
    {
        this.parent = parent2;
    }

    @FXML void backButtonClicked()
    {
        System.out.println("Back button clicked");
        stage.close();
    }
}

