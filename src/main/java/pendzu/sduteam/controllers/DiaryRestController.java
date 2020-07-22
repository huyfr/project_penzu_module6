package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.models.Diary;
import pendzu.sduteam.services.IDiaryService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/sdu")
@RestController
@CrossOrigin(origins = "*")
public class DiaryRestController {

    @Autowired
    private IDiaryService diaryService;

    @GetMapping()
    public ResponseEntity<Iterable<Diary>> getAllDiary(Diary diary){
        return ResponseEntity.ok(this.diaryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Diary>> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(this.diaryService.findById(id));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Diary> create(@RequestBody Diary diary){
        return ResponseEntity.ok(this.diaryService.create(diary));
    }

    @PutMapping
    public ResponseEntity<Diary> update(@RequestBody Diary diary){
        return ResponseEntity.ok(this.diaryService.save(diary));
    }

    @DeleteMapping("/dairy/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id){
        this.diaryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
