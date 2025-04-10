package controller.controllers;

import controller.enums.REDIRECT;
import controller.enums.URL;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        System.out.println("User 로그아웃 완료");

        return REDIRECT.getRedirectPathString(URL.HOME);
    }
}
