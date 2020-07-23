package pendzu.sduteam.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import pendzu.sduteam.models.Tag;
import pendzu.sduteam.repositories.TagRepository;
import pendzu.sduteam.services.ITagService;

import java.util.Optional;

@Service
@PropertySource({"classpath:status.properties"})
public class TagServiceIpml implements ITagService {

  @Value("${entity.deleted}")
  private int deleteStatus;

  @Autowired
  TagRepository tagRepository;

  @Override
  public Optional<Tag> findById(Long id) {
    return tagRepository.findById(id);
  }

  @Override
  public Iterable<Tag> findAll() {
    return tagRepository.findAll();
  }

  @Override
  public Tag save(Tag model) {
    return tagRepository.save(model);
  }

  @Override
  public void delete(Long id) {
    Optional<Tag> tagOptional = tagRepository.findById(id);
    Tag tag = tagOptional.get();
    tag.setStatus(deleteStatus);
    tagRepository.save(tag);
  }
}
