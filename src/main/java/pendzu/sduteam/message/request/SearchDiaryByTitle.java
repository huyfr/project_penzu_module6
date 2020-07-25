package pendzu.sduteam.message.request;

public class SearchDiaryByTitle {
    private String title;

    public SearchDiaryByTitle() {
    }

    public SearchDiaryByTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
