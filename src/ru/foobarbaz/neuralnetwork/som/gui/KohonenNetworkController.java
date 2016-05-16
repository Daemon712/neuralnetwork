package ru.foobarbaz.neuralnetwork.som.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import ru.foobarbaz.neuralnetwork.som.logic.SelfOrganizingMap;
import ru.foobarbaz.neuralnetwork.som.model.Vehicle;

import java.util.List;

public class KohonenNetworkController {
    @FXML private TextField nameField;
    @FXML private TextField costField;
    @FXML private TextField powerField;
    @FXML private TextField volumeField;
    @FXML private TextField massField;
    @FXML private TextField capacityField;

    private SelfOrganizingMap selfOrganizingMap;
    private List<Vehicle> dataSet;

    public void initialize(SelfOrganizingMap selfOrganizingMap, List<Vehicle> dataSet){
        this.selfOrganizingMap = selfOrganizingMap;
        this.dataSet = dataSet;
    }

    public void readNewVehicle(){
        try {
            Vehicle vehicle = new Vehicle();
            vehicle.setName(nameField.getText());
            vehicle.setCost(Integer.parseInt(costField.getText()));
            vehicle.setEnginePower(Float.parseFloat(powerField.getText()));
            vehicle.setEngineVolume(Float.parseFloat(volumeField.getText()));
            vehicle.setMass(Integer.parseInt(massField.getText()));
            vehicle.setCapacity(Integer.parseInt(capacityField.getText()));
            dataSet.add(vehicle);
        } catch (RuntimeException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setHeaderText("Введены некорректные данные!");
            alert.showAndWait();
        }
    }
}
