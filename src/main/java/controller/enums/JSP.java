package controller.enums;

public enum JSP {
    LOGIN_FAILED("/user/login_failed"),
    HOME("/home"),
    REGISTER("/user/form"),
    USER_LIST("/user/list"),
    UPDATE_FORM("/user/updateForm"),
    LOGIN("/user/login");

    private final String jsp;

    JSP(String jsp) {
        this.jsp = jsp;
    }

    public String getJspPath() {
        return jsp;
    }
}
