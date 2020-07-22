package pendzu.sduteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pendzu.sduteam.models.Diary;
import pendzu.sduteam.repositories.IDiaryRepository;
import pendzu.sduteam.services.IDiaryService;

import java.util.Optional;

@Service
public class DiaryServiceImpl implements IDiaryService {

    @Autowired
    private IDiaryRepository diaryRepository;

    @Override
    public Optional<Diary> findById(Long id) {
        return diaryRepository.findById(id);
    }

    @Override
    public Iterable<Diary> findAll() {
        return diaryRepository.findAll();
    }

    @Override
    public Diary save(Diary diary) {
        return diaryRepository.save(diary);
    }

    @Override
    public void delete(Long id) {
        diaryRepository.deleteById(id);
    }

    @Override
    public Diary create(Diary diary) {
        return diaryRepository.save(diary);
    }
}
