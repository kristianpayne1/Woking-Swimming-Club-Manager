import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import java.util.List;
import java.sql.SQLException;

import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReplySceneController
{

    private Stage stage;
    private InboxSceneController parent;

    @FXML   private TextField toField;
    @FXML   private TextField subjectField;
    @FXML   private TextArea messageArea;
    @FXML   private Button sendMessageButton;

    public ObservableList<Message> list = FXCollections.observableArrayList();
    public ReplySceneController()
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
            assert toField != null : "Can't find fromField";
            assert subjectField != null : "Can't find subjectField";
            assert messageArea != null : "Can't find messageArea";
            assert sendMessageButton != null : "Can't find deleteButton";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        System.out.println("Populating scene with items from the database...");       

        list.clear();
    }

    public void setParent2(InboxSceneController parent2)
    {
        this.parent = parent2;
    }

    @FXML   void sendClicked() throws SQLException
    {
        System.out.println("Send was clicked!");    

        String to = toField.getText();
        String subject = subjectField.getText();
        String message = messageArea.getText();
        if (subject != null || to != null){
            Message.sendMessage(to, subject, message);
            toField.clear();
            subjectField.clear();
            messageArea.clear();
            stage.close();
        }else{
            System.out.println("No subject or username was entered. Message not sent");
        }

    }

    public void loadItem(int MessageID) throws SQLException
    {        
        String to = null;
        String subject = null;

        PreparedStatement statement = Application.database.newStatement("SELECT Subject, CreaterID FROM Message WHERE MessageID = ?"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            statement.setInt(1, MessageID);
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                              
                        subject = results.getString("Subject");
                        to = Users.getUsername(results.getInt("CreaterID"));

                    }
                }
                catch (SQLException resultsexception)       // Catch any error processing the results.
                {
                    System.out.println("Database result processing error: " + resultsexception.getMessage());
                }
            }
        }

        toField.setText(to);
        subjectField.setText("RE:"+subject);
    }
}
/*
*public void loadItem(int id)
{        
thing = Thing.getById(id);
nameTextField.setText(thing.name);

List<Category> targetList = categoryChoiceBox.getItems();

for(Category c : targetList)
{
if (c.id == thing.categoryId)
{
categoryChoiceBox.getSelectionModel().select(c);
}                
}

}

@FXML   void saveButtonClicked()
{
System.out.println("Save button clicked!");        

if (thing == null)
{   
thing = new Thing(0, "", 0);
}

thing.name = nameTextField.getText();

Category selectedCategory = (Category) categoryChoiceBox.getSelectionModel().getSelectedItem();        
thing.categoryId = selectedCategory.id;

thing.save();

parent.initialize();

stage.close();
}

@FXML   void cancelButtonClicked()
{
System.out.println("Cancel button clicked!");        
stage.close();
}
*/

