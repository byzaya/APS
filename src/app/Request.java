package app;

public class Request {
    private double time;
    private final int sourceNumber;
    private final int requestNumber;

    public Request() {
        time = 0.0;
        sourceNumber = 0;
        requestNumber = 0;
    }

    public Request(Request request) {
        this.time = request.time;
        this.sourceNumber = request.sourceNumber;
        this.requestNumber = request.requestNumber;
    }

    public Request(double time, int sourceNumber, int requestNumber) {
        this.time = time;
        this.sourceNumber = sourceNumber;
        this.requestNumber = requestNumber;
    }

    public Double getTime() {
        return time;
    }

    public Integer getSourceNumber() {
        return sourceNumber;
    }

    public Integer getRequestNumber() {
        return requestNumber;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
