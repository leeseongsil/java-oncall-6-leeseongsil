package oncall.utils;

import java.util.LinkedList;
import java.util.List;

public class WorkerQueue {

    private final List<String> elements;

    public WorkerQueue(List<String> elements) {
        if (elements.size() == 0) {
            throw new IllegalArgumentException();
        }

        this.elements = new LinkedList<>(elements);
    }

    public String take() {
        String element = elements.get(0);

        elements.remove(element);
        elements.add(element);
        return element;
    }

    public String take(String previousElement) {
        String element = elements.get(0);
        if (element.equals(previousElement)) {
            element = elements.get(1);
        }

        elements.remove(element);
        elements.add(element);
        return element;
    }
}
