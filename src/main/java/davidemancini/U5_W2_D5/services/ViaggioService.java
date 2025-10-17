package davidemancini.U5_W2_D5.services;

import davidemancini.U5_W2_D5.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {
    @Autowired
    private ViaggiRepository viaggiRepository;
}
