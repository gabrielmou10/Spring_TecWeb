<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style type="text/css">  <%@include file="/WEB-INF/lib/form_style.css" %> </style>
<head>
<link rel="stylesheet" type="text/css" href="bootstrap.css">
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>


	<div class="login-page">
		<div class="form">
			<form class="login-form" action="entrar" method="post">
				<h3>Login</h3>
				<input type="text" name="login" placeholder="usu�rio" /> <input
					type="password" name="senha" placeholder="senha" /> <input
					id="botao" type="submit" value="Entrar">
			</form>
			<form class="login-form" action="PaginaRegistrar" method="post">
				<input id="botao" type="submit" value="Registrar">
			</form>
		</div>
	</div>



</body>
</html>