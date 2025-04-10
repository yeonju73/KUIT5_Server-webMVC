package controller.enums;

public enum JSP {
    LOGIN_FAILED("/user/login_failed.jsp"),
    HOME("/home.jsp"),
    REGISTER("/user/form.jsp"),
    USER_LIST("/user/list.jsp"),
    UPDATE_FORM("/user/updateForm.jsp"),
    LOGIN("/user/login.jsp");

    private final String jsp;

    JSP(String jsp) {
        this.jsp = jsp;
    }

    public String getJspPath() {
        return jsp;
    }
}
