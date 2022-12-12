package app.analytics;

import app.Request;
import javafx.util.Pair;

public class StepModel {
    private final double currentTime;
    private Actions action;
    private Pair<Integer, Request> data;

    public StepModel(double currentTime, Actions action, Pair<Integer, Request> data) {
        this.currentTime = currentTime;
        this.action = action;
        this.data = data;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public Actions getAction() {
        return action;
    }

    public Pair<Integer, Request> getData() {
        return data;
    }
}
