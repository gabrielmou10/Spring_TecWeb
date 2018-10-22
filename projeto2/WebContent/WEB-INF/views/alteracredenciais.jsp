<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session = "true"%>
<!DOCTYPE html>
<html>
<style type="text/css">  <%@include file="/WEB-INF/lib/form_style.css" %> </style>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.*,mvc.model.*" %>
<%@ page import="java.util.*,mvc.controller.*" %>
	
<div class="login-page">
	<div class="form">
	<%int pessoa_id = (Integer) request.getAttribute("pessoa_id");
	System.out.println(pessoa_id);%>
    <form class="login-form" action = "editarPessoa" method="POST">

    <h3>Alterar Credenciais</h3>
      <input type="text" name="log" placeholder="Novo login"/>
      <input type="password" name="sen" placeholder="Nova senha"/>
      <input type ="hidden" name = "id_usuario" value=<%=pessoa_id%>>
      <input id="botao" type="submit" value="Mudar">
    </form>
	</div>
</div>
</body>
</html>
</body>
</html>