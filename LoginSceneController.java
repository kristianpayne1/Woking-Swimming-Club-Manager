import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public class LoginSceneController
{    
    /* The stage that the scene belongs to, required to catch stage events and test for duplicate controllers. */
    private static Stage stage;     
    private int loginAttempts = 0;

    /* These FXML variables exactly corrispond to the controls that make up the scene, as designed in Scene 
     * Builder. It is important to ensure that these match perfectly or the controls won't be interactive. */

    @FXML   private Label titleLabel;
    @FXML   private Button loginButton;
    @FXML   private TextField usernameField;
    @FXML   private TextField passwordField;

    public LoginSceneController()          // The constructor method, called first when the scene is loaded.
    {
        System.out.println("Initialising controllers...");

        /* Our JavaFX application should only have one initial scene. The following checks to see
         * if a scene already exists (deterimed by if the stage variable has been set) and if so 
         * terminates the whole application with an error code (-1). */        
        if (stage != null)
        {
            System.out.println("Error, duplicate controller - terminating application!");
            System.exit(-1);
        }        

    } 

    @FXML   void initialize()           // The method automatically called by JavaFX after the constructor.
    {            
        /* The following assertions check to see if the JavaFX controls exists. If one of these fails, the
         * application won't work. If the control names in Scene Builder don't match the variables this fails. */ 
        System.out.println("Asserting controls...");
        try
        {

            assert loginButton != null : "Can't find login button";
            assert usernameField != null : "Can't find username field";
            assert passwordField != null : "Can't find password field";
            assert titleLabel != null : "Can't find title label";

        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        /* Next, we load the list of fruit from the database and populate the listView. */
        System.out.println("Populating scene with items from the database...");        
        //@SuppressWarnings("unchecked")

        //Image imageInbox = new Image (getClass().getResourceAsStream("inbox.png"));
        //inboxButton.setGraphic(new ImageView(imageInbox));
    }

    /* In order to catch stage events (the main example being the close (X) button being clicked) we need
     * to setup event handlers. This happens after the constructor and the initialize methods. Once this is
     * complete, the scene is fully loaded and ready to use. */
    public void prepareStageEvents(Stage stage)
    {
        System.out.println("Preparing stage events...");

        this.stage = stage;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.out.println("Close button was clicked!");
                    Application.terminate();
                }
            });
    }       

    /* The next three methods are event handlers for clicking on the buttons. 
     * The names of these methods are set in Scene Builder so they work automatically. */    
    @FXML   void loginClicked() throws SQLException
    {
        System.out.println("Login was clicked!");    

        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean loginSuccess = false;
        if (loginAttempts != 5){
            if (username != null || password != null){
                loginSuccess = Users.isLoginValid(username, password);
                if (loginSuccess == true){
                    openNewScene();
                    Users.setActiveUser(username);
                    stage.close();
                }else{
                    loginAttempts++; 
                }
            }else{
                System.out.println("No username or password entered");
            }
        }else{
            System.out.println("Too many login attempts. Login disabled");
            loginButton.setDisable(true);
        }
    }

    void openNewScene()
    {

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("HomeGUI.fxml"));

        try
        {
            Stage stage2 = new Stage();
            stage2.setTitle("Home");
            stage2.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage2.show();           
            HomeSceneController controller2 = loader.getController();
            controller2.prepareStageEvents(stage2);

            controller2.setParent(this);

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}

