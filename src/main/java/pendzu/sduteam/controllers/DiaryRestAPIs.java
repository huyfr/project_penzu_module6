package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.message.request.SearchDiaryByTagAndTitle;
import pendzu.sduteam.message.request.SearchDiaryByTitle;
import pendzu.sduteam.message.request.SearchDiaryByTitleAndUserId;
import pendzu.sduteam.models.Diary;
import pendzu.sduteam.services.IDiaryService;
import pendzu.sduteam.services.impl.DiaryFirebaseServiceExtends;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/sdu")
@RestController
@CrossOrigin(origins = "*")
@PropertySource({"classpath:status.properties"})
public class DiaryRestAPIs {

    @Value("${entity.exist}")
    private int activeDiaryStatus;

    @Value("${entity.greater.than.zero}")
    private int allPostAvailable;

    @Autowired
    private IDiaryService diaryService;

    @Autowired
    private DiaryFirebaseServiceExtends diaryFirebaseServiceExtends;

    @GetMapping("/diary")
    public ResponseEntity<?> getListDiary() {
        List<Diary> diaries = (List<Diary>) diaryService.findAll();
        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(diaries, HttpStatus.OK);
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

        LocalDateTime localDateTime = LocalDateTime.now();

        diary.setCreatedate(localDateTime);
        diary.setUpdatedate(localDateTime);
        String tempContent = diary.getContent();
        String contentReplaceImage = tempContent.replace("<img", "<img class=\"img-fluid\"");
        String contentReplaceFinal = contentReplaceImage.replace("<iframe", "<iframe class=\"embed-responsive embed-responsive-16by9\"");
        diary.setContent(contentReplaceFinal);
        diaryService.save(diary);

        return new ResponseEntity<>(diary, HttpStatus.CREATED);
    }

    @PutMapping("/diary/{id}")
    public ResponseEntity<?> updateDiary(@Valid @RequestBody Diary diary, @PathVariable Long id) {
        Optional<Diary> currentDiary = diaryService.findById(id);

        if (!currentDiary.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            LocalDateTime localDateTime = LocalDateTime.now();

            diary.setUpdatedate(localDateTime);
            String tempContent = diary.getContent();
            String contentReplaceImage = tempContent.replace("<img", "<img class=\"img-fluid\"");
            String contentReplaceFinal = contentReplaceImage.replace("<iframe", "<iframe class=\"embed-responsive embed-responsive-16by9\"");
            diary.setContent(contentReplaceFinal);
            diaryService.save(diary);
            return new ResponseEntity<>(currentDiary, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/diary/user/{idUser}")
    public ResponseEntity<List<Diary>> listDiaryByUser(
            @PathVariable("idUser") long idUser,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Diary> listByUser = diaryService.findDiariesByUserIdAndStatusAfter(pageable, idUser, allPostAvailable);
        if (listByUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(listByUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Diary>> getAllDiary(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Diary> list = diaryService.findAll(pageable);

        return new ResponseEntity(list, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/diary/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        this.diaryService.changeStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/diary/pagination/ASC")
    public ResponseEntity<?> getListDiaryAndPaginationASC(@PageableDefault(value = 2) Pageable pageable) {
        Page<Diary> diaries = diaryService.findAllByOrderByCreatedateAsc(pageable);

        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(diaries, HttpStatus.OK);
    }

    @GetMapping("/diary/pagination/DESC")
    public ResponseEntity<?> getListDiaryAndPaginationDESC(@PageableDefault(value = 2) Pageable pageable) {
        Page<Diary> diaries = diaryService.findAllByOrderByCreatedateDesc(pageable);

        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(diaries, HttpStatus.OK);
    }

    @PostMapping("/diary/searchBy-Title-And-UserId")
    public ResponseEntity<?> searchDiaryByTitleAndUserId(@RequestBody SearchDiaryByTitleAndUserId searchDiaryByTitleAndUserId) {
        List<Diary> diaries;
        if (searchDiaryByTitleAndUserId.getTitle().equals("")) {
            diaries = (List<Diary>) diaryService.findAll();
            if (diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries, HttpStatus.OK);
        }
        diaries = (List<Diary>) diaryService.findDiariesByTitleContainingAndUserId(searchDiaryByTitleAndUserId.getTitle(), searchDiaryByTitleAndUserId.getId());
        return new ResponseEntity<>(diaries, HttpStatus.OK);
    }

    @GetMapping("/diary/searchBy-TagId/{id}")
    public ResponseEntity<?> searchByTagId(@PathVariable Long id) {
        List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTagId(id);

        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(diaries, HttpStatus.OK);
    }

    @PostMapping("/diary/search-by-title")
    public ResponseEntity<?> searchDiaryByTitle(@RequestBody SearchDiaryByTitle titleForm) {
        if (titleForm.getTitle().equals("") || titleForm.getTitle() == null) {
            List<Diary> diaries = (List<Diary>) diaryService.findAll();

            if (diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(diaries, HttpStatus.OK);
            }
        }

        List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTitleContaining(titleForm.getTitle());
        if (diaries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(diaries, HttpStatus.OK);
        }
    }

    @PostMapping("/diary/search-by-tag-and-title")
    public ResponseEntity<?> searchDiaryByTagAndTitle(@RequestBody SearchDiaryByTagAndTitle searchForm) {
        if (searchForm.getTitle() == null && searchForm.getTagId() == null) {
            List<Diary> diaries = (List<Diary>) diaryService.findAll();
            if (diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries, HttpStatus.OK);
        }

        if (searchForm.getTitle() == null && searchForm.getTagId() != null) {
            List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTagId(searchForm.getTagId());
            if (diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries, HttpStatus.OK);
        }

        if (searchForm.getTitle() != null && searchForm.getTagId() == null) {
            List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTitleContaining(searchForm.getTitle());
            if (diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries, HttpStatus.OK);
        }

        if (searchForm.getTagId() != null && searchForm.getTitle() != null) {
            List<Diary> diaries = (List<Diary>) diaryService.findDiariesByTagIdAndTitleContaining(searchForm.getTagId(), searchForm.getTitle());
            if (diaries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(diaries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
