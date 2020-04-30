import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "Commentaire")
public class Commentaire extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = this.getServletContext().getContextPath();
        HttpSession session = request.getSession();
        String comment = request.getParameter("comm");
        String admail = (String) session.getAttribute("email");
        String idbut = request.getParameter("bouton");
        String idb = request.getParameter("id");
        ArrayList<ArrayList<String>> posts = new ArrayList<>();
        ArrayList<ArrayList<String>> coms = new ArrayList<>();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AE?serverTimezone=UTC", "root", "root");
            Statement stmt = con.createStatement();

            //int statut = stmt.executeUpdate("INSERT INTO com (postid, email, message) "
            //        + "VALUES ('" + idbut + "', '" + admail + "', '" + comment + "');");

            posts.add(new ArrayList<String>());
            ResultSet rs2 = stmt.executeQuery("SELECT id, message FROM post WHERE email = '" + admail + "';");
            while (rs2.next()) {
                int x = 0;
                int i = 1;
                int j = 2;
                posts.get(x).add(rs2.getString(i));
                posts.get(x).add(rs2.getString(j));
                x++;
                j = j + 2;
                i = i + 2;
            }
            request.setAttribute("post", posts);
            coms.add(new ArrayList<String>());
            ResultSet rs3 = stmt.executeQuery("SELECT postid, username, com.message FROM post, user, com WHERE post.email = '" + admail + "' AND user.email = post.email AND postid = post.id;");
            while (rs3.next()) {
                int x = 0;
                int i = 1;
                int j = 2;
                int k = 3;
                coms.get(x).add(rs3.getString(i));
                coms.get(x).add(rs3.getString(j) + " a dit '" + rs3.getString(k) + "'");
                //coms.get(x).add(rs3.getString(k));
                i = i + 3;
                j = j + 3;
                k = k + 3;
                x++;
            }
            request.setAttribute("coms", coms);
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        String id = (String) session.getAttribute("username");
        request.setAttribute("username", id);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/Blog/Accueil.jsp" ).forward( request, response );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Blog/Commentaire.jsp" ).forward( request, response );

    }
}
