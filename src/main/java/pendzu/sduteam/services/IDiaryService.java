package pendzu.sduteam.services;

import pendzu.sduteam.models.Diary;

public interface IDiaryService extends GenericService<Diary>{
    Diary create (Diary diary);
}
