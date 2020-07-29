package pendzu.sduteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pendzu.sduteam.models.Album;
import pendzu.sduteam.models.Image;
import pendzu.sduteam.repositories.IAlbumRepository;
import pendzu.sduteam.services.IAlbumService;

import java.util.Optional;

@Service
@PropertySource({"classpath:status.properties"})
public class AlbumServiceImpl implements IAlbumService {

  @Value("${entity.deleted}")
  private int deleteStatus;

  @Autowired
  private IAlbumRepository albumRepository;

  @Override
  public Page<Album> findAllByUserIdAndStatus(Pageable pageable, Long id, int status) {
    return this.albumRepository.findAllByUserIdAndStatus(pageable,id,status);
  }

  @Override
  public Iterable<Album> findAllByUserIdAndStatusAfter(Long user_id, int status) {
    return this.albumRepository.findAllByUserIdAndStatusAfter(user_id,status);
  }

  @Override
  public Optional<Album> findById(Long id) {
    return this.albumRepository.findById(id);
  }

  @Override
  public Iterable<Album> findAll() {
    return this.albumRepository.findAll();
  }

  @Override
  public Album save(Album model) {
    return this.albumRepository.save(model);
  }

  @Override
  public void delete(Long id) {
    Optional<Album> albumOptional = this.albumRepository.findById(id);
    Album album = albumOptional.get();
    if (album != null) {
      album.setStatus(deleteStatus);
      this.albumRepository.save(album);
    }
  }
}
