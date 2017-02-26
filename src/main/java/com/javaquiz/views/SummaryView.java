package com.javaquiz.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.Javaquiz;
import static com.javaquiz.Javaquiz.CHAPTER_VIEW;
import com.javaquiz.SummaryCell;
import com.javaquiz.model.Chapters;
import com.javaquiz.model.Question;
import com.javaquiz.model.Questions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

public class SummaryView extends View {

    Comparator<Question> byQuestion
            = (Question o1, Question o2) -> {
                String id1 = o1.getQuestion_id();
                String id2 = o2.getQuestion_id();
                String[] tokens1 = id1.split(":");
                String[] tokens2 = id2.split(":");
                return Integer.parseInt(tokens1[0]) - Integer.parseInt(tokens2[0]);
            };
    public static CharmListView<Question, Integer> qView;
    public static ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Incorrect", 54),
            new PieChart.Data("Unaswered", 123),
            new PieChart.Data("Correct", 89));

    public SummaryView(String name) {
        super(name);
        VBox vb = new VBox();
        Chapters.cbList.get(0).setOnAction(e -> pullData());
        pullData();
        vb.getChildren().add(Chapters.cbList.get(0));
        PieChart pc = new PieChart(pieChartData);
        pc.autosize();
        vb.getChildren().add(pc);
        qView = new CharmListView<>(Questions.questionList);
        qView.setCellFactory(p -> new SummaryCell());
        qView.autosize();
        vb.getChildren().add(qView);
        setCenter(vb);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                -> MobileApplication.getInstance().showLayer(Javaquiz.MENU_LAYER)));
        appBar.getActionItems().add(MaterialDesignIcon.ARROW_BACK.button(e
                -> MobileApplication.getInstance().switchView(CHAPTER_VIEW)));
        try {
            appBar.setTitleText(QuestionView.questionListView.getSelectedItem().getQuestion_id());
        } catch (Exception ex) {
            appBar.setTitleText("Summary");
        }
    }

    private void pullData() {
        String select = Chapters.cbList.get(0).getValue().toString();
        if (select.equals("All")) {            
            initializeDB(select);
        } else {
            select = Chapters.cbList.get(0).getValue().toString();
            String[] getId = select.split(":");
            System.out.println(getId[0]);
            initializeDB(getId[0]);
        }
        
        System.out.println(select);
        pieChartData.get(0).setPieValue(150);
    }

    public void getQuestions(String select) {
    }

    public void initializeDB(String select) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "scott", "tiger");
            Statement stmt = connection.createStatement();
            String getQuestions = "";
            if (select.equals("All")) {
                getQuestions = "Select * from question";
            } else {
                getQuestions = "Select * from question where chapterId = " + select;
            }
            ResultSet rset = stmt.executeQuery(getQuestions);
            Questions.questionList.clear();
            while (rset.next()) {
                Questions.questionList.add(new Question(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
                        rset.getString(6), rset.getString(5)));
            }
            Questions.questionList.sort(byQuestion);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
    }

    public View getView(String name) {
        return new SummaryView(name);
    }

    public View getView() {
        return new SummaryView("Summary");
    }
}
