package controllers;

import app.Main;
import app.Settings;
import app.Simulator;
import app.analytics.DeviceAnalytics;
import app.analytics.SourceAnalytics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AutoModeController {
    private Main mainApp;
    private Settings settings;
    private Simulator simulator;
    private ObservableList<SourceAnalytics> sourceTableList;
    private ObservableList<DeviceAnalytics> deviceTableList;

    @FXML
    private TableView<SourceAnalytics> sourceTable;

    @FXML
    private TableColumn<SourceAnalytics, String> sourceTableColumnName;

    @FXML
    private TableColumn<SourceAnalytics, String> sourceTableColumnCreated;

    @FXML
    private TableColumn<SourceAnalytics, String> sourceTableColumnDone;

    @FXML
    private TableColumn<SourceAnalytics, String> sourceTableColumnReject;

    @FXML
    private TableColumn<SourceAnalytics, String> sourceTableColumnTimeInSystem;

    @FXML
    private TableColumn<SourceAnalytics, String> sourceTableColumnProcTime;

    @FXML
    private TableColumn<SourceAnalytics, String> sourceTableColumnWaitTime;

    @FXML
    private TableColumn<SourceAnalytics, String> sourceTableColumnWaitDisp;

    @FXML
    private TableColumn<SourceAnalytics, String> sourceTableColumnProcDisp;

    @FXML
    private TableColumn<SourceAnalytics, String> sourceTableColumnRejectProbability;

    @FXML
    private TableView<DeviceAnalytics> deviceTable;

    @FXML
    private TableColumn<DeviceAnalytics, String> deviceTableColumnName;

    @FXML
    private TableColumn<DeviceAnalytics, String> deviceTableColumnK;

    @FXML
    private Button exitButton;

    @FXML
    private Button updateButton;

    @FXML
    public void initialize() {
        sourceTableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        sourceTableColumnCreated.setCellValueFactory(new PropertyValueFactory<>("createdRequests"));
        sourceTableColumnDone.setCellValueFactory(new PropertyValueFactory<>("doneRequests"));
        sourceTableColumnReject.setCellValueFactory(new PropertyValueFactory<>("rejectedRequests"));
        sourceTableColumnTimeInSystem.setCellValueFactory(new PropertyValueFactory<>("timeInSystem"));
        sourceTableColumnWaitTime.setCellValueFactory(new PropertyValueFactory<>("timeOfWait"));
        sourceTableColumnProcTime.setCellValueFactory(new PropertyValueFactory<>("timeOfProcess"));
        sourceTableColumnWaitDisp.setCellValueFactory(new PropertyValueFactory<>("waitDisp"));
        sourceTableColumnProcDisp.setCellValueFactory(new PropertyValueFactory<>("procDisp"));
        sourceTableColumnRejectProbability.setCellValueFactory(new PropertyValueFactory<>("rejectProbability"));

        deviceTableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        deviceTableColumnK.setCellValueFactory(new PropertyValueFactory<>("rate"));
    }

    @FXML
    void onClickExitButton(ActionEvent event) {
        mainApp.openMainMenu();
    }

    @FXML
    void onClickUpdateButton(ActionEvent event) {
        simulator = new Simulator(settings);
        simulator.simulate();

        sourceTableList = FXCollections.observableArrayList(simulator.getAnalytics().getSourcesAnalytics());
        sourceTable.setItems(sourceTableList);

        deviceTableList = FXCollections.observableArrayList(simulator.getAnalytics().getDeviceAnalytics());
        deviceTable.setItems(deviceTableList);
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
