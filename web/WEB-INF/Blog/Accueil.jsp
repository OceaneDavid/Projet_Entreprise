
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*,com.*, java.util.* "%>

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
    <% ArrayList<ArrayList<String>> posts = (ArrayList<ArrayList<String>>) request.getAttribute("post");
        ArrayList<ArrayList<String>> com = (ArrayList<ArrayList<String>>) request.getAttribute("coms");

        if(posts != null){
        for(int i = 0; i < posts.size(); i++) {

            for(int x = 1; x < posts.get(i).size(); x = x + 2){
                out.println(posts.get(i).get(x));%>
    <br />
    <%
            if(com != null) {
            for(int j = 0; j < com.size(); j++) {
                for(int k = 1; k < com.get(j).size(); k = k + 2) {
                    if(com.get(j).get(k-1).equals(posts.get(i).get(x-1))){
                        out.println(com.get(j).get(k));
                       %> <br/> <%
                    }
                }
            }
            }%>

    <input type="button" value="Ajouter un commentaire" name="bouton" id="<%=posts.get(i).get(x-1)%>" OnClick="window.location.href = 'http://localhost:8080/AE_Project_war_exploded/Commentaire'" />
    <br />
    <br />
    <%}

        }%>



        <%}%>




</p>
</body>
</html>
