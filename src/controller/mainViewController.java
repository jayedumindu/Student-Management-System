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
        Student st = new Student(sidTxt.getText(),nicTxt.getText(),sNameTxt.getText(),sContactTxt.getText(),sAddressTxt.getText(),sMailTxt.getText());
        String statement = "INSERT INTO TABLE Student(?,?,?,?,?,?)";
        if(CrudUtil.execute(statement,st.getSId(),st.getNIC(),st.getSName(),st.getContact(),st.getAddress(),st.getEMail())){
            new Alert(Alert.AlertType.INFORMATION,"Student Saved!").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Interrupted!").show();
        }
    }

    public void removeStudent(ActionEvent actionEvent) {

    }

    public void searchStudent(ActionEvent actionEvent) {
    }

    private void loadAllStudents(){

    }

}
