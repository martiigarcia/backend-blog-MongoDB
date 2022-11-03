package exceptions;

import java.util.Map;

public class ModeloException  extends RuntimeException {


    private Map<String, String> errors;

    public ModeloException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> toMap() {
        return Map.copyOf(errors);
    }
}