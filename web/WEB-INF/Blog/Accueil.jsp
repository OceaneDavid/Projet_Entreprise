
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*,com.*, java.util.* "%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Mon compte</title>
</head>
<body>

<h1>Bonjour <%=request.getAttribute("username")%> !</h1>

<input type="button" value="Ajouter un post" OnClick="window.location.href = 'http://localhost:8080/AE_Project_war_exploded/Post'" />
<br />
<br />

<h3>Vos posts : </h3>
<p>
    <% ArrayList<String> posts = (ArrayList<String>) request.getAttribute("post");
        if (posts!=null){
            if (posts.size() > 0)
            {
                for(int i = 0; i < posts.size(); i++) {
                    out.println(posts.get(i)); %>
    <br />
    <%}
    }
    }%>

</p>
</body>
</html>
