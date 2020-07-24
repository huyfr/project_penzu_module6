package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.models.Diary;
import pendzu.sduteam.services.IDiaryService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/sdu")
@RestController
@CrossOrigin(origins = "*")
public class DiaryRestAPIs {

    @Autowired
    private IDiaryService diaryService;

    @GetMapping("/diaries")
    public ResponseEntity<Iterable<Diary>> getAllDiaryTitle(Diary diary){
        return ResponseEntity.ok(this.diaryService.findAll());
    }

    /*-Tuan*/

    @GetMapping("/diary")
    public ResponseEntity<?> getListDiary() {
        List<Diary> diaries = (List<Diary>) diaryService.findAll();
        if(diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(diaries,HttpStatus.OK);
    }

    @GetMapping("/diary/{id}")
    public ResponseEntity<?> getDiary(@PathVariable Long id) {
        Optional<Diary> diary = diaryService.findById(id);

        if (!diary.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(diary, HttpStatus.OK);
    }

    @PostMapping("/diary")
    public ResponseEntity<?> createDiary(@Valid @RequestBody Diary diary) {

        LocalDateTime now = LocalDateTime.now();
        diary.setCreatedate(now);
        diaryService.save(diary);

        return new ResponseEntity<>(diary, HttpStatus.CREATED);
    }

    @PutMapping("/diary/{id}")
    public ResponseEntity<?> updateDiary(@Valid @RequestBody Diary diary, @PathVariable Long id) {
        Optional<Diary> diary1 = diaryService.findById(id);

        if (!diary1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LocalDateTime now = LocalDateTime.now();

        diary1.get().setUpdatedate(now);
        diary1.get().setTitle(diary.getTitle());
        diary1.get().setDescription(diary.getDescription());
        diary1.get().setContent(diary.getContent());
        diary1.get().setTag(diary.getTag());
        diary1.get().setUser(diary.getUser());
        diary1.get().setAttachment(diary.getAttachment());
        diary1.get().setStatus(diary.getStatus());
        diary1.get().setReaction(diary.getReaction());

        diaryService.save(diary1.get());

        return new ResponseEntity<>(diary1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Diary>> getAllDiary(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Diary> list =  diaryService.findAll(pageable);

        return new ResponseEntity(list, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
        this.diaryService.changeStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
