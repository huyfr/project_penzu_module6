package pendzu.sduteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pendzu.sduteam.models.Album;
import pendzu.sduteam.repositories.IAlbumRepository;
import pendzu.sduteam.services.IAlbumService;

import java.util.Optional;

@Service
public class AlbumServiceImpl implements IAlbumService {

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
    return Optional.empty();
  }

  @Override
  public Iterable<Album> findAll() {
    return null;
  }

  @Override
  public Album save(Album model) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }
}
