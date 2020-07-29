package pendzu.sduteam.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pendzu.sduteam.models.Image;
import pendzu.sduteam.services.impl.ImageServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/api/sdu")
@CrossOrigin(origins = "*")
@PropertySource({"classpath:status.properties"})
public class ImageRestAPIs {

  @Autowired
  private ImageServiceImpl imageService;

  @GetMapping("/image/albumId/{id}")
  public ResponseEntity<?> getListImageByAlbumId(@PathVariable Long id) {
    List<Image> images = (List<Image>) imageService.findImagesByAlbumId(id);

    if (images.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(images, HttpStatus.OK);
  }
}
