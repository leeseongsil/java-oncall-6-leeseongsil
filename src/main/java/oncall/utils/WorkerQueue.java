package oncall.utils;

import java.util.List;

public class WorkerQueue {

    private final List<String> elements;
    private int index = 0;
    private String standing = null;

    public WorkerQueue(List<String> elements) {
        if (elements.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.elements = List.copyOf(elements);
    }

    public String take(String previousElement) {
        if (standing != null && !previousElement.equals(standing)) {
            return getTempAndDelete();
        }
        String candidate = nextElement();
        if (candidate.equals(previousElement)) {
            standing = candidate;
            candidate = nextElement();
        }
        return candidate;
    }

    private String getTempAndDelete() {
        String temp = standing;
        standing = null;
        return temp;
    }

    private String nextElement() {
        String temp = elements.get(index);
        index = next(index);
        return temp;
    }

    private int next(int index) {
        return (index + 1) % elements.size();
    }
}
