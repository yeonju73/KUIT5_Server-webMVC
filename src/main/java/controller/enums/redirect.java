package controller.enums;

public enum redirect {
    REDIRECT("redirect:");

    private final String redirectSign;

    redirect(String s) {
        this.redirectSign = s;
    }

    public String getRedirectSign() {
        return redirectSign;
    }
}
