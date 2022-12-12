package controllers;

import app.*;
import app.analytics.StepModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.util.ArrayList;

public class StepModeController {
    private Main mainApp;
    private Settings settings;
    private Simulator simulator;
    private ObservableList<DataTableModel> sourceTableList ,bufferTableList, deviceTableList;
    private ArrayList<StepModel> steps;
    private Integer currentStep, allStepCount, currentRequestNumber;
    private Double currentTime;

    @FXML
    private TableView<DataTableModel> sourceTable;

    @FXML
    private TableColumn<DataTableModel, String> sourceColumnName;


    @FXML
    private TableColumn<DataTableModel, String> sourceColumnRequest;

    @FXML
    private TableView<DataTableModel> bufferTable;

    @FXML
    private TableColumn<DataTableModel, String> bufferColumnName;

    @FXML
    private TableColumn<DataTableModel, String> bufferColumnState;

    @FXML
    private TableColumn<DataTableModel, String> bufferColumnRequest;

    @FXML
    private TableView<DataTableModel> deviceTable;

    @FXML
    private TableColumn<DataTableModel, String> deviceColumnName;

    @FXML
    private TableColumn<DataTableModel, String> deviceColumnState;

    @FXML
    private TableColumn<DataTableModel, String> deviceColumnRequest;


    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button exitButton;

    @FXML
    private Button updateButton;

    @FXML
    private Label timeLabel;

    @FXML
    private Label stepLabel;

    @FXML
    private Label allStepsLabel;

    @FXML
    private TextField goToStepField;

    @FXML
    private Button goToStepButton;

    @FXML
    private Button prevButton;

    @FXML
    private Button nextButton;

    @FXML
    public void initialize() {

        sourceColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
//        sourceColumnState.setCellValueFactory(new PropertyValueFactory<>("State"));
        sourceColumnRequest.setCellValueFactory(new PropertyValueFactory<>("Request"));

        bufferColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        bufferColumnState.setCellValueFactory(new PropertyValueFactory<>("State"));
        bufferColumnRequest.setCellValueFactory(new PropertyValueFactory<>("Request"));

        deviceColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        deviceColumnState.setCellValueFactory(new PropertyValueFactory<>("State"));
        deviceColumnRequest.setCellValueFactory(new PropertyValueFactory<>("Request"));

        currentStep = 0;
        allStepCount = 0;
        currentTime = 0.0;
        currentRequestNumber = 0;
    }

    @FXML
    void onClickNextButton(ActionEvent event) {
        if (currentStep >= allStepCount) {
            return;
        }

        currentStep++;
        stepLabel.setText(currentStep.toString());

        commitStep(currentStep);
    }

    @FXML
    void onClickPrevButton(ActionEvent event) {
        currentStep--;
        if (currentStep < 0) {
            currentStep++;
            return;
        }

        int tmpStep = currentStep;
        stepLabel.setText(currentStep.toString());

        refresh();
        for (int i = 0; i < tmpStep; i++) {
            onClickNextButton(event);
        }
    }

    @FXML
    void onClickExitButton(ActionEvent event) {
        mainApp.openMainMenu();
    }

    @FXML
    void onClickUpdateButton(ActionEvent event) {
        simulator = new Simulator(settings);
        simulator.simulate();
        steps = new ArrayList<>();
        steps.add(null);
        steps.addAll(simulator.getAnalytics().getSteps());

        goToStepButton.setDisable(false);
        nextButton.setDisable(false);
        prevButton.setDisable(false);

        allStepCount = steps.size() - 1;
        allStepsLabel.setText(allStepCount.toString());

        refresh();
    }

    @FXML
    void onClickGoToStepButton(ActionEvent event) {
        String tmpStepStr = goToStepField.getText();
        int tmpStep;
        try {
            tmpStep = Integer.parseInt(tmpStepStr);
        } catch (Exception e) {
            goToStepField.requestFocus();
            return;
        }
        if (tmpStep < 0 || tmpStep > allStepCount) {
            goToStepField.requestFocus();
            return;
        }

        refresh();
        for (int i = 0; i < tmpStep; i++) {
            onClickNextButton(event);
        }
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
        simulator = new Simulator(settings);
    }

    private void commitStep(Integer currentStep) {
        StepModel model = steps.get(currentStep);
        Pair<Integer, Request> data = model.getData();
        currentTime = model.getCurrentTime();
        timeLabel.setText(currentTime.toString());
        String message = switch (model.getAction()) {
            case NEW_REQUEST -> {
                currentRequestNumber++;
                sourceTableList.set(data.getKey(), new DataTableModel("Source" + (data.getKey() + 1), "Busy", (data.getValue().getSourceNumber() + 1) + "." + currentRequestNumber));
                yield "Создана заявка " + (data.getKey() + 1) + "." + currentRequestNumber + " Источником " + (data.getValue().getSourceNumber() + 1);
            }
            case ADD_TO_BUFFER -> {
                bufferTableList.set(data.getKey(), new DataTableModel("Buffer" + (data.getKey() + 1), "Busy", (data.getValue().getSourceNumber() + 1) + "." + currentRequestNumber));
                yield "Заявка " + (data.getValue().getSourceNumber() + 1) + "." + currentRequestNumber + " была добавлена в Buffer" + (data.getKey() + 1);
            }
            case REMOVE_FROM_BUFFER -> {
                bufferTableList.set(data.getKey(), new DataTableModel("Buffer" + (data.getKey() + 1), "Free", "-"));
                yield "Заявка " + (data.getValue().getSourceNumber() + 1) + "." + currentRequestNumber + " была отклонена Buffer" + (data.getKey() + 1);
            }
            case GET_FROM_BUFFER -> {
                bufferTableList.set(data.getKey(), new DataTableModel("Buffer" + (data.getKey() + 1), "Free", "-"));
                yield "Заявка " + (data.getValue().getSourceNumber() + 1) + "." + currentRequestNumber + " была выбрана из Buffer" + (data.getKey() + 1);
            }
            case ADD_TO_DEVICE -> {
                deviceTableList.set(data.getKey(), new DataTableModel("Device" + (data.getKey() + 1), "Busy", (data.getValue().getSourceNumber() + 1) + "." + currentRequestNumber));
                yield "Заявка " + (data.getValue().getSourceNumber() + 1) + "." + currentRequestNumber + " была добавлена в Device" + (data.getKey() + 1);
            }
            case REMOVE_FROM_DEVICE -> {
                deviceTableList.set(data.getKey(), new DataTableModel("Device" + (data.getKey() + 1), "Free", "-"));
                yield "Заявка " + (data.getValue().getSourceNumber() + 1) + "." + currentRequestNumber + " была обработана Device" + (data.getKey() + 1);
            }
        };
        descriptionTextArea.setText(message);
    }

    private void refresh() {
        currentTime = 0.0;
        timeLabel.setText(currentTime.toString());

        descriptionTextArea.clear();

        currentRequestNumber = 0;

        currentStep = 0;
        stepLabel.setText(currentStep.toString());

        sourceTableList = FXCollections.observableArrayList();
        for (int i = 1; i <= settings.getSourceAmount(); i++) {
            sourceTableList.add(new DataTableModel("Source"+i, "Free","-"));
        }
        sourceTable.setItems(sourceTableList);

        bufferTableList = FXCollections.observableArrayList();
        for (int i = 1; i <= settings.getBufferSize(); i++) {
            bufferTableList.add(new DataTableModel("Buffer" + i, "Free", "-"));
        }
        bufferTable.setItems(bufferTableList);

        deviceTableList = FXCollections.observableArrayList();
        for (int i = 1; i <= settings.getDeviceAmount(); i++) {
            deviceTableList.add(new DataTableModel("Device" + i, "Free", "-"));
        }
        deviceTable.setItems(deviceTableList);
    }
}
