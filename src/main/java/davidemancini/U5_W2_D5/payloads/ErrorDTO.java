package davidemancini.U5_W2_D5.payloads;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ErrorDTO(
        String message,
        LocalDateTime oraErrore
) {
}
