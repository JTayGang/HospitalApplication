package com.example.hospitalapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class DoctorAppointmentController {
    String patientID = "";

    private Scene scene;
    private Parent root;
    Connection conn = null;

    @FXML
    private ListView<MedicalHistory> diagnosisList;
    @FXML
    private TableView<Prescription> prescriptionList;

    @FXML
    private Label nameLbl;
    @FXML
    private Label weightLbl;
    @FXML
    private Label heightLbl;
    @FXML
    private Label bodyTempLbl;
    @FXML
    private Label bloodPressureLbl;

    @FXML
    private TextField diagnosisTxt;
    @FXML
    private TextField prescriptionTxt;
    @FXML
    private TextField quantityTxt;
    @FXML
    private TextArea summaryTxtArea;

    List<MedicalHistory> diagnosisArray = new ArrayList<>();
    List<Prescription> prescriptionArray = new ArrayList<>();

    public void setPatientID(String id) throws SQLException {
        patientID = id;
    }

    public void setName(String name) {nameLbl.setText(name);};

    public void setWeight(double weight) {weightLbl.setText(String.valueOf(weight) + " lbs");}

    public void setHeight(double height) {
        heightLbl.setText(String.valueOf(height) + " cm");
    }

    public void setBodyTemp(double bodyTemp) {
        bodyTempLbl.setText(String.valueOf(bodyTemp) + " F");
    }

    public void setBloodPressure(String bloodPressure) {
        bloodPressureLbl.setText(bloodPressure + " mmHg");
    }


    public void updateDiagnosisList() {
        ObservableList<MedicalHistory> diagnosis = FXCollections.observableArrayList(diagnosisArray);
        diagnosisList.setItems(diagnosis);
    }

    public void addDiagnosis() {
        String uniqueID = UUID.randomUUID().toString();
        String timeStamp = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
        MedicalHistory medicalHistory = new MedicalHistory(uniqueID, diagnosisTxt.getText(), timeStamp);
        diagnosisArray.add(medicalHistory);
        updateDiagnosisList();
    }

    public void addPrescription() {
        Prescription prescription = new Prescription(prescriptionTxt.getText(), Integer.parseInt(quantityTxt.getText()), true);
        prescriptionArray.add(prescription);
    }

    public void updatePrescriptionList() {
        ObservableList<Prescription> prescriptions = FXCollections.observableArrayList(prescriptionArray);
        prescriptionList.setItems(prescriptions);
        updatePrescriptionList();
    }

}
