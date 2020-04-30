<%--
  Created by IntelliJ IDEA.
  User: svenja
  Date: 28/04/2020
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*,com.*, java.util.* "%>
<html>
<head>
    <title>Inscription</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<h1>Inscription</h1>

<form action = "" method = "post">
    <fieldset>

        <legend>Tous les champs de ce formulaire sont obligatoires.</legend>


        <% if(request.getAttribute("erreur") != null) {%>
        <div class="erreur">Une erreur a été rencontrée : <%=request.getAttribute("erreur")%></div>
        <% } %>

        <label for = "id">Identifiant : </label>
        <input type ="text" id = "id" name = "user_id"  size="20" maxlength="20" required/>
        <br />

        <label for = "id">Adresse mail : </label>
        <input type ="email" id = "email" name = "user_mail" size="20" maxlength="60" required/>
        <br />

        <label for = "pwd">Mot de passe : </label>
        <input type = "password" id = "pwd" name = "user_pwd"  size="20" minlength="6" maxlength="20" required/>
        <br />

        <button type = "submit">S'inscrire</button>


    </fieldset>

</form>

<p>Vous avez déjà un compte ? Connectez-vous vous en cliquant <input type="button" value="ici" OnClick="window.location.href = 'http://localhost:8080/AE_Project_war_exploded/Connexion'" />
</p>
</body>
</html>
