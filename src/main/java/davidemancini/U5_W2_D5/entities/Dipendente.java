package davidemancini.U5_W2_D5.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue
    //IMPEDISCO LA CREAZIONE DEL SETTER DI LOMBOK PER L'ID
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String avatar;

    //COSTRUTTORE

    public Dipendente(String username, String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.avatar = "https://ui-avatars.com/api/?name="+nome+"+"+cognome ;
        //METTO UN AVATAR DI DEFAULT CHE SARà MODIFICABILE.
    }
}
