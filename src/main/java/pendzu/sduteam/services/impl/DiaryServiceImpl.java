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
    private IDiaryRepository repository;

    @Override
    public Optional<Diary> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Diary> findAll() {
        return repository.findAll();
    }

    @Override
    public Diary save(Diary diary) {
        return repository.save(diary);
    }

    @Override
    public Diary create(Diary diary) {
        return repository.save(diary);
    }

    @Override
    public Page<Diary> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

  @Override
  public Page<Diary> findAllByUserIdAndStatus(Pageable pageable, Long id, int status) {
    return repository.findAllByUserIdAndStatus(pageable, id, status);
  }

  @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void changeStatus(Long id) {
        Optional<Diary> diaryOptional = repository.findById(id);
        Diary diary = diaryOptional.get();
        if (diary != null) {
            diary.setStatus(deleteStatus);
            repository.save(diary);
        }
    }

    @Override
    public Iterable<Diary> findDiariesByUserId(Long user_id) {
        return repository.findDiariesByUserId(user_id);
    }

    @Override
    public Iterable<Diary> findDiariesByTagId(Long tag_id) {
        return repository.findDiariesByTagId(tag_id);
    }

    @Override
    public Iterable<Diary> findDiariesByTitleContaining(String title) {
        return repository.findDiariesByTitleContaining(title);
    }

    @Override
    public Page<Diary> findAllByOrderByCreatedateAsc(Pageable pageable) {
        return repository.findAllByOrderByCreatedateAsc(pageable);
    }

    @Override
    public Page<Diary> findAllByOrderByCreatedateDesc(Pageable pageable) {
        return repository.findAllByOrderByCreatedateDesc(pageable);
    }

    @Override
    public Iterable<Diary> findDiariesByTitleContainingAndUserId(String title, Long user_id) {
        return repository.findDiariesByTitleContainingAndUserId(title, user_id);
    }

    @Override
    public Iterable<Diary> findDiariesByTagIdAndTitleContaining(Long tag_id, String title) {
        return repository.findDiariesByTagIdAndTitleContaining(tag_id, title);
    }
}
