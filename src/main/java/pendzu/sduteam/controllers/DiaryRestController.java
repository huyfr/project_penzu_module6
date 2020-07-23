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

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/sdu")
@RestController
@CrossOrigin(origins = "*")
public class DiaryRestController {

  @Autowired
  private IDiaryService diaryService;

  //    @GetMapping("/diaries")
//    public ResponseEntity<Iterable<Diary>> getAllDiary(Diary diary){
//        return ResponseEntity.ok(this.diaryService.findAll());
//    }

  @GetMapping("/diary/{id}")
  public ResponseEntity<Optional<Diary>> getById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(this.diaryService.findById(id));
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/create")
  public ResponseEntity<Diary> create(@RequestBody Diary diary) {
    return ResponseEntity.ok(this.diaryService.create(diary));
  }

  @PutMapping("/edit")
  public ResponseEntity<Diary> update(@RequestBody Diary diary) {
    return ResponseEntity.ok(this.diaryService.save(diary));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> remove(@PathVariable Long id) {
    this.diaryService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Diary>> getAllDiary(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "2") int size
  ) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Diary> list =  diaryService.findAll(pageable);

    return new ResponseEntity(list, new HttpHeaders(), HttpStatus.OK);
  }
}
