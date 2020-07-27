package pendzu.sduteam.services.impl;

import org.springframework.stereotype.Service;
import pendzu.sduteam.models.Diary;
import pendzu.sduteam.services.FirebaseStorageService;

@Service
public class DiaryFirebaseServiceExtends extends FirebaseStorageService<Diary> {
}
