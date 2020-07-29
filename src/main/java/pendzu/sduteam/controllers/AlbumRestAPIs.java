package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pendzu.sduteam.models.Album;
import pendzu.sduteam.services.IAlbumService;
import pendzu.sduteam.services.impl.AlbumServiceImpl;

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
}
