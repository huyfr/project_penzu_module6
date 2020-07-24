package pendzu.sduteam.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pendzu.sduteam.models.Diary;

public interface IDiaryService extends GenericService<Diary> {
    Diary create(Diary diary);

    Page<Diary> findAll(Pageable pageable);

    Page<Diary> getDiariesByUserId(Pageable pageable, Long idUser);

    void changeStatus(Long id);
}
