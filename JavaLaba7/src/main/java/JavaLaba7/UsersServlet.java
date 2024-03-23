package JavaLaba7;

import JavaLaba7.DataSets.UserProfileDataSet;
import JavaLaba7.Service.DBService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class UsersServlet extends HttpServlet {

    public void doGet(HttpServletRequest httpServletRequest,
                      HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("registration.jsp").forward(httpServletRequest, httpServletResponse);
    }

    //Регистрация в системе
    public void doPost(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) throws IOException {
        String email = httpServletRequest.getParameter("email");
        String login = httpServletRequest.getParameter("login");
        String pass = httpServletRequest.getParameter("pass");

        if (email.isEmpty() || login.isEmpty() || pass.isEmpty()) {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.getWriter().println("Отсутсвует email, логин или пароль");
            return;
        }

        UserProfileDataSet profile = new UserProfileDataSet(login, pass, email);
        if (DBService.getUserByLogin(login) == null) {
            // Создание новой папки для пользователя
            File folder = new File("C:\\Users\\Informant\\fileManager\\" + login);
            boolean isCreationSuccess = folder.mkdir();
            //Скорее всего никогда не будет false, но если будет нехватать памяти или что-то ещё, то сработает
            if (!isCreationSuccess) {
                httpServletResponse.setContentType("text/html;charset=utf-8");
                httpServletResponse.getWriter().println("Случилась ошибка при создании папки, попробуйте ещё раз");
                return;
            }

            DBService.addNewUser(profile);

            httpServletRequest.getSession().setAttribute("login",login);
            httpServletRequest.getSession().setAttribute("pass",pass);

            String currentURL = httpServletRequest.getRequestURL().toString();
            httpServletResponse.sendRedirect(ServletUtilities.makeNewUrl(currentURL, "/manager"));
        } else {
            httpServletResponse.setContentType("text/html;charset=utf-8");
            httpServletResponse.getWriter().println("Пользователь с таким логином уже есть в системе");
        }
    }
}
