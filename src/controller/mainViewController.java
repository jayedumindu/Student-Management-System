package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.CrudUtil;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Student;

import java.sql.SQLException;

public class mainViewController {

    public TableView studentTbl;
    public TableColumn sIdClm;
    public TableColumn sNameClm;
    public TableColumn sMailClm;
    public TableColumn sContactClm;
    public TableColumn sAddressClm;

    public JFXTextField sNameTxt;
    public JFXTextField sMailTxt;
    public JFXTextField sContactTxt;
    public JFXTextField sAddressTxt;
    public JFXButton saveBtn;
    public JFXTextField sidTxt;

    public void saveOrUpdateStudent(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Student st = new Student(sidTxt.getText(),sNameTxt.getText(),sContactTxt.getText(),sAddressTxt.getText(),sMailTxt.getText());
        String statement = "INSERT INTO TABLE Student(?,?,?,?,?)";
        if(CrudUtil.execute(statement,st.getSId(),st.getSName(),st.getContact(),st.getAddress(),st.getEMail())){
            new Alert(Alert.AlertType.INFORMATION,"Student Saved!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Interrupted!").show();
        }
    }

    public void removeStudent(ActionEvent actionEvent) {

    }

    public void searchStudent(ActionEvent actionEvent) {
    }
}
