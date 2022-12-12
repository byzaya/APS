package app;

public class Settings {
    private Integer sourceAmount;
    private Integer bufferSize;
    private Integer deviceAmount;
    private Integer requestsAmount;

    private Double alpha;
    private Double beta;
    private Double lambda;

    public Settings() {
        sourceAmount = 10;
        bufferSize = 10;
        deviceAmount = 3;
        requestsAmount = 10000;
        alpha = 1.0;
        beta = 1.1;
        lambda = 0.275;
    }

    public Settings(Integer sourceAmount, Integer bufferSize, Integer deviceAmount, Integer requestsAmount, Double alpha, Double beta, Double lambda) {
        this.sourceAmount = sourceAmount;
        this.bufferSize = bufferSize;
        this.deviceAmount = deviceAmount;
        this.requestsAmount = requestsAmount;
        this.alpha = alpha;
        this.beta = beta;
        this.lambda = lambda;
    }

    public void setSourceAmount(Integer sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public void setBufferSize(Integer bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void setDeviceAmount(Integer deviceAmount) {
        this.deviceAmount = deviceAmount;
    }

    public void setRequestsAmount(Integer requestsAmount) {
        this.requestsAmount = requestsAmount;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public void setLambda(Double lambda) {
        this.lambda = lambda;
    }

    public Integer getSourceAmount() {
        return sourceAmount;
    }

    public Integer getBufferSize() {
        return bufferSize;
    }

    public Integer getDeviceAmount() {
        return deviceAmount;
    }

    public Integer getRequestsAmount() {
        return requestsAmount;
    }

    public Double getAlpha() {
        return alpha;
    }

    public Double getBeta() {
        return beta;
    }

    public Double getLambda() {
        return lambda;
    }
}
