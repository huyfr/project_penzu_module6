package pendzu.sduteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import pendzu.sduteam.models.Diary;
import pendzu.sduteam.models.Image;
import pendzu.sduteam.repositories.IImageRepository;
import pendzu.sduteam.services.IImageService;

import java.util.Optional;

@Service
@PropertySource({"classpath:status.properties"})
public class ImageServiceImpl implements IImageService {

  @Value("${entity.deleted}")
  private int deleteStatus;

  @Autowired
  private IImageRepository imageRepository;

  @Override
  public Iterable<Image> findImagesByAlbumIdAndStatusAfter(Long id, int status) {
    return this.imageRepository.findImagesByAlbumIdAndStatusAfter(id,status);
  }

  @Override
  public Optional<Image> findById(Long id) {
    return this.imageRepository.findById(id);
  }

  @Override
  public Iterable<Image> findAll() {
    return this.imageRepository.findAll();
  }

  @Override
  public Image save(Image model) {
    return this.imageRepository.save(model);
  }

  @Override
  public void delete(Long id) {
    Optional<Image> imageOptional = this.imageRepository.findById(id);
    Image image = imageOptional.get();
    if (image != null) {
      image.setStatus(deleteStatus);
      this.imageRepository.save(image);
    }
  }
}
