package pendzu.sduteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pendzu.sduteam.models.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
