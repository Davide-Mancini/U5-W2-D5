package davidemancini.U5_W2_D5.payloads;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ErrorListDTO(
        String message,
        LocalDateTime oraErrore,
        List<String>errorsList
) {
}
