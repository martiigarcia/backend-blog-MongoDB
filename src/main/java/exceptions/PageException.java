package exceptions;
import java.util.Map;

public class PageException extends RuntimeException {


    private Map<String, String> errors;

    public PageException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> toMap() {
        return Map.copyOf(errors);
    }
}