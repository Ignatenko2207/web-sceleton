package info.sjd.controller;

import info.sjd.model.User;
import info.sjd.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null)
        {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/wrong.jsp");
            dispatcher.forward(req, resp);
        }
        else{
            User user = UserService.findUserByLoginAndPassword(login, password);

            if (user != null && user.getPassword().equals(password)){
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/good.jsp");
                HttpSession session = req.getSession();
                session.setAttribute("user-name", String.format("%s %s", user.getFirstName(), user.getLastName()));
                dispatcher.forward(req, resp);
            }
            else{
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/wrong.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
