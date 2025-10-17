package davidemancini.U5_W2_D5.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record NewPrenotazioneDTO(
        @NotNull
        LocalDate data_richiesta,
        @NotBlank
        @Size(min = 1,max = 35)
        String note,
        @NotNull
        UUID dipendente,
        @NotNull
        UUID viaggio
) {
}
