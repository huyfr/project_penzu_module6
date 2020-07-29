package pendzu.sduteam.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotBlank
  private String title;

  @Lob
  private String avatar;

  @NotBlank
  @ManyToOne(targetEntity = Tag.class)
  @JoinColumn(name = "tag_id")
  private Tag tag;

  @JsonIgnore
  @OneToMany(targetEntity = Image.class, cascade = CascadeType.ALL)
  private List<Image> images;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "albums_attachment",
    joinColumns = @JoinColumn(name = "albums_id"),
    inverseJoinColumns = @JoinColumn(name = "attachments_id"))
  private Set<Attachment> attachment = new HashSet<>();

  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "users_id")
  private User user;


  private String blobstring;

  @ManyToOne(targetEntity = Reaction.class)
  @JoinColumn(name = "reactions_id")
  private Reaction reaction;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updatedate;

  private int status = 1;

  public Album(@NotBlank String title, @NotBlank Tag tag, Set<Attachment> attachment, User user, String blobstring, Reaction reaction, LocalDateTime createdate, LocalDateTime updatedate, int status) {
    this.title = title;
    this.tag = tag;
    this.attachment = attachment;
    this.user = user;
    this.blobstring = blobstring;
    this.reaction = reaction;
    this.createdate = createdate;
    this.updatedate = updatedate;
    this.status = status;
  }

  public Album(@NotBlank String title, String avatar, @NotBlank Tag tag, List<Image> images, Set<Attachment> attachment, User user, String blobstring, Reaction reaction, LocalDateTime createdate, LocalDateTime updatedate, int status) {
    this.title = title;
    this.avatar = avatar;
    this.tag = tag;
    this.images = images;
    this.attachment = attachment;
    this.user = user;
    this.blobstring = blobstring;
    this.reaction = reaction;
    this.createdate = createdate;
    this.updatedate = updatedate;
    this.status = status;
  }

  public Album(@NotBlank String title, @NotBlank Tag tag, Set<Attachment> attachment, User user) {
    this.title = title;
    this.tag = tag;
    this.attachment = attachment;
    this.user = user;
  }

  public Album() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Tag getTag() {
    return tag;
  }

  public void setTag(Tag tag) {
    this.tag = tag;
  }

  public Set<Attachment> getAttachment() {
    return attachment;
  }

  public void setAttachment(Set<Attachment> attachment) {
    this.attachment = attachment;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getBlobstring() {
    return blobstring;
  }

  public void setBlobstring(String blobstring) {
    this.blobstring = blobstring;
  }

  public Reaction getReaction() {
    return reaction;
  }

  public void setReaction(Reaction reaction) {
    this.reaction = reaction;
  }

  public LocalDateTime getCreatedate() {
    return createdate;
  }

  public void setCreatedate(LocalDateTime createdate) {
    this.createdate = createdate;
  }

  public LocalDateTime getUpdatedate() {
    return updatedate;
  }

  public void setUpdatedate(LocalDateTime updatedate) {
    this.updatedate = updatedate;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }
}
