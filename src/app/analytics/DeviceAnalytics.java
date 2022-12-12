package app.analytics;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class DeviceAnalytics {
    private SimpleStringProperty name;
    private SimpleStringProperty rate;

    private ArrayList<Double> timeOfWork;
    private Double allTimeOfWork;

    public DeviceAnalytics(Integer deviceId) {
        name = new SimpleStringProperty("Device" + deviceId.toString());
        rate = new SimpleStringProperty();

        timeOfWork = new ArrayList<>();
        allTimeOfWork = 0.0;
    }

    public Double getTimeOfWork() {
        Double result = 0.0;
        for (Double item : timeOfWork) {
            result += item;
        }
        return result;
    }

    public Double getUtilizationRate() {
        return getTimeOfWork() / allTimeOfWork;
    }

    public void increaseTimeOfWork(Double timeOfWork) {
        this.timeOfWork.add(timeOfWork);
    }

    public void setAllTimeOfWork(Double allTimeOfWork) {
        this.allTimeOfWork = allTimeOfWork;
    }

    public String getName() {
        return name.get();
    }

    public String getRate() {
        Double result = getUtilizationRate() * 100;
        if (result > 100) {
            result = 100.0;
        }
        return String.format("%.2f", result) + "%";
    }

}
