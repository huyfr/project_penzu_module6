package pendzu.sduteam.services;

import pendzu.sduteam.models.Image;

public interface IImageService extends GenericService<Image> {
  Iterable<Image> findImagesByAlbumIdAndStatusAfter(Long id, int status);
}
