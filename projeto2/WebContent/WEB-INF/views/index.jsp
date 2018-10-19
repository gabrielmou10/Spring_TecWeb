<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style type="text/css">  <%@include file="/WEB-INF/lib/form_style.css" %>
<%@include file="/WEB-INF/lib/style.css" %></style>

<link  href="http://fonts.googleapis.com/css?family=Reenie+Beanie:regular" rel="stylesheet"type="text/css">

<head>




<%@ page import="java.util.*,mvc.model.*" %>
<%@ page import="java.util.*,mvc.controller.*" %>



<%List<Notas> listaNotas = (ArrayList<Notas>) request.getAttribute("notas");
String gif_url = (String) request.getAttribute("gif_url");%>

</head>
<body>

<%-- 
<div class= "button-flex">
<div class="form">
	<form class="login-form" action = "FiltraNotas">
		<input type = "text" name="palavra_filtrada">
		<input type = "hidden" value =<%= id_usuario %> name = "pessoa_id">
		<input id="home" class="button" type = "submit" value="Filtrar notas">
	</form></div></div>  --%>
	
<div class="flex-container">
<% for (Notas nota : listaNotas) { 
System.out.println(nota.getPessoa_id());
System.out.println("nota");%>
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
		<input type='hidden' name='conteudo' value="<%= nota.getConteudo()%>">
		<input id="editar" class="button" type = "submit" value = "Editar">
	</form></div>
	</div>
  </div>
<% } %>
</div>
<h3 id="gif" class="center">Gif do dia:</h3>
<img src="<%=gif_url%>" alt="Erro de carregamento" class="center" style="float:center;"/>


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
	
	<%--
	<div>
	<form action="OrdenaNotas">
		<input type="hidden" name='pessoa_id' value=<%=id_usuario %>>
		<input id="home" class="button" type = "submit" value="Ordenar: Atualização">
	</form></div>  --%>

	</div>

</body>
</html>