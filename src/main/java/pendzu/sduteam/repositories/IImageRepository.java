package pendzu.sduteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pendzu.sduteam.models.Image;

public interface IImageRepository extends PagingAndSortingRepository<Image, Long> {
  Iterable<Image> findImagesByAlbumId(Long id);
}
