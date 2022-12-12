package app.analytics;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class SourceAnalytics {
    private SimpleStringProperty name;
    private SimpleStringProperty createdRequests;
    private SimpleStringProperty doneRequests;
    private SimpleStringProperty rejectedRequests;
    private SimpleStringProperty timeInSystem;
    private SimpleStringProperty timeOfWait;
    private SimpleStringProperty timeOfProcess;
    private SimpleStringProperty waitDisp;
    private SimpleStringProperty procDisp;
    private SimpleStringProperty rejectProbability;

    private Integer requestGenerated;
    private Integer requestDone;
    private Integer requestReject;
    private ArrayList<Double> requestTimeOfWait;
    private ArrayList<Double> requestTimeOfProcess;

    public SourceAnalytics(Integer sourceId) {
        name = new SimpleStringProperty("Source" + sourceId.toString());
        createdRequests = new SimpleStringProperty("0");
        doneRequests = new SimpleStringProperty("0");
        rejectedRequests = new SimpleStringProperty("0");
        timeInSystem = new SimpleStringProperty("0.0");
        timeOfWait = new SimpleStringProperty("0.0");
        timeOfProcess = new SimpleStringProperty("0.0");
        waitDisp = new SimpleStringProperty("0.0");
        procDisp = new SimpleStringProperty("0.0");
        rejectProbability = new SimpleStringProperty("0.0");

        requestGenerated = 0;
        requestDone = 0;
        requestReject = 0;
        requestTimeOfWait = new ArrayList<>();
        requestTimeOfProcess = new ArrayList<>();
    }

    public Double getAverageTimeOfWaiting() {
        return getRequestTimeOfWait() / requestGenerated;
    }

    public Double getAverageTimeOfProcess() {
        return getRequestTimeOfProcess() / requestDone;
    }

    public Double getAverageTimeInSystem() {
        return (getRequestTimeOfProcess() + getRequestTimeOfWait()) / requestGenerated;
    }

    public Integer getRequestGenerated() {
        return requestGenerated;
    }

    public Double getRequestRejectProbability() {
        return (double) requestReject / requestGenerated;
    }

    public void setRequestGenerated(Integer requestGenerated) {
        this.requestGenerated = requestGenerated;
    }

    public Integer getRequestDone() {
        return requestDone;
    }

    public void setRequestDone(Integer requestDone) {
        this.requestDone = requestDone;
    }

    public Integer getRequestReject() {
        return requestReject;
    }

    public void setRequestReject(Integer requestReject) {
        this.requestReject = requestReject;
    }

    public Double getRequestTimeOfWait() {
        Double result = 0.0;
        for (Double item : requestTimeOfWait) {
            result += item;
        }
        return result;
    }

    public void increaseRequestTimeOfWait(Double requestTimeOfWait) {
        this.requestTimeOfWait.add(requestTimeOfWait);
    }

    public Double getRequestTimeOfProcess() {
        Double result = 0.0;
        for (Double item : requestTimeOfProcess) {
            result += item;
        }
        return result;
    }

    public void increaseRequestTimeOfProcess(Double requestTimeOfProcess) {
        this.requestTimeOfProcess.add(requestTimeOfProcess);
    }

    public Double getDispersionTimeOfWait() {
        double result = 0.0;
        for (Double item : requestTimeOfWait) {
            result += Math.pow(item - getAverageTimeOfWaiting(), 2);
        }
        return result / requestTimeOfWait.size();
    }

    public Double getDispersionTimeOfProcess() {
        double result = 0.0;
        for (Double item : requestTimeOfProcess) {
            result += Math.pow(item - getAverageTimeOfProcess(), 2);
        }
        return result / requestTimeOfProcess.size();
    }

    public String getName() {
        return name.get();
    }

    public String getCreatedRequests() {
        return getRequestGenerated().toString();
    }

    public String getDoneRequests() {
        return getRequestDone().toString();
    }

    public String getRejectedRequests() {
        return getRequestReject().toString();
    }

    public String getTimeInSystem() {
        return String.format("%.6f", getAverageTimeInSystem());
    }

    public String getTimeOfWait() {
        return String.format("%.6f", getAverageTimeOfWaiting());
    }

    public String getTimeOfProcess() {
        return String.format("%.6f", getAverageTimeOfProcess());
    }

    public String getWaitDisp() {
        return String.format("%.6f", getDispersionTimeOfWait());
    }

    public String getProcDisp() {
        return String.format("%.6f", getDispersionTimeOfProcess());
    }

    public String getRejectProbability() {
        return String.format("%.2f", getRequestRejectProbability() * 100) + "%";
    }
}
