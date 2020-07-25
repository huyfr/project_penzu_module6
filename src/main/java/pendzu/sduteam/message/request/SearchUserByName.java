package pendzu.sduteam.message.request;

public class SearchUserByName {
    private String name;

    public SearchUserByName() {
    }

    public SearchUserByName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
