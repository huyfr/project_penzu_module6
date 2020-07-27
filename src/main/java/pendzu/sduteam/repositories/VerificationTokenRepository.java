package pendzu.sduteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pendzu.sduteam.models.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
