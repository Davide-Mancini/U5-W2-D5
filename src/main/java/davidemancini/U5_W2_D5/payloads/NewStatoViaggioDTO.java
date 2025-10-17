package davidemancini.U5_W2_D5.payloads;

import davidemancini.U5_W2_D5.entities.Stato;
import jakarta.validation.constraints.NotNull;

public record NewStatoViaggioDTO(
        @NotNull(message = "Definisci il nuovo stato")
        Stato stato
) {
}
