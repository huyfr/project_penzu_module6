package pendzu.sduteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pendzu.sduteam.models.Comment;
import pendzu.sduteam.repositories.ICommentRepository;
import pendzu.sduteam.services.ICommentService;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllByDiaryId(Long id) {
        return commentRepository.getAllByDiary_Id(id);
    }
}
