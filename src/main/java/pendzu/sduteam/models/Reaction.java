package pendzu.sduteam.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "reactions")
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    private String reactionname;

    public Reaction(Long id, @NotBlank String reactionname) {
        this.reactionname = reactionname;
    }

    public Reaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReactionname() {
        return reactionname;
    }

    public void setReactionname(String reactionname) {
        this.reactionname = reactionname;
    }
}
