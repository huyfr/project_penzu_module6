package pendzu.sduteam.message.request;

public class SearchTagByNameTag {
    private String name;

    public SearchTagByNameTag(String name) {
        this.name = name;
    }

    public SearchTagByNameTag() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
