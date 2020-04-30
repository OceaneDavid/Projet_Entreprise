<%--
  Created by IntelliJ IDEA.
  User: svenja
  Date: 29/04/2020
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nouveau post</title>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>

<h1>Écriture d'un nouveau post</h1>

<form action="" method="post">
    <p>
        <label for="contenu">Saisissez le contenu de votre nouveau post : </label><br />
        <textarea name="contenu" id="contenu" maxlength="300" rows="10" cols="50"></textarea>
    </p>
    <button type = "submit">Ajouter le post</button>
</form>

</body>
</html>
