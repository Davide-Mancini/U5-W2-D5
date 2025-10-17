package davidemancini.U5_W2_D5.payloads;

import davidemancini.U5_W2_D5.entities.Stato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewViaggioDTO(
        @NotBlank
        String destinazione,
        @NotNull
        LocalDate data_viaggio,
        @NotBlank
        Stato stato
) {
}
