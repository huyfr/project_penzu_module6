package pendzu.sduteam.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pendzu.sduteam.models.Album;

public interface IAlbumService extends GenericService<Album> {
  Page<Album> findAllByUserIdAndStatus(Pageable pageable, Long id, int status);

  Iterable<Album> findAllByUserIdAndStatusAfter(Long user_id, int status);
}
