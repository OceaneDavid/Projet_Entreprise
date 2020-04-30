<%--
  Created by IntelliJ IDEA.
  User: svenja
  Date: 30/04/2020
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>Nouveau commentaire</title>
</head>
<body>

<h1>Écriture d'un commentaire</h1>

<form action="" method="post">
    <p>
        <label for="comm">Saisissez le contenu de votre commentaire : </label><br />
        <textarea name="comm" id="comm" maxlength="300" rows="10" cols="50"></textarea>
    </p>
    <button type = "submit">Ajouter le commentaire</button>
</form>

</body>
</html>
