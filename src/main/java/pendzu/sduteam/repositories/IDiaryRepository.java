package pendzu.sduteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pendzu.sduteam.models.Diary;

public interface IDiaryRepository extends JpaRepository<Diary, Long> {
}
