package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

@WebServlet("/user/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemoryUserRepository repository = MemoryUserRepository.getInstance();
        User user = repository.findUserById(req.getParameter("userId"));

        if (user == null || !repository.checkPassword(user, req.getParameter("password"))){
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid username or password");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        System.out.println("User 로그인 완료");

        resp.sendRedirect("/user/userList");
    }
}
