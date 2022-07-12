package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.CrudUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Student;
import tdm.StudentTDM;

import java.sql.ResultSet;
import java.sql.SQLException;

public class mainViewController {

    public TableView<StudentTDM> studentTbl;
    public TableColumn<StudentTDM,String> sIdClm;
    public TableColumn<StudentTDM,String> sNameClm;
    public TableColumn<StudentTDM,String> sMailClm;
    public TableColumn<StudentTDM,String> sContactClm;
    public TableColumn<StudentTDM,String> sAddressClm;

    public JFXTextField sNameTxt;
    public JFXTextField sMailTxt;
    public JFXTextField sContactTxt;
    public JFXTextField sAddressTxt;
    public JFXButton saveBtn;
    public JFXTextField sidTxt;
    public JFXTextField nicTxt;

    ObservableList<StudentTDM> studentTDMS = FXCollections.observableArrayList();

    public void initialize(){
        studentTbl.setItems(studentTDMS);
    }

    public void saveOrUpdateStudent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        // checking if student exist or not
        Student st = new Student(sidTxt.getText(),nicTxt.getText(),sNameTxt.getText(),sContactTxt.getText(),sAddressTxt.getText(),sMailTxt.getText());
        String statement = "INSERT INTO TABLE Student(?,?,?,?,?,?)";
        if(CrudUtil.execute(statement,st.getSId(),st.getNIC(),st.getSName(),st.getContact(),st.getAddress(),st.getEMail())){
            new Alert(Alert.AlertType.INFORMATION,"Student Saved!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Interrupted!").show();
        }
        loadAllStudents();
    }

    public void removeStudent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        StudentTDM selectedTDM = studentTbl.getSelectionModel().getSelectedItem();
        String statement = "DELETE FROM Student WHERE student_id=?";
        if(CrudUtil.execute(statement,selectedTDM.getSId())){
            new Alert(Alert.AlertType.INFORMATION,"Student Deleted!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Interrupted!").show();
        }
        loadAllStudents();
    }

    public void searchStudent(ActionEvent actionEvent) {
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM student WHERE student_id=? ", sidTxt.getText());
            if (result.next()) {
                sNameTxt.setText(result.getString(2));
                sMailTxt.setText(result.getString(3));
                sContactTxt.setText(result.getString(4));
                sAddressTxt.setText(result.getString(5));
                nicTxt.setText(result.getString(6));


            } else {
                new Alert(Alert.AlertType.WARNING, "Empty set").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudents() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM student");
        studentTDMS.clear();
        while (resultSet.next()) {
            studentTDMS.add(
                    new StudentTDM(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        studentTbl.refresh();
    }

    void clearUI() {
        sAddressTxt.clear();
        sContactTxt.clear();
        sMailTxt.clear();
        sidTxt.clear();
        sNameTxt.clear();
        nicTxt.clear();
    }

}
