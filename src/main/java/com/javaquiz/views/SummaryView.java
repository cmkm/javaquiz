package com.javaquiz.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.CharmListView;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.javaquiz.Javaquiz;
import static com.javaquiz.Javaquiz.CHAPTER_VIEW;
import static com.javaquiz.Javaquiz.PRIMARY_VIEW;
import com.javaquiz.SummaryCell;
import com.javaquiz.model.Chapters;
import com.javaquiz.model.Question;
import com.javaquiz.model.Questions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
            new PieChart.Data("Incorrect", 33),
            new PieChart.Data("Unaswered", 33),
            new PieChart.Data("Correct", 33));

    public SummaryView(String name) {
        super(name);
        //need to get this to return view to PRIMARY_VIEW. May need to move to DrawerManager.java
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
            appBar.setTitleText("Summary");
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
        if (checkUserData()) {
            System.out.println("Getting user data");
            getUserData2(select);
        } else {
            System.out.println("Populating user data");
            popUserData();
        }
        getPieData(select);
    }

    public void getPieData(String select) {
        try {
            ResultSet rset;
            String getData = "";
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "scott", "tiger");
            Statement stmt = connection.createStatement();
            if (select.equals("All")) {
                getData = "Select COUNT(*) from userData where userId = '" + PrimaryPresenter.user + "' and correct = 1";
                rset = stmt.executeQuery(getData);
                rset.next();
                int correct = rset.getInt(1);
                System.out.println(correct);
                pieChartData.get(2).setPieValue(rset.getInt(1));
                getData = "Select COUNT(*) from userData where userId = '" + PrimaryPresenter.user + "' and correct = 0";
                rset = stmt.executeQuery(getData);
                rset.next();
                int incorrect = rset.getInt(1);
                System.out.println(incorrect);
                pieChartData.get(0).setPieValue(rset.getInt(1));
                getData = "Select COUNT(*) from userData where userId = '" + PrimaryPresenter.user + "'";
                rset = stmt.executeQuery(getData);
                rset.next();
                System.out.println(rset.getInt(1) - correct - incorrect);
                pieChartData.get(1).setPieValue(rset.getInt(1));
            } else {
                select = Chapters.cbList.get(0).getValue().toString();
                String[] getId = select.split(":");
                getData = "Select COUNT(*) from userData where userId = '" + PrimaryPresenter.user + "' and correct = 1"
                        + " and chapterId = " + getId[0];
                rset = stmt.executeQuery(getData);
                rset.next();
                int correct = rset.getInt(1);
                System.out.println(correct);
                pieChartData.get(2).setPieValue(rset.getInt(1));
                getData = "Select COUNT(*) from userData where userId = '" + PrimaryPresenter.user + "' and correct = 0"
                        + " and chapterId = " + getId[0];
                rset = stmt.executeQuery(getData);
                rset.next();
                int incorrect = rset.getInt(1);
                System.out.println(incorrect);
                pieChartData.get(0).setPieValue(rset.getInt(1));
                getData = "Select COUNT(*) from userData where userId = '" + PrimaryPresenter.user + "' and chapterId = " + getId[0];
                rset = stmt.executeQuery(getData);
                rset.next();
                System.out.println(rset.getInt(1) - correct - incorrect);
                pieChartData.get(1).setPieValue(rset.getInt(1));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkUserData() {
        ResultSet rset = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "scott", "tiger");
            Statement stmt = connection.createStatement();
            String getData = "Select * from userData where userId = '" + PrimaryPresenter.user + "'";
            rset = stmt.executeQuery(getData);
            return rset.next();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void popUserData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "scott", "tiger");
            String data = "Insert into userData (userId, chapterId, sectionId, questionId, correct) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(data);
            stmt.setString(1, PrimaryPresenter.user);
            for (int i = 0; i < Questions.questionList.size(); i++) {
                stmt.setString(2, Questions.questionList.get(i).getChapter_id());
                stmt.setString(3, Questions.questionList.get(i).getSection_id());
                stmt.setString(4, Questions.questionList.get(i).getQuestion_id());
                stmt.setString(5, null);
                stmt.executeUpdate();
            }
            System.out.println("User data populated");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getUserData2(String select) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "scott", "tiger");
            Statement stmt = connection.createStatement();
            String getData = "";
            if (select.equals("All")) {
                getData = "Select * from userData where userId = '" + PrimaryPresenter.user + "'";
            } else {
                select = Chapters.cbList.get(0).getValue().toString();
                String[] getId = select.split(":");
                getData = "Select * from userData where userId = '" + PrimaryPresenter.user + "' and chapterId = " + getId[0];
            }

            ResultSet rset = stmt.executeQuery(getData);
            int i = 0;
            while (rset.next()) {
                if (rset.getString(5) != null) {
                    switch (rset.getString(5)) {
                        case "0":
                            Questions.questionList.get(i).setStatus("Incorrect");
                            break;
                        case "1":
                            Questions.questionList.get(i).setStatus("Correct");
                            break;
                    }
                }
                i++;
            }
            Questions.questionList.sort(byQuestion);
            System.out.println("Got user data!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
                        rset.getString(6), rset.getString(5), "Unanswered"));
            }
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
