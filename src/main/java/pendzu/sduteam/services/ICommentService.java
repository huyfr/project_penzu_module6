package pendzu.sduteam.services;

import pendzu.sduteam.models.Comment;

import java.util.List;
import java.util.Optional;

public interface ICommentService {
    List<Comment> getAll();

    List<Comment> getAllByDiaryId(Long id);

    Comment createComment(Comment comment);
}
