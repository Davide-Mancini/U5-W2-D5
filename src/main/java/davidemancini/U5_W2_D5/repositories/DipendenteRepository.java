package davidemancini.U5_W2_D5.repositories;

import davidemancini.U5_W2_D5.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, UUID> {
    //QUERY DERIVATA CHE RICERCA PER EMIAL
    Optional<Dipendente> findByEmail(String email);
}
