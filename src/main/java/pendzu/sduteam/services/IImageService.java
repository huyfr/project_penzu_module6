package pendzu.sduteam.services;

import pendzu.sduteam.models.Image;

public interface IImageService extends GenericService<Image> {
  Iterable<Image> findImagesByAlbumId(Long id);
}
