//package pendzu.sduteam.formatter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Component;
//import pendzu.sduteam.models.Comment;
//import pendzu.sduteam.services.impl.CommentService;
//
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Optional;
//
//@Component
//public class CommentFormatter implements Formatter<Comment> {
//
//    CommentService commentService;
//
//    @Autowired
//    public CommentFormatter(CommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    @Override
//    public Comment parse(String text, Locale locale) throws ParseException {
//        Optional<Comment> optionalComment = commentService.getAllByDiaryId(Long.parseLong(text));
//        Comment comment = optionalComment.get();
//        return comment;
//    }
//
//    @Override
//    public String print(Comment object, Locale locale) {
//        return null;
//    }
//}
