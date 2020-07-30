package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.models.Album;
import pendzu.sduteam.services.IAlbumService;
import pendzu.sduteam.services.impl.AlbumServiceImpl;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/sdu")
@CrossOrigin(origins = "*")
@PropertySource({"classpath:status.properties"})
public class AlbumRestAPIs {

  @Value("${entity.exist}")
  private int activeDiaryStatus;

  @Value("${entity.greater.than.zero}")
  private int allPostAvailable;

  @Autowired
  private AlbumServiceImpl albumService;

  @GetMapping("/album")
  public ResponseEntity<List<Album>>getAllAlbum(){
    List<Album> albums = (List<Album>) this.albumService.findAll();
    if (albums.isEmpty()){
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(albums, HttpStatus.OK);
  }

  @GetMapping("/album/userId/{id}")
  public ResponseEntity<?> getAlbumsByUserIdAndStatus(@PathVariable Long id) {
    List<Album> albums = (List<Album>) albumService.findAllByUserIdAndStatusAfter(id,allPostAvailable);
    if (albums.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(albums, HttpStatus.OK);
  }

  @GetMapping("/album/{id}")
  public ResponseEntity<?>getAlbumById(@PathVariable("id")Long id) {
    Optional<Album> album = this.albumService.findById(id);

    if (!album.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(album,HttpStatus.OK);
  }

  @PostMapping("/album")
  public ResponseEntity<?> createAlbum(@Valid @RequestBody Album album) {
    LocalDateTime localDateTime = LocalDateTime.now();
    album.setCreatedate(localDateTime);
    album.setUpdatedate(localDateTime);
    albumService.save(album);
    return new ResponseEntity<>(album, HttpStatus.CREATED);
  }

  @PutMapping("/album/{id}")
  public ResponseEntity<?> updateAlbum(@PathVariable Long id , @RequestBody Album album) {
    Optional<Album> album1 = albumService.findById(id);
    if (!album1.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    album1.get().setTag(album.getTag());
    album1.get().setTitle(album.getTitle());
    albumService.save(album1.get());

    return new ResponseEntity<>(album1 , HttpStatus.OK);
  }

}
