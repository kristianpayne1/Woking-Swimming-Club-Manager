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
    //This variable will be used later to check how many times the user has tried to login unsuccessfully 
    
    /* These FXML variables exactly corrispond to the controls that make up the scene, as designed in Scene 
     * Builder. It is important to ensure that these match perfectly or the controls won't be interactive. */

    @FXML   private Label titleLabel;
    @FXML   private Label loginLabel;
    @FXML   private Label failedLabel;
    @FXML   private ImageView WokingLogo;
    @FXML   private Button loginButton;
    @FXML   private TextField usernameField;
    @FXML   private TextField passwordField;

    public LoginSceneController()          // The constructor method, called first when the scene is loaded.
    {
        System.out.println("Initialising controllers...");

        /* My  application should only have one initial scene. The following checks to see
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
            assert WokingLogo != null : "Can't find imageview";
            assert loginLabel != null :"Can't find login label";
            assert failedLabel != null :"Can't find failed label";

        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

    }

    /* In order to catch stage events (the main example being the close (X) button being clicked) I need
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

    /* The next method is event handlers for clicking on the login button. 
     * The name of this method is set in Scene Builder so it works automatically. */
     
     //This method is used to check whether or not the strings that the user inputted was correct or incorrect
    @FXML   void loginClicked() throws SQLException
    {
        System.out.println("Login was clicked!");    

        String username = usernameField.getText(); //stores what the user typed into string variable username
        String password = passwordField.getText(); //stores what the user types into string variable password

        boolean loginSuccess = false;
        if (loginAttempts != 5){ //check's if the user has not reached the maximum login attempts
            if (username != null || password != null){ //checks if the user has entered something in both username and password fields
                loginSuccess = Users.isLoginValid(username, password); //calls the method isLoginValid from class Users, this returns a boolean
                if (loginSuccess == true){ //if the returned boolean value is true then the login is valid 
                    openNewScene(); //calls the method openNewScene
                    Users.setActiveUser(username); //calls the method setActiveUser of the username who has just successfully logged in to be active
                    stage.close(); //closes the login scene
                }else{ // if returned boolean was false 
                    loginAttempts++; //increments login attemps
                    failedLabel.setVisible(true); //sets the label failedLabel to be visible
                }
            }else{
                System.out.println("No username or password entered"); 
            }
        }else{
            System.out.println("Too many login attempts. Login disabled");
            loginButton.setDisable(true); //disables the login button 
        }
    }

    //this methods is called if the the login was successful 
    void openNewScene()
    {
        
        FXMLLoader loader = new FXMLLoader(Application.class.getResource("HomeGUI.fxml")); //loads new scene

        try
        {
            Stage stage2 = new Stage(); //constructor for second stage
            stage2.setTitle("Home"); //sets the title of the scene to Home
            stage2.setScene(new Scene(loader.load())); //sets new scene as second stage
            stage.setResizable(false); //disables resizable
            stage2.show();           //shows new scene
            HomeSceneController controller2 = loader.getController(); //calls the controller for the home scene
            controller2.prepareStageEvents(stage2); //calls prepareStageEvents from home scene controller

            controller2.setParent(this); //sets this stage as the new scene parent

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage()); //error messages
        }

    }

}

