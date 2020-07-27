package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.models.Comment;
import pendzu.sduteam.models.Diary;
import pendzu.sduteam.services.ICommentService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sdu")
public class CommentRestAPIs {

    @Autowired
    private ICommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<?> getAll(){
        List<Comment> comments = (List<Comment>) commentService.getAll();
        if (comments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getAllCommentByDiaryId(@PathVariable Long id){
        List<Comment> comments = (List<Comment>) commentService.getAllByDiaryId(id);
        if (comments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

//    @GetMapping("/comment/{id}")
//    public ResponseEntity<?> getAllCommentByDiaryId(@PathVariable Long id){
//        commentContent = null;
//        List<Comment> comments = (List<Comment>) commentService.getAllByDiaryId(id);
//        if (comments.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        for (int i = 0; i < comments.size(); i++){
//            String content = comments.get(i).getContent();
//            commentContent.add(content);
//        }
//        return new ResponseEntity<>(commentContent, HttpStatus.OK);
//    }

    @PostMapping("/comment/create")
    public ResponseEntity<?> createComment(@RequestBody Comment comment){
        commentService.createComment(comment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
