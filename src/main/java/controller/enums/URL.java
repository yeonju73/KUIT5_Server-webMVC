package controller.enums;

public enum URL {
    HOME("/"),
    REGISTER("/user/signup"),
    LOGIN("/user/login"),
    LOGOUT("/user/logout"),
    USER_LIST("/user/userList"),
    UPDATE("/user/update");


    private final String url;

    private URL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
