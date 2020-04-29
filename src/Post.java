import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "Post")
public class Post extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = this.getServletContext().getContextPath();
        HttpSession session = request.getSession();
        String texte = request.getParameter("contenu");
        String admail = (String) session.getAttribute("email");
        ArrayList<String> posts = new ArrayList<>();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AE?serverTimezone=UTC", "svenja", "root");
            Statement stmt = con.createStatement();

            int statut = stmt.executeUpdate("INSERT INTO post (email, message) "
                    + "VALUES ('" + admail + "', '" + texte + "');");

            ResultSet rs = stmt.executeQuery("SELECT message FROM post WHERE email = '" + admail + "';");
            while (rs.next()){
                int i = 1;
                posts.add(rs.getString(i));
                i++;
            }
            request.setAttribute("post", posts);
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //request.setAttribute("username", id);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/Blog/Accueil.jsp" ).forward( request, response );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Blog/Post.jsp" ).forward( request, response );

    }
}
