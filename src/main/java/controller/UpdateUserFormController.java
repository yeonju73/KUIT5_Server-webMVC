package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;

public class UpdateUserFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getMethod().equalsIgnoreCase("GET")) return doGet(req, resp);
        if (req.getMethod().equalsIgnoreCase("POST")) return doPost(req, resp);
        resp.sendError(SC_METHOD_NOT_ALLOWED, "Method not allowed");
        return null;
    }

    private String doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));

        // 사용자 정보가 없을 경우 에러 처리
        if (user == null){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            return null;
        }

        req.setAttribute("user", user);
        return "/user/updateForm";
    }

    private String doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 세션에 저장된 정보 가져오기
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if (!(value instanceof User user) || !user.isSameUser(req.getParameter("userId"))) { // 세션과 request param의 user Id가 다르다면
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid login or session");
            return null;
        }

        User updateUser = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        MemoryUserRepository.getInstance().changeUserInfo(updateUser);

        System.out.println("User 개인정보 업데이트 완료");

        return "redirect:/user/userList";
    }
}
