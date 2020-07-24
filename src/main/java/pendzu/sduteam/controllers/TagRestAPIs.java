package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.models.Diary;
import pendzu.sduteam.models.Tag;
import pendzu.sduteam.services.impl.TagServiceIpml;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/sdu")
@RestController
@CrossOrigin(origins = "*")
public class TagRestAPIs {

  @Autowired
  TagServiceIpml tagService;

  @GetMapping("/tags")
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

  @PostMapping("/create")
  public ResponseEntity<Tag> createCustomer(@Valid @RequestBody Tag tag) {
    tagService.save(tag);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/edit/{id}")
  public ResponseEntity<?> editTag(@PathVariable Long id, @RequestBody Tag tag) {
    Optional<Tag> tagOptional = tagService.findById(id);
    if (tagOptional == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    tagOptional.get().setStatus(tag.getStatus());
    tagOptional.get().setTagname(tag.getTagname());
    tagService.save(tagOptional.get());
    return new ResponseEntity<>(tagOptional , HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> remove(@PathVariable Long id){
    this.tagService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
