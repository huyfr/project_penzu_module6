package pendzu.sduteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pendzu.sduteam.models.Album;

public interface IAlbumRepository extends PagingAndSortingRepository<Album, Long> {
  Page<Album> findAllByUserIdAndStatus(Pageable pageable,Long id,int status);
}
