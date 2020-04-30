import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "Connexion")
public class Connexion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = this.getServletContext().getContextPath();
        HttpSession session = request.getSession();

        String admail = request.getParameter("user_mail");
        session.setAttribute("email", admail);
        String pwd = request.getParameter("user_pwd");
        session.setAttribute("pwd", pwd);
        ArrayList<ArrayList<String>> posts = new ArrayList<>();
        ArrayList<ArrayList<String>> coms = new ArrayList<>();


        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AE?serverTimezone=UTC", "root", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as nb FROM user WHERE email = '" + admail + "' AND mdp =  '" + pwd + "';");
            while(rs.next()) {
                if (rs.getInt(1) == 0) {
                    String erreur = "Les identifiants saisis ne correspondent pas. Veuillez réessayer.";
                    request.setAttribute("erreur", erreur);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/Blog/Connexion.jsp").forward(request, response);
                }
            }
            /*while (rs.next())
                System.out.println(rs.getInt(1));*/
            ResultSet rs1 = stmt.executeQuery("SELECT username FROM user WHERE email = '" + admail + "';");
            while(rs1.next()) {
                String username = rs1.getString(1);
                request.setAttribute("username", username);
                session.setAttribute("username", username);
                request.setAttribute("email", admail);

            }
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

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Blog/Accueil.jsp" ).forward( request, response );

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Blog/Connexion.jsp" ).forward( request, response );

    }
}
