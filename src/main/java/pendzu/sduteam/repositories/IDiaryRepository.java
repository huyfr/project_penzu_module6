package pendzu.sduteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pendzu.sduteam.models.Diary;

//public interface IDiaryRepository extends JpaRepository<Diary, Long> {
//
//}
public interface IDiaryRepository extends PagingAndSortingRepository<Diary, Long> {
//  @Query("select d from Diary d where d.status = 1")
Page<Diary> findAllByUserIdAndStatus(Pageable pageable,Long id,int status);

}
