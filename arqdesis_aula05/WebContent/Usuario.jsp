<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Usuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cliente</title>
</head>
<body>
	<%Usuario usuario = (Usuario)request.getAttribute("usuario"); %>
	Id: <%=usuario.getId() %><br>
	Nome: <%=usuario.getNome() %><br>
	Perfil: <%=usuario.getPerfil() %><br>
	Senha: <%=usuario.getSenha() %><br>
</body>
</html>