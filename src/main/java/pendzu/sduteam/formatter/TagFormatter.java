package pendzu.sduteam.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pendzu.sduteam.models.Tag;
import pendzu.sduteam.services.impl.TagServiceImpl;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class TagFormatter implements Formatter<Tag> {
  TagServiceImpl tagService;
  @Autowired
  public TagFormatter (TagServiceImpl tagService){
    this.tagService = tagService;
  }

  @Override
  public Tag parse(String text, Locale locale) throws ParseException {
    Optional<Tag> optionalTag= tagService.findById(Long.parseLong(text));
    Tag tag = optionalTag.get();
    return tag;
  }

  @Override
  public String print(Tag object, Locale locale) {
    return object.toString();
  }
}
