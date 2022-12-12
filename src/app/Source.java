package app;

import javafx.util.Pair;

import java.util.Random;

public class Source {
    private final double lambda;
    private final int sourceAmount;
    private final double[] times;
    private final int[] requestsNumbers;

    private final Random random = new Random();

    public Source() {
        lambda = 0.0;
        sourceAmount = 0;
        times = new double[sourceAmount];
        requestsNumbers = new int[sourceAmount];
    }

    public Source(Double lambda, Integer sourceAmount) {
        this.lambda = lambda;
        this.sourceAmount = sourceAmount;
        times = new double[sourceAmount];
        requestsNumbers = new int[sourceAmount];

        for (int i = 0; i < sourceAmount; i++) {
            requestsNumbers[i] = 0;
            times[i] = -1.0;
        }
    }

    public Request generate() {
        fillTimes();
        Pair<Integer, Double> nearestRequest = getMinTimeAndSourceNumber();
        removeMinTime(nearestRequest.getValue());
        return new Request(nearestRequest.getValue(), nearestRequest.getKey(), requestsNumbers[nearestRequest.getKey()]);
    }

    private void fillTimes() {
        for (int i = 0; i < sourceAmount; i++) {
            if (times[i] <= 0) {
                requestsNumbers[i]++;
                times[i] = (-1 / lambda) * Math.log(random.nextDouble());
            }
        }
    }

    private Pair<Integer, Double> getMinTimeAndSourceNumber() {
        double minTime = times[0];
        int minSourceNumber = 0;
        for (int i = 0; i < sourceAmount; i++) {
            if (times[i] < minTime) {
                minTime = times[i];
                minSourceNumber = i;
            }
        }
        return new Pair<>(minSourceNumber, minTime);
    }

    private void removeMinTime(double minTime) {
        for (int i = 0; i < sourceAmount; i++) {
            times[i] -= minTime;
        }
    }
}
