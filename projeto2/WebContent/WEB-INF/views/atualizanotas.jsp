<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style type="text/css">  <%@include file="/WEB-INF/lib/form_style.css" %> </style>
<head>
<link rel="stylesheet" type="text/css" href="bootstrap.css">
<meta charset="ISO-8859-1">
<title>${titulo}</title>
</head>
<body>
	
    
    <div class="login-page">
  <div class="form">
    <form class="login-form" action="editaNota" method="POST">
    <h3>${titulo}</h3><br>
      <input type="text" name="conteudo" value="${conteudo}"/>
      <input type ="hidden" name = "id" value= "${id}">
      <input id="botao" type="submit" value="Atualizar">
    </form>
  </div>
</div>
</body>
</html>