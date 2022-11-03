package exceptions;
import java.util.Map;

public class PostException  extends RuntimeException {


    private Map<String, String> errors;

    public PostException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> toMap() {
        return Map.copyOf(errors);
    }
}