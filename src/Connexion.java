import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Connexion")
public class Connexion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = this.getServletContext().getContextPath();
        HttpSession session = request.getSession();

        String admail = request.getParameter("user_mail");
        session.setAttribute("mail", admail);
        String pwd = request.getParameter("user_pwd");
        session.setAttribute("pwd", pwd);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AE?serverTimezone=UTC", "svenja", "root");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as nb FROM user WHERE email = '" + admail + "' AND mdp =  '" + pwd + "';");
            while(rs.next()) {
                if (rs.getInt(1) == 0) {
                    String erreur = "Les identifiants saisis ne correspondent pas. Veuillez réessayer.";
                    request.setAttribute("erreur", erreur);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/Blog/Connexion.jsp").forward(request, response);
                }
            }
            while (rs.next())
                System.out.println(rs.getInt(1));
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect(path + "/Accueil");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Blog/Connexion.jsp" ).forward( request, response );

    }
}
