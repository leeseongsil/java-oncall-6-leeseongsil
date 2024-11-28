package oncall;

import oncall.controller.EmergencyWorkController;

public class Application {
    public static void main(String[] args) {
        new EmergencyWorkController().run();
    }
}
