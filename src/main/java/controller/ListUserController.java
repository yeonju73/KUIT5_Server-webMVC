package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.util.Collection;

public class ListUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // 세션에 저장된 정보 가져오기
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if (!(value instanceof User user)) {
            return "redirect:/user/login";
        }
        System.out.println("logged in user: " + user.getUserId());

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users); // users 저장

        return "/user/list";
    }
}
