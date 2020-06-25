package servlets;

import accountServer.AccountServerI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPanelServlet extends HttpServlet {
    static final Logger LOGGER = LogManager.getLogger(HomePageServlet.class.getName());
    private final AccountServerI accountServer;

    public AdminPanelServlet(AccountServerI accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        int limit = accountServer.getUsersLimit();

        LOGGER.info("limit: {}", limit);

        response.getWriter().println(limit);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
