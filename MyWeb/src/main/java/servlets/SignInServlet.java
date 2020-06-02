package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");
        String message = "Unauthorized";

        UserProfile profile = accountService.getUserByLogin(login);

        if (profile != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            message = "Authorized: " + login;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(message);
    }
}
