package com.javaquiz;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import static com.javaquiz.Javaquiz.QUESTIONS_VIEW;
import com.javaquiz.model.Question;
import com.javaquiz.model.Questions;
import com.javaquiz.model.Section;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;

public class SectionCell extends CharmListCell<Section> {

    Comparator<Question> byQuestion
            = (Question o1, Question o2) -> {
                String id1 = o1.getQuestion_id();
                String id2 = o2.getQuestion_id();
                String[] tokens1 = id1.split(":");
                String[] tokens2 = id2.split(":");
                return Integer.parseInt(tokens1[0]) - Integer.parseInt(tokens2[0]);
            };
    private final ListTile tile;

    // TODO: custom completion graphics
    public SectionCell() {
        this.tile = new ListTile();
        //tile.setPrimaryGraphic(MaterialDesignIcon.ASSIGNMENT.graphic());
        setText(null);
        tile.setOnMouseClicked(e -> {
            String sectionId = super.itemProperty().getValue().getSection_id();
            String chapterId = super.itemProperty().getValue().getChapter_id();
            System.out.println(sectionId + ": " + chapterId);
            populateQuestions(sectionId, chapterId);
            MobileApplication.getInstance().switchView(QUESTIONS_VIEW);
        });
    }

    @Override
    public void updateItem(Section item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            if (item.getName().equals("")) {
                tile.textProperty().setAll(item.getChapter_id() + "." + item.getSection_id());
            } else {
                tile.textProperty().setAll(item.getChapter_id() + "." + item.getSection_id() + ": " + item.getName());
            }
        }
        setGraphic(tile);
    }

    public void populateQuestions(String sectionId, String chapterId) {
        try {
            Questions.questionList.clear();
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javaquiz", "scott", "tiger");
            Statement stmt = connection.createStatement();
            String getSections = "Select * from question where chapterId = " + chapterId + " and sectionId = '" + sectionId + "'";
            ResultSet rset = stmt.executeQuery(getSections);
            while (rset.next()) {
                Questions.questionList.add(new Question(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
                        rset.getString(6), rset.getString(5), "Unanswered"));
            }
            Questions.questionList.sort(byQuestion);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
    }
}
