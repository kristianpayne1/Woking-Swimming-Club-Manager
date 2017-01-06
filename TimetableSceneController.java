import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

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
    @FXML   private Button BackButton;
    @FXML   private Rectangle TimeBox1;
    @FXML   private Rectangle TimeBox2;
    @FXML   private Rectangle TimeBox3;
    @FXML   private Rectangle TimeBox4;
    @FXML   private Rectangle TimeBox5;
    @FXML   private Rectangle TimeBox6;
    @FXML   private Rectangle TimeBox7;
    @FXML   private Rectangle TimeBox8;
    @FXML   private Rectangle TimeBox9;
    @FXML   private Rectangle TimeBox10;
    @FXML   private Rectangle TimeBox11;
    @FXML   private Rectangle TimeBox12;
    @FXML   private Rectangle TimeBox13;
    @FXML   private Rectangle TimeBox14;
    @FXML   private Rectangle TimeBox15;
    @FXML   private Rectangle TimeBox16;
    @FXML   private Rectangle TimeBox17;
    @FXML   private Rectangle TimeBox18;
    @FXML   private Rectangle TimeBox19;
    @FXML   private Rectangle TimeBox20;
    @FXML   private Rectangle TimeBox21;
    @FXML   private Rectangle TimeBox22;
    @FXML   private Rectangle TimeBox23;
    @FXML   private Rectangle TimeBox24;
    @FXML   private Rectangle TimeBox25;
    @FXML   private Rectangle TimeBox26;
    @FXML   private Rectangle TimeBox27;
    @FXML   private Rectangle TimeBox28;

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

    /*void openNewScene(int MessageID)
    {

    FXMLLoader loader = new FXMLLoader(Application.class.getResource("messageGUI.fxml"));

    try
    {
    Stage stage3 = new Stage();
    stage3.setTitle("Reply");
    stage3.setScene(new Scene(loader.load()));
    stage.setResizable(false);
    stage3.show();           
    MessageSceneController controller4 = loader.getController();
    controller4.prepareStageEvents(stage3);

    controller4.setParent2(this);
    if (MessageID != 0) {
    controller4.loadItem(MessageID);
    }
    }
    catch (Exception ex)
    {
    System.out.println(ex.getMessage());
    }

    }
     */
}
