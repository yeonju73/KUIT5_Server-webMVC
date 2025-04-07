package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;


import java.io.IOException;
import java.util.Collection;

@WebServlet("/user/userList")
public class ListUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 세션에 저장된 정보 가져오기
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if (!(value instanceof User)) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid login or session");
            return;
        }

        User user = (User) value;
        System.out.println("logged in user: " + user.getUserId());

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users); // users 저장

        // request 로부터 dispatcher 객체를 가져옴
        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }
}
