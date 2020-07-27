package pendzu.sduteam.services;

import org.springframework.beans.factory.annotation.Autowired;
import pendzu.sduteam.models.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    List<Comment> getAll();

    List<Comment> getAllByDiaryId(Long id);

    Comment createComment(Comment comment);
}
