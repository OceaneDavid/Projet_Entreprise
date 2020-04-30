<%--
  Created by IntelliJ IDEA.
  User: svenja
  Date: 27/04/2020
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*,com.*, java.util.* "%>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
<h1>Connexion</h1>

<form action = "" method = "post">

    <fieldset>

    <legend>Veuillez saisir vos identifiants</legend>

        <% if(request.getAttribute("erreur") != null) {%>
        <div class="erreur">Une erreur a été rencontrée : <%=request.getAttribute("erreur")%></div>
        <% } %>

        <label for = "mail">Adresse mail : </label>
        <input type ="email" id = "mail" name = "user_mail" size="20" maxlength="60" required>
        <br />

        <label for = "pwd">Mot de passe : </label>
        <input type = "password" id = "pwd" name = "user_pwd" size="20" minlength="6" maxlength="20" required>
        <br />

        <button type = "submit">Se connecter</button>

    </fieldset>

</form>

<p>Vous n'avez pas encore de compte ? Inscrivez vous en cliquant <input type="button" value="ici" OnClick="window.location.href = 'http://localhost:8080/AE_Project_war_exploded/Inscription'" />
</p>
</body>
</html>
