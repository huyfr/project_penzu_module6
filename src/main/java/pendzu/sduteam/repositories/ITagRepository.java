package pendzu.sduteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pendzu.sduteam.models.Tag;

public interface ITagRepository extends JpaRepository<Tag,Long> {
    Iterable<Tag> findTagsByNameContaining(String tag_name);
}
