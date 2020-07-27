package pendzu.sduteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pendzu.sduteam.models.VerificationToken;
import pendzu.sduteam.repositories.VerificationTokenRepository;
import pendzu.sduteam.services.VerificationTokenService;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    public void save(VerificationToken token) {
        verificationTokenRepository.save(token);
    }
}
