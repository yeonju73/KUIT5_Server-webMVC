package controller.controllers;

import controller.enums.JSP;
import controller.enums.REDIRECT;
import controller.enums.URL;
import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;

public class LoginController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getMethod().equalsIgnoreCase("GET")) return doGet();
        if (req.getMethod().equalsIgnoreCase("POST")) return doPost(req);
        resp.sendError(SC_METHOD_NOT_ALLOWED, "Method not allowed");
        return null;
    }

    private String doGet() {
        return JSP.LOGIN.getJspPath();
    }

    private static String doPost(HttpServletRequest req) {
        MemoryUserRepository repository = MemoryUserRepository.getInstance();
        User user = repository.findUserById(req.getParameter("userId"));

        if (user == null || !user.matchPassword(req.getParameter("password"))){
            System.out.println("User 로그인 실패");
            return JSP.LOGIN_FAILED.getJspPath();
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        System.out.println("User 로그인 완료");

        return REDIRECT.getRedirectPathString(URL.USER_LIST);
    }
}
