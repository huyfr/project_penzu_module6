package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pendzu.sduteam.models.Diary;
import pendzu.sduteam.services.impl.DiaryServiceImpl;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ShareDiaryAPIs {
    @Autowired
    private DiaryServiceImpl diaryService;

    @GetMapping("/show-diary")
    public ResponseEntity<?> showDiary(@RequestParam("share") String shareUrl) {
        Iterable<Diary> diaryList = (List<Diary>) diaryService.findByUrl(shareUrl);
        Diary diary = diaryList.iterator().next();
        return new ResponseEntity<>(diary, HttpStatus.OK);
    }
}
