package app.analytics;

import app.Request;
import app.Settings;
import javafx.util.Pair;

import java.util.ArrayList;

public class Analytics {
    private ArrayList<StepModel> steps;
    private ArrayList<SourceAnalytics> sourcesAnalytics;
    private ArrayList<DeviceAnalytics> deviceAnalytics;
    private Double allTimeOfWork;

    public Analytics(Settings settings) {
        steps = new ArrayList<>();
        sourcesAnalytics = new ArrayList<>();
        for (int i = 0; i < settings.getSourceAmount(); i++) {
            sourcesAnalytics.add(new SourceAnalytics(i + 1));
        }
        deviceAnalytics = new ArrayList<>();
        for (int i = 0; i < settings.getDeviceAmount(); i++) {
            deviceAnalytics.add(new DeviceAnalytics(i + 1));
        }
    }

    public void newRequestGenerate(double currentTime, Pair<Integer, Request> data) {
        steps.add(new StepModel(currentTime, Actions.NEW_REQUEST, data));
    }

    public void addRequestToBuffer(double currentTime, Pair<Integer, Request> data) {
        steps.add(new StepModel(currentTime, Actions.ADD_TO_BUFFER, data));
    }

    public void removeRequestFromBuffer(double currentTime, Pair<Integer, Request> data) {
        steps.add(new StepModel(currentTime, Actions.REMOVE_FROM_BUFFER, data));
    }

    public void getRequestFromBuffer(double currentTime, Pair<Integer, Request> data) {
        steps.add(new StepModel(currentTime, Actions.GET_FROM_BUFFER, data));
    }

    public void addRequestToDevice(double currentTime, Pair<Integer, Request> data) {
        steps.add(new StepModel(currentTime, Actions.ADD_TO_DEVICE, data));
    }

    public void removeRequestFromDevice(double currentTime, Pair<Integer, Request> data) {
        steps.add(new StepModel(currentTime, Actions.REMOVE_FROM_DEVICE, data));
    }

    public void setSourceRequestGenerated(Integer sourceId, Integer requestAmount) {
        sourcesAnalytics.get(sourceId).setRequestGenerated(requestAmount);
    }

    public void increaseSourceRequestDone(Integer sourceId) {
        sourcesAnalytics.get(sourceId).setRequestDone(sourcesAnalytics.get(sourceId).getRequestDone() + 1);
    }

    public void increaseSourceRequestRejected(Integer sourceId) {
        sourcesAnalytics.get(sourceId).setRequestReject(sourcesAnalytics.get(sourceId).getRequestReject() + 1);
    }

    public void increaseSourceTimeOfWait(Integer sourceId, Double timeOfWait) {
        sourcesAnalytics.get(sourceId).increaseRequestTimeOfWait(timeOfWait);
    }

    public void increaseSourceTimeOfProcess(Integer sourceId, Double timeOfProcess) {
        sourcesAnalytics.get(sourceId).increaseRequestTimeOfProcess(timeOfProcess);
    }

    public ArrayList<Double> getSourceAvarageTimeOfWaiting() {
        ArrayList<Double> result = new ArrayList<>();
        for (SourceAnalytics item : sourcesAnalytics) {
            result.add(item.getAverageTimeOfWaiting());
        }
        return result;
    }

    public ArrayList<Double> getSourceAvarageTimeOfProcess() {
        ArrayList<Double> result = new ArrayList<>();
        for (SourceAnalytics item : sourcesAnalytics) {
            result.add(item.getAverageTimeOfProcess());
        }
        return result;
    }

    public ArrayList<Double> getSourceAverageTimeInSystem() {
        ArrayList<Double> result = new ArrayList<>();
        for (SourceAnalytics item : sourcesAnalytics) {
            result.add(item.getAverageTimeInSystem());
        }
        return result;
    }

    public ArrayList<Double> getSourceRejectProbability() {
        ArrayList<Double> result = new ArrayList<>();
        for (SourceAnalytics item : sourcesAnalytics) {
            result.add(item.getRequestRejectProbability());
        }
        return result;
    }

    public ArrayList<Double> getSourceDispersionTimeOfWait() {
        ArrayList<Double> result = new ArrayList<>();
        for (SourceAnalytics item : sourcesAnalytics) {
            result.add(item.getDispersionTimeOfWait());
        }
        return result;
    }

    public ArrayList<Double> getSourceDispersionTimeOfProcess() {
        ArrayList<Double> result = new ArrayList<>();
        for (SourceAnalytics item : sourcesAnalytics) {
            result.add(item.getDispersionTimeOfProcess());
        }
        return result;
    }

    public void increaseDeviceTimeOfWork(Integer deviceId, Double timeOfWork) {
        deviceAnalytics.get(deviceId).increaseTimeOfWork(timeOfWork);
    }

    public ArrayList<Double> getUtilizationRate() {
        ArrayList<Double> result = new ArrayList<>();
        for (DeviceAnalytics item : deviceAnalytics) {
            result.add(item.getUtilizationRate());
        }
        return result;
    }

    public void setDeviceAllTimeOfWork() {
        for (DeviceAnalytics item : deviceAnalytics) {
            item.setAllTimeOfWork(allTimeOfWork);
        }
    }

    public ArrayList<SourceAnalytics> getSourcesAnalytics() {
        return sourcesAnalytics;
    }

    public ArrayList<DeviceAnalytics> getDeviceAnalytics() {
        return deviceAnalytics;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }

    public Integer getStepCount() {
        return steps.size();
    }

    public void setAllTimeOfWork(Double allTimeOfWork) {
        this.allTimeOfWork = allTimeOfWork;
    }
}


