<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<style type="text/css">  <%@include file="/WEB-INF/lib/form_style.css" %> </style>
<head>
<link rel="stylesheet" type="text/css" href="bootstrap.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
    
    <div class="login-page">
  <div class="form">
    <form class="login-form" action="editaNota" method="POST">
    <%int id = (Integer) request.getAttribute("id");
    String text = (String) request.getAttribute("conteudo");%>
    <h3>Atualizar Nota</h3><br>
      <input type="text" name="conteudo" value='<%=text%>'/>
      <input type ="hidden" name = "id" value= <%=id %>>
      <input id="botao" type="submit" value="Atualizar">
    </form>
  </div>
</div>
</body>
</html>