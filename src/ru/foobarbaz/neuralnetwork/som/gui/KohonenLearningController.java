package ru.foobarbaz.neuralnetwork.som.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
import java.io.IOException;
import java.util.List;

public class KohonenLearningController {
    @FXML
    private ComboBox<DistanceFunction> distanceComboBox;
    private DistanceFunction distanceFunction;

    @FXML
    private TextField clustersField;
    private int clusters;

    @FXML
    private TextField erasField;
    private int eras;

    private List<Vehicle> data;

    public void loadDataAndStudy(){
        try {
            readAndValidateFormData();
        } catch (RuntimeException e){
            showErrorAlert("Введены некорректные данные!");
            return;
        }

        try {
            data = loadDataFromFile();
        } catch (JAXBException | RuntimeException e){
            showErrorAlert("Некорректный файл конфигурации!");
            return;
        }

        try {
            showNetworkWindow(createAndStudySom());
        } catch (Exception e){
            e.printStackTrace();
            showErrorAlert("Что-то пошло не так :(");
        }
    }

    private List<Vehicle> loadDataFromFile() throws JAXBException{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        File file = fileChooser.showOpenDialog(null);
        if (file == null || !file.exists() || !file.isFile()){
            throw new RuntimeException();
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(DataSet.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        DataSet dataSet = (DataSet) jaxbUnmarshaller.unmarshal(file);

        List<Vehicle> vehicles = dataSet.getData();
        if (vehicles.isEmpty()){
            throw new RuntimeException();
        }
        return vehicles;
    }

    private SelfOrganizingMap createAndStudySom() {
        int inputParams = data.get(0).getNormalizedData().length;
        SelfOrganizingMap som = new SelfOrganizingMapImpl(distanceFunction, inputParams, clusters);
        double[][] studyingData = ArraysHelper.to2DArray(data.stream().map(Vehicle::getNormalizedData));
        som.study(studyingData, eras);
        return som;
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

    private void showNetworkWindow(SelfOrganizingMap som) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/KohonenNetwork.fxml"));
        GridPane root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        KohonenNetworkController controller = loader.getController();
        controller.initialize(som, data);
        stage.show();
    }

    private void showErrorAlert(String header){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
