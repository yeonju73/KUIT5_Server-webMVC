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

@WebServlet("/user/update")
public class UpdateUserFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));

        if (user == null){
            // 사용자 정보가 없을 경우 에러 처리
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return;
        }

        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션에 저장된 정보 가져오기
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if (!(value instanceof User user) || !user.isSameUser(req.getParameter("userId"))) { // 세션과 request param의 user Id가 다르다면
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid login or session");
            return;
        }

        User updateUser = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        MemoryUserRepository.getInstance().changeUserInfo(updateUser);

        System.out.println("User 개인정보 업데이트 완료");

        resp.sendRedirect("/user/userList");
    }
}
