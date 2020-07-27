package pendzu.sduteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pendzu.sduteam.models.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
//    List<Comment> findAllByDiaryId(Long id);

    List<Comment> getAllByDiary_Id(Long id);
}
