import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
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
    @FXML   private RadioButton Button50m;
    @FXML   private RadioButton Button100m;
    @FXML   private RadioButton FreeButton;
    @FXML   private RadioButton BackButton;
    @FXML   private RadioButton BreastButton;
    @FXML   private RadioButton FlyButton;
    @FXML   private LineChart<String , Number> ProgressionGraph;

    public int buttonCombo = 0;

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

    public void loadProgressionGraph(int buttonCase)
    {

        if (buttonCase == 0){
            PreparedStatement statement = Application.database.newStatement("SELECT PrevFree50m.PB1, PrevFree50m.PB1Date, PrevFree50m.PB2, PrevFree50m.PB2Date, PrevFree50m.PB3, PrevFree50m.PB3Date, PrevFree50m.PB4, PrevFree50m.PB4Date, PBTimes.Free50m, PBTimes.Free50mDate FROM PrevFree50m INNER JOIN PBTimes ON PBTimes.PrevFree50mID = PrevFree50m.PrevFreeID WHERE PBTimes.PBID = 1;"); 
            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
            if (statement != null)      // Assuming the statement correctly initated...
            {
                //statement.setInt(1, Users.getSwimmerID(Users.getActiveUserID()));
                ResultSet results = Application.database.runQuery(statement);       // ...run the query!

                if (results != null)        // If some results are returned from the query...
                {
                    try {                               // ...add each one to the list.
                        while (results.next()) {                                               
                            series.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB1Date")), results.getInt("PB1")));
                            series.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB2Date")), results.getInt("PB2")));
                            series.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB3Date")), results.getInt("PB3")));
                            series.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB4Date")), results.getInt("PB4")));
                            series.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("Free50mDate")), results.getInt("Free50m")));
                            ProgressionGraph.getData().add(series);
                        }
                    }
                    catch (SQLException resultsexception)       // Catch any error processing the results.
                    {
                        System.out.println("Database result processing error: " + resultsexception.getMessage());
                    }
                }
            }
        }else if(buttonCase == 1){
            PreparedStatement statement = Application.database.newStatement("SELECT PrevBack50m.PB1, PrevBack50m.PB1Date, PrevBack50m.PB2, PrevBack50m.PB2Date, PrevBack50m.PB3, PrevBack50m.PB3Date, PrevBack50m.PB4, PrevBack50m.PB4Date, PBTimes.Back50m, PBTimes.Back50mDate FROM PrevBack50m INNER JOIN PBTimes ON PBTimes.PrevBack50mID = PrevBack50m.PrevBackID WHERE PBTimes.PBID = 1;"); 
            XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
            if (statement != null)      // Assuming the statement correctly initated...
            {
                //statement.setInt(1, Users.getSwimmerID(Users.getActiveUserID()));
                ResultSet results = Application.database.runQuery(statement);       // ...run the query!

                if (results != null)        // If some results are returned from the query...
                {
                    try {                               // ...add each one to the list.
                        while (results.next()) {                                               
                            series2.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB1Date")), results.getInt("PB1")));
                            series2.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB2Date")), results.getInt("PB2")));
                            series2.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB3Date")), results.getInt("PB3")));
                            series2.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB4Date")), results.getInt("PB4")));
                            series2.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("Back50mDate")), results.getInt("Back50m")));
                            ProgressionGraph.getData().add(series2);
                        }
                    }
                    catch (SQLException resultsexception)       // Catch any error processing the results.
                    {
                        System.out.println("Database result processing error: " + resultsexception.getMessage());
                    }
                }
            }
        }else if(buttonCase == 2){
            PreparedStatement statement = Application.database.newStatement("SELECT PrevBreast50m.PB1, PrevBreast50m.PB1Date, PrevBreast50m.PB2, PrevBreast50m.PB2Date, PrevBreast50m.PB3, PrevBreast50m.PB3Date, PrevBreast50m.PB4, PrevBreast50m.PB4Date, PBTimes.Breast50m, PBTimes.Breast50mDate FROM PrevBreast50m INNER JOIN PBTimes ON PBTimes.PrevBreast50mID = PrevBreast50m.PrevBreastID WHERE PBTimes.PBID = 1;"); 
            XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
            if (statement != null)      // Assuming the statement correctly initated...
            {
                //statement.setInt(1, Users.getSwimmerID(Users.getActiveUserID()));
                ResultSet results = Application.database.runQuery(statement);       // ...run the query!

                if (results != null)        // If some results are returned from the query...
                {
                    try {                               // ...add each one to the list.
                        while (results.next()) {                                               
                            series3.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB1Date")), results.getInt("PB1")));
                            series3.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB2Date")), results.getInt("PB2")));
                            series3.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB3Date")), results.getInt("PB3")));
                            series3.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB4Date")), results.getInt("PB4")));
                            series3.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("Breast50mDate")), results.getInt("Breast50m")));
                            ProgressionGraph.getData().add(series3);
                        }
                    }
                    catch (SQLException resultsexception)       // Catch any error processing the results.
                    {
                        System.out.println("Database result processing error: " + resultsexception.getMessage());
                    }
                }
            }
        }else if(buttonCase == 3){
            PreparedStatement statement = Application.database.newStatement("SELECT PrevFly50m.PB1, PrevFly50m.PB1Date, PrevFly50m.PB2, PrevFly50m.PB2Date, PrevFly50m.PB3, PrevFly50m.PB3Date, PrevFly50m.PB4, PrevFly50m.PB4Date, PBTimes.Fly50m, PBTimes.Fly50mDate FROM PrevFly50m INNER JOIN PBTimes ON PBTimes.PrevFly50mID = PrevFly50m.PrevFlyID WHERE PBTimes.PBID = 1;"); 
            XYChart.Series<String, Number> series4 = new XYChart.Series<String, Number>();
            if (statement != null)      // Assuming the statement correctly initated...
            {
                //statement.setInt(1, Users.getSwimmerID(Users.getActiveUserID()));
                ResultSet results = Application.database.runQuery(statement);       // ...run the query!

                if (results != null)        // If some results are returned from the query...
                {
                    try {                               // ...add each one to the list.
                        while (results.next()) {                                               
                            series4.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB1Date")), results.getInt("PB1")));
                            series4.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB2Date")), results.getInt("PB2")));
                            series4.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB3Date")), results.getInt("PB3")));
                            series4.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB4Date")), results.getInt("PB4")));
                            series4.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("Fly50mDate")), results.getInt("Fly50m")));
                            ProgressionGraph.getData().add(series4);
                        }
                    }
                    catch (SQLException resultsexception)       // Catch any error processing the results.
                    {
                        System.out.println("Database result processing error: " + resultsexception.getMessage());
                    }
                }
            }
        }else if (buttonCase == 4){
            PreparedStatement statement = Application.database.newStatement("SELECT PrevFree100m.PB1, PrevFree100m.PB1Date, PrevFree100m.PB2, PrevFree100m.PB2Date, PrevFree100m.PB3, PrevFree100m.PB3Date, PrevFree100m.PB4, PrevFree100m.PB4Date, PBTimes.Free100m, PBTimes.Free100mDate FROM PrevFree100m INNER JOIN PBTimes ON PBTimes.PrevFree100mID = PrevFree100m.PrevFreeID WHERE PBTimes.PBID = 1;"); 
            XYChart.Series<String, Number> series5 = new XYChart.Series<String, Number>();
            if (statement != null)      // Assuming the statement correctly initated...
            {
                //statement.setInt(1, Users.getSwimmerID(Users.getActiveUserID()));
                ResultSet results = Application.database.runQuery(statement);       // ...run the query!

                if (results != null)        // If some results are returned from the query...
                {
                    try {                               // ...add each one to the list.
                        while (results.next()) {                                               
                            series5.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB1Date")), results.getInt("PB1")));
                            series5.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB2Date")), results.getInt("PB2")));
                            series5.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB3Date")), results.getInt("PB3")));
                            series5.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB4Date")), results.getInt("PB4")));
                            series5.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("Free100mDate")), results.getInt("Free100m")));
                            ProgressionGraph.getData().add(series5);
                        }
                    }
                    catch (SQLException resultsexception)       // Catch any error processing the results.
                    {
                        System.out.println("Database result processing error: " + resultsexception.getMessage());
                    }
                }
            }
        }else if(buttonCase == 5){
            PreparedStatement statement = Application.database.newStatement("SELECT PrevBack100m.PB1, PrevBack100m.PB1Date, PrevBack100m.PB2, PrevBack100m.PB2Date, PrevBack100m.PB3, PrevBack100m.PB3Date, PrevBack100m.PB4, PrevBack100m.PB4Date, PBTimes.Back100m, PBTimes.Back100mDate FROM PrevBack100m INNER JOIN PBTimes ON PBTimes.PrevBack100mID = PrevBack100m.PrevBackID WHERE PBTimes.PBID = 1;"); 
            XYChart.Series<String, Number> series6 = new XYChart.Series<String, Number>();
            if (statement != null)      // Assuming the statement correctly initated...
            {
                //statement.setInt(1, Users.getSwimmerID(Users.getActiveUserID()));
                ResultSet results = Application.database.runQuery(statement);       // ...run the query!

                if (results != null)        // If some results are returned from the query...
                {
                    try {                               // ...add each one to the list.
                        while (results.next()) {                                               
                            series6.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB1Date")), results.getInt("PB1")));
                            series6.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB2Date")), results.getInt("PB2")));
                            series6.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB3Date")), results.getInt("PB3")));
                            series6.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB4Date")), results.getInt("PB4")));
                            series6.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("Back100mDate")), results.getInt("Back100m")));
                            ProgressionGraph.getData().add(series6);
                        }
                    }
                    catch (SQLException resultsexception)       // Catch any error processing the results.
                    {
                        System.out.println("Database result processing error: " + resultsexception.getMessage());
                    }
                }
            }
        }else if(buttonCase == 6){
            PreparedStatement statement = Application.database.newStatement("SELECT PrevBreast100m.PB1, PrevBreast100m.PB1Date, PrevBreast100m.PB2, PrevBreast100m.PB2Date, PrevBreast100m.PB3, PrevBreast100m.PB3Date, PrevBreast100m.PB4, PrevBreast100m.PB4Date, PBTimes.Breast100m, PBTimes.Breast100mDate FROM PrevBreast100m INNER JOIN PBTimes ON PBTimes.PrevBreast100mID = PrevBreast100m.PrevBreastID WHERE PBTimes.PBID = 1;"); 
            XYChart.Series<String, Number> series7 = new XYChart.Series<String, Number>();
            if (statement != null)      // Assuming the statement correctly initated...
            {
                //statement.setInt(1, Users.getSwimmerID(Users.getActiveUserID()));
                ResultSet results = Application.database.runQuery(statement);       // ...run the query!

                if (results != null)        // If some results are returned from the query...
                {
                    try {                               // ...add each one to the list.
                        while (results.next()) {                                               
                            series7.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB1Date")), results.getInt("PB1")));
                            series7.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB2Date")), results.getInt("PB2")));
                            series7.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB3Date")), results.getInt("PB3")));
                            series7.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB4Date")), results.getInt("PB4")));
                            series7.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("Breast100mDate")), results.getInt("Breast100m")));
                            ProgressionGraph.getData().add(series7);
                        }
                    }
                    catch (SQLException resultsexception)       // Catch any error processing the results.
                    {
                        System.out.println("Database result processing error: " + resultsexception.getMessage());
                    }
                }
            }
        }else if(buttonCase == 7){
            PreparedStatement statement = Application.database.newStatement("SELECT PrevFly100m.PB1, PrevFly100m.PB1Date, PrevFly100m.PB2, PrevFly100m.PB2Date, PrevFly100m.PB3, PrevFly100m.PB3Date, PrevFly100m.PB4, PrevFly100m.PB4Date, PBTimes.Fly100m, PBTimes.Fly100mDate FROM PrevFly100m INNER JOIN PBTimes ON PBTimes.PrevFly100mID = PrevFly100m.PrevFlyID WHERE PBTimes.PBID = 1;"); 
            XYChart.Series<String, Number> series8 = new XYChart.Series<String, Number>();
            if (statement != null)      // Assuming the statement correctly initated...
            {
                //statement.setInt(1, Users.getSwimmerID(Users.getActiveUserID()));
                ResultSet results = Application.database.runQuery(statement);       // ...run the query!

                if (results != null)        // If some results are returned from the query...
                {
                    try {                               // ...add each one to the list.
                        while (results.next()) {                                               
                            series8.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB1Date")), results.getInt("PB1")));
                            series8.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB2Date")), results.getInt("PB2")));
                            series8.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB3Date")), results.getInt("PB3")));
                            series8.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("PB4Date")), results.getInt("PB4")));
                            series8.getData().add(new XYChart.Data<String, Number>(Application.stringDate(results.getDate("Fly100mDate")), results.getInt("Fly100m")));
                            ProgressionGraph.getData().add(series8);
                        }
                    }
                    catch (SQLException resultsexception)       // Catch any error processing the results.
                    {
                        System.out.println("Database result processing error: " + resultsexception.getMessage());
                    }
                }

            }
        }
    }

    @FXML void backButtonClicked()
    {
        System.out.println("Back button clicked");
        stage.close();
    }

    @FXML void radioClicked(){
        int buttonCase = 0;
        if (Button50m.isSelected()){
            if(FreeButton.isSelected()){
                buttonCase = 0;
                loadProgressionGraph(buttonCase);
            }else if(BackButton.isSelected()){
                buttonCase = 1;
                loadProgressionGraph(buttonCase);
            }else if(BreastButton.isSelected()){
                buttonCase = 2;
                loadProgressionGraph(buttonCase);
            }else if(FlyButton.isSelected()){
                buttonCase = 3;
                loadProgressionGraph(buttonCase);
            }
        }else if (Button100m.isSelected()){
            if(FreeButton.isSelected()){
                buttonCase = 4;
                loadProgressionGraph(buttonCase);
            }else if(BackButton.isSelected()){
                buttonCase = 5;
                loadProgressionGraph(buttonCase);
            }else if(BreastButton.isSelected()){
                buttonCase = 6;
                loadProgressionGraph(buttonCase);
            }else if(FlyButton.isSelected()){
                buttonCase = 7;
                loadProgressionGraph(buttonCase);
            }
        }else{
            System.out.println("No distance selected");
        }
    }

    @FXML void button100mClicked(){
        if (Button50m.isSelected()){
            FreeButton.setSelected(false);
            BackButton.setSelected(false);
            BreastButton.setSelected(false);
            FlyButton.setSelected(false);
        }
    }
    
    @FXML void button50mClicked(){
        if (Button50m.isSelected()){
            FreeButton.setSelected(false);
            BackButton.setSelected(false);
            BreastButton.setSelected(false);
            FlyButton.setSelected(false);
        }
    }
}

