package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final RequestMapper requestMapper = new RequestMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uriPath = getUriPath(req);
        Controller controller = requestMapper.getController(uriPath);

        if (controller != null){
            String path = controller.execute(req, resp);
            if (!path.startsWith("redirect:")){
                forward(path, req, resp);
                return;
            }
            redirect(path.split("redirect:")[1], resp);
        }
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
