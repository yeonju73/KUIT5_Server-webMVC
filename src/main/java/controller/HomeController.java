package controller;

import controller.enums.JSP;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return JSP.HOME.getJspPath();
    }
}
