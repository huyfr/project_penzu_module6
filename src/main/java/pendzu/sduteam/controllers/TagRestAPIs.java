package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.message.request.SearchTagByNameTag;
import pendzu.sduteam.models.Tag;
import pendzu.sduteam.services.IDiaryService;
import pendzu.sduteam.services.impl.TagServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/sdu")
@RestController
@CrossOrigin(origins = "*")
public class TagRestAPIs {

  @Autowired
  private TagServiceImpl tagService;

  @Autowired
  private IDiaryService diaryService;

  @GetMapping("/tag")
  public ResponseEntity<?> getListTag() {
    List<Tag> tags = (List<Tag>) tagService.findAll();
    if(tags.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(tags,HttpStatus.OK);
  }

  @GetMapping("/tag/{id}")
  public ResponseEntity<?> getTag(@PathVariable Long id) {
    Optional<Tag> tag = tagService.findById(id);
    if (!tag.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(tag, HttpStatus.OK);
  }

//  @PostMapping("/create")
//  public ResponseEntity<Tag> createCustomer(@Valid @RequestBody Tag tag) {
//    tagService.save(tag);
//    return new ResponseEntity<>(HttpStatus.OK);
//  }

  @PutMapping("/tag/{id}")
  public ResponseEntity<?> editTag(@PathVariable Long id, @RequestBody Tag tag) {
    Optional<Tag> tagOptional = tagService.findById(id);
    if (!tagOptional.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    tagOptional.get().setStatus(tag.getStatus());
    tagOptional.get().setName(tag.getName());
    tagService.save(tagOptional.get());
    return new ResponseEntity<>(tagOptional , HttpStatus.OK);
  }

  @DeleteMapping("/tag/{id}")
  public ResponseEntity<Void> remove(@PathVariable Long id){
    this.tagService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /*Tuan Code*/
  @PostMapping("/tag")
  public ResponseEntity<?> createTag(@Valid @RequestBody Tag tag) {
    tagService.save(tag);

    return new ResponseEntity<>(tag,HttpStatus.CREATED);
  }

  @PostMapping("/tag/search-by-name")
  public ResponseEntity<?> searchTagByNameTag(@RequestBody SearchTagByNameTag tagForm) {
    if(tagForm.getName().equals("") || tagForm.getName() == null ) {
      List<Tag> tags = (List<Tag>) tagService.findAll();
      if(tags.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(tags,HttpStatus.OK);
      }
    }

    List<Tag> tags = (List<Tag>) tagService.findTagsByNameContaining(tagForm.getName());
    if(tags.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(tags,HttpStatus.OK);
    }
  }

}
