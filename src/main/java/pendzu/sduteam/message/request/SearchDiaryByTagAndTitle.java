package pendzu.sduteam.message.request;

public class SearchDiaryByTagAndTitle {
    private Long tagId;
    private String title;

    public SearchDiaryByTagAndTitle() {
    }

    public SearchDiaryByTagAndTitle(Long tagId, String title) {
        this.tagId = tagId;
        this.title = title;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
