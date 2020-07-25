package pendzu.sduteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pendzu.sduteam.models.Diary;

public interface IDiaryRepository extends PagingAndSortingRepository<Diary, Long> {
    Page<Diary> findAllByUserIdAndStatus(Pageable pageable, Long id, int status);

    Iterable<Diary> findDiariesByUserId(Long user_id);

    Iterable<Diary> findDiariesByTagId(Long tag_id);

    Iterable<Diary> findDiariesByTitleContaining(String title);

    Page<Diary> findAllByOrderByCreatedateAsc(Pageable pageable);

    Page<Diary> findAllByOrderByCreatedateDesc(Pageable pageable);

    Iterable<Diary> findDiariesByTagIdAndTitleContaining(Long tag_id, String title);
    Iterable<Diary> findDiariesByTitleContainingAndUserId(String title, Long user_id);

}
