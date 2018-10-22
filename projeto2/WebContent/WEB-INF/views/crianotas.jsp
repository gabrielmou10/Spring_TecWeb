<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style type="text/css">  <%@include file="/WEB-INF/lib/form_style.css" %> </style>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<div class="login-page">
  <div class="form">
  <h3>Criar Nota</h3>
    <form class="login-form" action="adicionaNota" method="post"><br>
      <input type="text" name="conteudo" placeholder="conteudo"/>
      <input id="botao" type="submit" value="Criar">
    </form>
  </div>
</div>
</body>
