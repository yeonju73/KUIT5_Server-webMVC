package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Controller {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
