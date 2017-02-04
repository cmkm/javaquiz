
import java.sql.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author James Ballew
 */
public class GenerateRadioButtons extends Application {

    private PreparedStatement preparedAnswers;
    private PreparedStatement preparedQuestion;
    private PreparedStatement preparedCount;
    private Statement stmt;
    private ArrayList<String> answerKey;
    private ArrayList<RadioButton> rbs;
    private TextField temp;
    private ScrollPane scrollPane;
    private VBox vb;
    //TODO create ToggleGroup[totalQuestions] so that each group of buttons can be affected by the listener
    private ToggleGroup group;
    private RadioButton[] rb;

    public void initializeDB() {
        try {
            //initialize new VBox then add generated textfield and radiobutton components based off of query results
            vb = new VBox();
            vb.setFillWidth(true);
            answerKey = new ArrayList<>();
            ResultSet rsetCount = null;
            ResultSet rsetAns = null;

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "phillip", "abc123");
            
            System.out.println("Database connected");
            preparedQuestion = connection.prepareStatement("Select questionId, text from Question where chapterId = ? and sectionId = ?");
            preparedAnswers = connection.prepareStatement("Select text, correct from Answer where chapterId = ? and sectionId = ? "
                    + "and questionId = ?");
            preparedCount = connection.prepareStatement("Select count(*) from Answer where chapterId = ? and sectionId = ? "
                    + "and questionId = ?");
            
            //currently hardcoded, will be filled base on UI selections
            preparedQuestion.setString(1, "1");
            preparedQuestion.setString(2, "1.2");
            preparedAnswers.setString(1, "1");
            preparedAnswers.setString(2, "1.2");
            preparedCount.setString(1, "1");
            preparedCount.setString(2, "1.2");

            ResultSet rset = preparedQuestion.executeQuery();
            
            while (rset.next()) {
                //Generate textfield to hold question text then add to vb
                group = new ToggleGroup();                
                temp = new TextField(String.valueOf(rset.getObject(2)));
                temp.setEditable(false);
                vb.getChildren().add(temp);                
                
                //use qId from first query to perform second query for answers
                //generate radiobuttons for each answer using query results then add to vb
                String qId = String.valueOf(rset.getObject(1));
                preparedAnswers.setString(3, qId);               
                rsetAns = preparedAnswers.executeQuery();
                
                preparedCount.setString(3, qId);
                rsetCount = preparedCount.executeQuery();
                
                rsetCount.next();
                int count = rsetCount.getInt(1);
                rb = new RadioButton[count];
                               
                int i = 0;
                while (rsetAns.next()) {
                    answerKey.add(String.valueOf(rsetAns.getObject(2)));
                    rb[i] = new RadioButton(String.valueOf(rsetAns.getObject(1)));
                    rb[i].setToggleGroup(group);
                    vb.getChildren().add(rb[i]);
                    i++;
                }
                //update database to save selected answer on each toggle (is it efficient? save button?)
                //need to find a way to save answerId and associate with each radiobutton
                group.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
                    if (group.getSelectedToggle() != null) {
//                        System.out.println((group.getSelectedToggle().toString()));
                        String tempSelect = (group.getSelectedToggle().toString());
                        int j = 0;
                        while (tempSelect.charAt(j)!= '\'') {
                            j++;
                        }
                        String selected = tempSelect.substring(j, tempSelect.length());
                        updateSelect(selected);
                    }
                });
            }
            rset.close();
            rsetAns.close();
            rsetCount.close();
            //add the VBox to the scrollPane
            scrollPane.setContent(vb);
//            for (int i = 0; i < rb.length; i++) {
//                System.out.println(rb[i].getText());
//                System.out.println(answerKey.get(i));
//            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
    }

    //currently updates using the text of radiobutton and text of answer table
    //Issues: can not assume answers are unique, must perform query to get chapterid 
    //and sectionid in order to update previous selected answer to = 0
    //TODO update unselected entries
    public void updateSelect(String select) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "phillip", "abc123");
            stmt = connection.createStatement();
            String query = "Update Answer set selected = 1 where text = " + select;
            stmt.executeUpdate(query);
            System.out.println("Answer table updated.");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("answer table not updated.");
        }
    }

    @Override
    public void start(Stage primaryStage) {
        scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(vb);
        Scene scene = new Scene(scrollPane, 670, 400);
        primaryStage.setTitle("GenerateRadeioGroup"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        initializeDB();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
