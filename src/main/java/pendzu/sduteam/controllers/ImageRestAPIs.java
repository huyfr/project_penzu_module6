package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pendzu.sduteam.models.Image;
import pendzu.sduteam.services.impl.ImageFirebaseServiceExtends;
import pendzu.sduteam.services.impl.ImageServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/sdu")
@CrossOrigin(origins = "*")
@PropertySource({"classpath:status.properties"})
public class ImageRestAPIs {

  @Value("${entity.greater.than.zero}")
  private int allPostAvailable;

  @Autowired
  private ImageServiceImpl imageService;

  @Autowired
  private ImageFirebaseServiceExtends imageFirebaseServiceExtends;

  @GetMapping("/image/albumId/{id}")
  public ResponseEntity<?> getListImageByAlbumId(@PathVariable Long id) {
    List<Image> images = (List<Image>) imageService.findImagesByAlbumIdAndStatusAfter(id,allPostAvailable);

    if (images.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(images, HttpStatus.OK);
  }

  @DeleteMapping("/image/{id}")
  public ResponseEntity<?> deleteImage(@PathVariable Long id) {
    Optional<Image> image = imageService.findById(id);

    if (!image.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    if(image.get().getBlobString() != null) {
      imageFirebaseServiceExtends.deleteFirebaseStorageFile(image.get());
    }
    imageService.delete(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
