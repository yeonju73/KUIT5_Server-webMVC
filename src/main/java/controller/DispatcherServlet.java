package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static controller.enums.redirect.REDIRECT;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final RequestMapper requestMapper = new RequestMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uriPath = getUriPath(req);
        Controller controller = requestMapper.getController(uriPath);

        if (controller == null){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        doExecute(req, resp, controller);
    }

    private void doExecute(HttpServletRequest req, HttpServletResponse resp, Controller controller) throws IOException, ServletException {
        String path = controller.execute(req, resp);
        if (checkAndDoRedirect(resp, path))
            return;
        forward(path, req, resp);
    }

    private boolean checkAndDoRedirect(HttpServletResponse resp, String path) throws IOException {
        if (path.startsWith(REDIRECT.getRedirectSign())){
            redirect(path.split(REDIRECT.getRedirectSign())[1], resp);
            return true;
        }
        return false;
    }

    private static String getUriPath(HttpServletRequest req) {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        return uri.substring(contextPath.length());
    }

    private void forward(String path, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(path + ".jsp");
        rd.forward(req, resp);
    }

    private void redirect(String path, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(path);
    }
}
