<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style type="text/css">  <%@include file="/WEB-INF/lib/form_style.css" %>
<%@include file="/WEB-INF/lib/style.css" %></style>

<link  href="http://fonts.googleapis.com/css?family=Reenie+Beanie:regular" rel="stylesheet"type="text/css">

<head>

<meta charset="UTF-8">


<%@ page import="java.util.*,mvc.model.*" %>
<%@ page import="java.util.*,mvc.controller.*" %>



<%List<Notas> listaNotas = (ArrayList<Notas>) request.getAttribute("notas");
String gif_url = (String) request.getAttribute("gif_url");
String filtro = (String) request.getAttribute("filtro");%>
<title>Início</title>
</head>
<body>

<div class= "button-flex">
<div class="form">
	<form class="login-form" action = "inicio">
		<input type = "text" name="palavra_chave">
		<input id="home" class="button" type = "submit" value="<%=filtro%>">
	</form></div></div>
	
<div class="flex-container">
<% for (Notas nota : listaNotas) { %>
  <div class="grid-item">
  	<p id="texto_nota"><%=nota.getConteudo()%></p>
  	<h6 id="data"> Criado em <%=nota.getDateTime()%></h6>
  	<div class="button-flex">
  	
  	<div>
	<form action = "removeNota" method = "post">
		<input type='hidden' name='id' value="<%= nota.getId()%>">
		<input id="apagar" class="button" type ="submit" value = "Apagar">
	</form></div>
	
	<div>
	<form action = "paginaEditaNota">
		<input type='hidden' name='id' value="<%= nota.getId()%>">
		<input type='hidden' name='tipo' value=0>
		<input id="editar" class="button" type = "submit" value = "Editar">
	</form></div>
	
	<div>
	<form action = "paginaEditaNota">
		<input type='hidden' name='id' value="<%= nota.getId()%>">
		<input type='hidden' name='tipo' value=1>
		<input id="editar" class="button" type = "submit" value = "Traduzir">
	</form></div>
	
	</div>
  </div>
<% } %>
</div>



<div class= "button-flex">

<div>
	<form action = "paginaAdicionarNota" method = "get">
		<input id="home" class="button" type = "submit" value = "Crie uma nota">
	</form></div>
	<div>
	<form action="paginaEditarPessoa">
		<input id="home" class="button" type ="submit" value="Alterar login e senha">
	</form></div>
	<div>
	<form action="sair">
		<input id="home" class="button" type ="submit" value="Sair">
	</form></div>
	
	
	
		</div>
	
<br><hr><br><br>
<div class= "search">
<div class="form">
	<form class="login-form" action = "buscaGif">
		<input type = "text" name="palavra_gif">
		<input id="home" class="button" type = "submit" value="Buscar Giphy">
	</form></div></div>
	<object class="center" data="<%=gif_url%>" type="image/gif"></object>
	
	

	
	
	
	
	<%--
	<div>
	<form action="OrdenaNotas">
		<input type="hidden" name='pessoa_id' value=<%=id_usuario %>>
		<input id="home" class="button" type = "submit" value="Ordenar: Atualização">
	</form></div>  --%>



</body>
</html>