package controllers;

import app.Main;
import app.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SettingsController {
    private Main mainApp;
    private Settings settings;

    @FXML
    private TextField sourceCountField;

    @FXML
    private TextField bufferSizeField;

    @FXML
    private TextField deviceCountField;

    @FXML
    private TextField requestsCountField;

    @FXML
    private TextField alphaField;

    @FXML
    private TextField betaField;

    @FXML
    private TextField lambdaField;

    @FXML
    private Button safeButton;

    @FXML
    void onClickSafeButton(ActionEvent event) {
        try {
            if (sourceCountField.getText().isEmpty() ||
                    bufferSizeField.getText().isEmpty() ||
                    deviceCountField.getText().isEmpty() ||
                    requestsCountField.getText().isEmpty() ||
                    alphaField.getText().isEmpty() ||
                    betaField.getText().isEmpty() ||
                    lambdaField.getText().isEmpty()) {
                return;
            }
            int sourceAmount = Integer.parseInt(sourceCountField.getText());
            int bufferSize = Integer.parseInt(bufferSizeField.getText());
            int deviceAmount = Integer.parseInt(deviceCountField.getText());
            int requestsAmount = Integer.parseInt(requestsCountField.getText());
            double alpha = Double.parseDouble(alphaField.getText());
            double beta = Double.parseDouble(betaField.getText());
            double lambda = Double.parseDouble(lambdaField.getText());

            settings.setSourceAmount(sourceAmount);
            settings.setBufferSize(bufferSize);
            settings.setDeviceAmount(deviceAmount);
            settings.setRequestsAmount(requestsAmount);
            settings.setAlpha(alpha);
            settings.setBeta(beta);
            settings.setLambda(lambda);

            mainApp.openMainMenu();
        } catch (Exception ignored) {
        }
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
        sourceCountField.setText(settings.getSourceAmount().toString());
        bufferSizeField.setText(settings.getBufferSize().toString());
        deviceCountField.setText(settings.getDeviceAmount().toString());
        requestsCountField.setText(settings.getRequestsAmount().toString());
        alphaField.setText(settings.getAlpha().toString());
        betaField.setText(settings.getBeta().toString());
        lambdaField.setText(settings.getLambda().toString());
    }
}
