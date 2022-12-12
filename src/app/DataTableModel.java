package app;

import javafx.beans.property.SimpleStringProperty;

public class DataTableModel {
    private SimpleStringProperty name;
    private SimpleStringProperty state;
    private SimpleStringProperty request;

    public DataTableModel(String name, String state, String request) {
        this.name = new SimpleStringProperty(name);
        this.state = new SimpleStringProperty(state);
        this.request = new SimpleStringProperty(request);
    }

    public String getName() {
        return name.get();
    }

    public String getState() {
        return state.get();
    }

    public String getRequest() {
        return request.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setState(String state) {
        this.state = new SimpleStringProperty(state);
    }

    public void setRequest(String request) {
        this.request = new SimpleStringProperty(request);
    }
}