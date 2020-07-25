package pendzu.sduteam.services;

import pendzu.sduteam.models.Tag;

public interface ITagService extends GenericService<Tag> {
    Iterable<Tag> findTagsByNameContaining(String tag_name);
}
