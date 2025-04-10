package controller;

import controller.enums.JSP;
import controller.enums.REDIRECT;
import controller.enums.URL;
import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwp.model.User;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;

public class CreateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getMethod().equalsIgnoreCase("GET")) return doGet();
        if (req.getMethod().equalsIgnoreCase("POST")) return doPost(req);
        resp.sendError(SC_METHOD_NOT_ALLOWED, "Method not allowed");
        return null;
    }

    private String doGet() {
        return JSP.REGISTER.getJspPath();
    }

    private static String doPost(HttpServletRequest req) {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("User 회원가입 완료");

        return REDIRECT.getRedirectPathString(URL.HOME);
    }
}
