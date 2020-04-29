import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "Inscription")
public class Inscription extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = this.getServletContext().getContextPath();
        HttpSession session = request.getSession();

        String id = request.getParameter("user_id");
        session.setAttribute("id", id);
        String admail = request.getParameter("user_mail");
        session.setAttribute("email", admail);
        String pwd = request.getParameter("user_pwd");
        session.setAttribute("pwd", pwd);

            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/AE?serverTimezone=UTC", "svenja", "root");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as nb FROM user WHERE email = '" + admail + "';");
                while(rs.next()){
                if(rs.getInt(1) != 0){
                    String erreur = "L'adresse mail est déjà utilisé par un autre compte. Veuillez en utiliser une autre.";
                    request.setAttribute("erreur", erreur);
                    this.getServletContext().getRequestDispatcher( "/WEB-INF/Blog/Inscription.jsp" ).forward( request, response );

                }}

                int statut = stmt.executeUpdate("INSERT INTO user (username, email, mdp) "
                        + "VALUES ('" + id + "', '" + admail + "', '" + pwd + "');");
                System.out.println(statut);
                //while (rs.next())
                //    System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
                con.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(path + "/Accueil");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Blog/Inscription.jsp" ).forward( request, response );

    }
}
