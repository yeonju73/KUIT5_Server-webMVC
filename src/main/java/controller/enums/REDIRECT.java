package controller.enums;

public enum REDIRECT {
    REDIRECT("redirect:");

    private final String redirectSign;

    REDIRECT(String s) {
        this.redirectSign = s;
    }

    public String getRedirectSign() {
        return redirectSign;
    }

    public static String getRedirectPathString(URL url) {
        return REDIRECT.getRedirectSign() + url.getUrl();
    }
}
