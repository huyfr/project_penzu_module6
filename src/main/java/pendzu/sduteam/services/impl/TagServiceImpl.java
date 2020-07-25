package pendzu.sduteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import pendzu.sduteam.models.Tag;
import pendzu.sduteam.repositories.ITagRepository;
import pendzu.sduteam.services.ITagService;

import java.util.Optional;

@Service
@PropertySource({"classpath:status.properties"})
public class TagServiceImpl implements ITagService {

  @Value("${entity.deleted}")
  private int deleteStatus;

  @Autowired
  ITagRepository repository;

  @Override
  public Optional<Tag> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  public Iterable<Tag> findAll() {
    return repository.findAll();
  }

  @Override
  public Tag save(Tag model) {
    return repository.save(model);
  }

  @Override
  public void delete(Long id) {
    Optional<Tag> tagOptional = repository.findById(id);
    Tag tag = tagOptional.get();
    tag.setStatus(deleteStatus);
    repository.save(tag);
  }

  @Override
  public Iterable<Tag> findTagsByNameContaining(String tag_name) {
    return repository.findTagsByNameContaining(tag_name);
  }
}
