package oncall.view;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR] ";

    public void printErrorMessage(Exception exception) {
        print(ERROR_PREFIX.concat(exception.getMessage()));
        printBlankLine();
    }

    private void print(String message) {
        System.out.print(message);
    }

    private void printBlankLine() {
        System.out.println();
    }
}
