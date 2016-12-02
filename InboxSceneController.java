import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
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
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class InboxSceneController
{

    private Stage stage;
    private HomeSceneController parent;

    @FXML   private TextField fromField;
    @FXML   private TextField subjectField;
    @FXML   private TextField createField;
    @FXML   private TextArea messageArea;
    @FXML   private Button replyButton;
    @FXML   private Button deleteButton;
    @FXML   private Button backButton;
    @FXML   private TableView<Message> tableView;
    @FXML   private TableColumn<Message, String> fromColumn;
    @FXML   private TableColumn<Message, String> subjectColumn;
    @FXML   private TableColumn<Message, String> messageColumn;

    public ObservableList<Message> list = FXCollections.observableArrayList();
    public InboxSceneController()
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
            assert fromField != null : "Can't find fromField";
            assert subjectField != null : "Can't find subjectField";
            assert createField != null : "Can't find createField";
            assert messageArea != null : "Can't find messageArea";
            assert replyButton != null : "Can't find replyButton";
            assert deleteButton != null : "Can't find deleteButton";
            assert backButton != null : "Can't find backButton";
        }
        catch (AssertionError ae)
        {
            System.out.println("FXML assertion failure: " + ae.getMessage());
            Application.terminate();
        }

        System.out.println("Populating scene with items from the database...");       

        fromColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("From"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("Subject"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<Message, String>("Message"));

        list.clear();
        readAll();
    }

    public void setParent1(HomeSceneController parent)
    {
        this.parent = parent;
    }

    public  void readAll() throws SQLException
    {
        list.clear();       // Clear the target list first.

        /* Create a new prepared statement object with the desired SQL query. */
        PreparedStatement statement = Application.database.newStatement("SELECT MessageID, Subject, CreaterID, MessageBody, CreateDate FROM Message WHERE recieverID = ?"); 

        if (statement != null)      // Assuming the statement correctly initated...
        {
            statement.setInt(1, Users.getActiveUserID());
            ResultSet results = Application.database.runQuery(statement);       // ...run the query!

            if (results != null)        // If some results are returned from the query...
            {
                try {                               // ...add each one to the list.
                    while (results.next()) {                                               
                        list.add( new Message(
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

    @FXML   void tableViewClicked()
    {
        Message selectedItem = (Message) tableView.getSelectionModel().getSelectedItem();

        if (selectedItem == null)
        {
            System.out.println("Nothing selected!");
        }
        else
        {   
            fromField.setDisable(false);
            subjectField.setDisable(false);
            createField.setDisable(false);
            messageArea.setDisable(false);
            replyButton.setDisable(false);
            deleteButton.setDisable(false);
            fromField.setText(selectedItem.getFrom());
            subjectField.setText(selectedItem.getSubject());
            createField.setText(selectedItem.getDate());
            messageArea.setText(selectedItem.getMessage());
            System.out.println(selectedItem.getSubject());
        }
    }

    @FXML   void replyButtonPressed()   
    {
        System.out.println("Reply button pressed");
        Message selectedItem = (Message) tableView.getSelectionModel().getSelectedItem();
        openNewScene(selectedItem.getMessageID());
    }

    @FXML   void deleteButtonPressed() throws SQLException
    {
        System.out.println("Delete was clicked");
        Message selectedItem = (Message) tableView.getSelectionModel().getSelectedItem();
        Message.deleteById(selectedItem.getMessageID());
        fromField.clear();
        subjectField.clear();
        createField.clear();
        messageArea.clear();
        readAll();
    }

    void openNewScene(int MessageID)
    {

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("replyGUI.fxml"));

        try
        {
            Stage stage3 = new Stage();
            stage3.setTitle("Reply");
            stage3.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage3.show();           
            ReplySceneController controller4 = loader.getController();
            controller4.prepareStageEvents(stage3);

            controller4.setParent2(this);
            if (MessageID != 0) controller4.loadItem(MessageID);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }
}
