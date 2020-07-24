package pendzu.sduteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pendzu.sduteam.models.Diary;
import pendzu.sduteam.repositories.IDiaryRepository;
import pendzu.sduteam.services.IDiaryService;

import java.util.Optional;

@Service
@PropertySource({"classpath:status.properties"})
public class DiaryServiceImpl implements IDiaryService {

    @Value("${entity.deleted}")
    private int deleteStatus;

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
    public Diary create(Diary diary) {
        return diaryRepository.save(diary);
    }

    @Override
    public Page<Diary> findAll(Pageable pageable) {
        return diaryRepository.findAll(pageable);
    }

  @Override
  public Page<Diary> getDiariesByUserId(Pageable pageable, Long idUser) {
    return diaryRepository.getDiariesByUserId(pageable,idUser);
  }

  @Override
    public void delete(Long id) {
        Optional<Diary> diaryOptional = diaryRepository.findById(id);
        Diary diary = diaryOptional.get();
        if (diary != null) {
            diary.setStatus(deleteStatus);
            diaryRepository.save(diary);
        }
    }

}
