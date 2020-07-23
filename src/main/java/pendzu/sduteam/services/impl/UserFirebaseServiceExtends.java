package pendzu.sduteam.services.impl;

import org.springframework.stereotype.Service;
import pendzu.sduteam.models.User;
import pendzu.sduteam.services.FirebaseStorageService;

@Service
public class UserFirebaseServiceExtends extends FirebaseStorageService<User> {
}
