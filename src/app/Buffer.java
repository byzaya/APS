package app;

import javafx.util.Pair;

import java.util.ArrayList;

public class Buffer {
    private final int size;
    private int index = 0;
    private ArrayList<Request> requests;
    private int sourceAmount;
    private int prioritySource = 0;

    public Buffer() {
        size = 0;
        requests = new ArrayList<>();
    }

    public Buffer(int size, int sourceAmount) {
        this.size = size;
        this.sourceAmount = sourceAmount;
        this.requests = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            requests.add(null);
        }
    }

    public boolean isEmptyBuffer() {
        for (int i = 0; i < size; i++) {
            if (requests.get(i) != null) {
                return false;
            }
        }
        return true;
    }

    public void setSourceAmount(int sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    //true - if was inserted
    //false - if was rejected from buffer
    public Pair<Boolean, Pair<Integer, Request>> put(Request newRequest) {
        for (int i = 0; i < size; i++) {
            if (requests.get(index) == null) {
                requests.set(index, new Request(newRequest));
                int insertedIndex = index;
                index++;
                index = calculateCorrectIndex(index);
                return new Pair<>(true, new Pair<>(insertedIndex, new Request(newRequest)));
            }
            index++;
            index = calculateCorrectIndex(index);
        }

        Request deletedRequest = new Request(requests.get(index));
        int deleteIndex = index;
        requests.set(index, new Request(newRequest));
        index++;
        index = calculateCorrectIndex(index);
        return new Pair<>(false, new Pair<>(deleteIndex, deletedRequest));
    }

    //return index of buffer and request
    public Pair<Integer, Request> get() {
        if (isEmptyBuffer()) {
            return null;
        }

        while (true) {
            for (int i = 0; i < size; i++) {
                Request tmpRequest = requests.get(i);
                if (tmpRequest != null) {
                    if (tmpRequest.getSourceNumber() == prioritySource) {
                        requests.set(i, null);
                        return new Pair<>(i, new Request(tmpRequest));
                    }
                }
            }
            definePrioritySource();
        }
    }

    private void definePrioritySource() {
        prioritySource = sourceAmount - 1;
        for (int i = 0; i < size; i++) {
            Request tmpRequest = requests.get(i);
            if (tmpRequest != null) {
                if (tmpRequest.getSourceNumber() < prioritySource) {
                    prioritySource = tmpRequest.getSourceNumber();
                }
            }
        }
    }

    private int calculateCorrectIndex(int index) {
        return (index >= size) || (index < 0) ? 0 : index;
    }
}
