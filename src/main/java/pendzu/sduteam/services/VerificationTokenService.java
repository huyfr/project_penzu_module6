package pendzu.sduteam.services;

import org.springframework.stereotype.Service;
import pendzu.sduteam.models.VerificationToken;

public interface VerificationTokenService {
    VerificationToken findByToken(String token);

    void save(VerificationToken token);
}
