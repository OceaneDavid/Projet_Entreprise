import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

@WebServlet(name = "Accueil")
public class Accueil extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String admail = escapeHtml4((String) session.getAttribute("email"));
        ArrayList<String> posts = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AE?serverTimezone=UTC", "root", "root");
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT message FROM post WHERE email = '" + admail + "';");
            while (rs.next()) {
                int i = 1;
                posts.add(rs.getString(i));
                i++;
            }
            request.setAttribute("post", posts);
            con.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //request.setAttribute("username", id);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/Blog/Accueil.jsp" ).forward( request, response );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Blog/Connexion.jsp" ).forward( request, response );

    }
}
