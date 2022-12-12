package controllers;


import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    private Main mainApp;

    @FXML
    private Button stepModeButton;

    @FXML
    private Button autoModeButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button exitButton;


    @FXML
    void onClickStepMode(ActionEvent event) {
        mainApp.openStepModeWindow();
    }

    @FXML
    void onClickAutoMode(ActionEvent event) {
        mainApp.openAutoModeWindow();
    }

    @FXML
    void onClickSettings(ActionEvent event) {
        mainApp.openSettingsWindow();
    }

    @FXML
    void onButtonClickExit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}
