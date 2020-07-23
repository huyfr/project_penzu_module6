package pendzu.sduteam.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @Lob
    private String content;

    private int status = 1;

    @NotBlank
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank
    @ManyToOne(targetEntity = Diary.class)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    public Comment(@NotBlank String content, int status, @NotBlank User user, @NotBlank Diary diary) {
        this.content = content;
        this.status = status;
        this.user = user;
        this.diary = diary;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Diary getDiary() {
        return diary;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }
}
