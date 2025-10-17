package davidemancini.U5_W2_D5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewDipendenteDTO(
        @NotBlank
        @Size(min = 2, max = 20, message = "Lo username deve avere una lunghezza compresa tra 2 e 20 caratteri")
        String username,
        @NotBlank
        @Size(min = 2, max = 20, message = "Il nome deve avere una lunghezza compresa tra 2 e 20 caratteri")
        String nome,
        @NotBlank
        @Size(min = 2, max = 20, message = "Il cognome deve avere una lunghezza compresa tra 2 e 20 caratteri")
        String cognome,
        @NotBlank
        @Email(message = "L'indirizzo email non è nel formato corretto(example@example.com)")
        String email,
        String avatar
) {
}
