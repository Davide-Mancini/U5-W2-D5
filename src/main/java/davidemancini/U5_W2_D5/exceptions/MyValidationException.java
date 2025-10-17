package davidemancini.U5_W2_D5.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class MyValidationException extends RuntimeException {
    private List<String> errorsMex;
    public MyValidationException(List<String> errorsMex) {
        super("ci sono errori di validazione");
        this.errorsMex=errorsMex;
    }
}
