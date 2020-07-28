package pendzu.sduteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pendzu.sduteam.models.Comment;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getAllByDiary_Id(Long id);
}
