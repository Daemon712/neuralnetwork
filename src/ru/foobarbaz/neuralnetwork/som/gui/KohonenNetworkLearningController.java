package ru.foobarbaz.neuralnetwork.som.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import ru.foobarbaz.neuralnetwork.common.ArraysHelper;
import ru.foobarbaz.neuralnetwork.function.distance.DistanceFunction;
import ru.foobarbaz.neuralnetwork.som.logic.SelfOrganizingMap;
import ru.foobarbaz.neuralnetwork.som.logic.SelfOrganizingMapImpl;
import ru.foobarbaz.neuralnetwork.som.model.DataSet;
import ru.foobarbaz.neuralnetwork.som.model.Vehicle;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class KohonenNetworkLearningController {
    @FXML
    private ComboBox<DistanceFunction> distanceComboBox;
    private DistanceFunction distanceFunction;

    @FXML
    private TextField clustersField;
    private int clusters;

    @FXML
    private TextField erasField;
    private int eras;

    private DataSet dataSet;

    public void loadDataAndStudy(){
        try {
            readAndValidateFormData();
        } catch (RuntimeException e){
            showErrorAlert("Введены некорректные данные!");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file == null || !file.exists() || !file.isFile()){
            showErrorAlert("Файл конфигурации не выбран!");
            return;
        }

        try {
            dataSet = readDataSet(file);
        } catch (JAXBException ex){
            showErrorAlert("Некорректный файл конфигурации!");
            return;
        }

        List<Vehicle> vehicles = dataSet.getData();
        if (vehicles.isEmpty()){
            showErrorAlert("Некорректный файл конфигурации!");
            return;
        }
        int inputParams = vehicles.get(0).getNormalizedData().length;
        SelfOrganizingMap som = new SelfOrganizingMapImpl(distanceFunction, inputParams, clusters);
        double[][] studyingData = ArraysHelper.to2DArray(vehicles.stream().map(Vehicle::getNormalizedData));
        som.study(studyingData, eras);
    }

    private DataSet readDataSet(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(DataSet.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (DataSet) jaxbUnmarshaller.unmarshal(file);
    }

    private void readAndValidateFormData(){
        if ((distanceFunction = distanceComboBox.getValue()) == null) {
            throw new RuntimeException();
        }
        clusters = Integer.parseInt(clustersField.getText());
        eras = Integer.parseInt(erasField.getText());
        if (clusters <= 0 || eras <= 0) {
            throw new RuntimeException();
        }
    }

    private void showErrorAlert(String header){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
