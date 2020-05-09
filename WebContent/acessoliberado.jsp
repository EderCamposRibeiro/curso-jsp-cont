<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center" style="padding-top: 10%;" >	
	<h3 >Seja bem vindo ao sistema em JSP!</h3>

	<table>
		<tr>
			<td>	<a href="salvarUsuario?acao=listartodos"> <img src="resources/img/login.png" width="100px" height="100px"
						title="Cadastrar/Alterar usuário" alt="Cadastrar/Alterar usuário"></a>
			</td>
			<td>	<a href="salvarProduto?acao=listartodos"> <img src="resources/img/produto.png" width="80px" height="80px"
						title="Cadastrar/Alterar produto" alt="Cadastrar/Alterar produto"></a>
			</td>
		</tr>
	</table>
	</div>


</body>
</html>