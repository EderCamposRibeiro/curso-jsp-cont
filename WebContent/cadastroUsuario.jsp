<%@page import="beans.BeanCursoJsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
		<title>Cadastro de usuário</title>
		<link rel="stylesheet" href="resources/css/cadastro.css">
		<!-- Adicionando JQuery -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
</head>

<body>
	<a href="acessoliberado.jsp"><img src="resources/img/home.png" width="35px" height="35px" title="Voltar" alt="Voltar"></a>
	<br>
	<a href="index.jsp"><img src="resources/img/icon.png" width="35px" height="35px" title="Sair" alt="Sair"></a>
	<h1 align="center">Cadastro de usuário</h1>

	<form action="salvarUsuario" method="post" id="formUser"
		onsubmit="return validarCampos()? true : false;"
		enctype="multipart/form-data">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Codigo:</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" class="field-long"></td>
						<td>Cep:</td>
						<td><input type="text" id="cep" name="cep" maxlength="9"
							onblur="consultaCep();" value="${user.cep}"
							placeholder="Digite o Cep"></td>
					</tr>
					<tr>
						<td>Login:</td>
						<td><input type="text" id="login" name="login" maxlength="10"
							value="${user.login}" placeholder="Nome para Login"></td>
						<td>Rua:</td>
						<td><input type="text" id="rua" name="rua" maxlength="50"
							value="${user.rua}" placeholder="Rua ou conjunto"></td>
					</tr>
					<tr>
						<td>Senha:</td>
						<td><input type="password" id="senha" name="senha" maxlength="10"
							value="${user.senha}" placeholder="Crie aqui a senha"></td>
						<td>Bairro:</td>
						<td><input type="text" id="bairro" name="bairro" maxlength="50"
							value="${user.bairro}"
							placeholder="Bairro/Região administrativa no (DF)"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome" maxlength="50"
							value="${user.nome}" placeholder="Informe o nome do usuário"></td>
						<td>Cidade:</td>
						<td><input type="text" id="cidade" name="cidade" maxlength="50"
							value="${user.cidade}" placeholder="Digite a cidade"></td>
					</tr>
					<tr>
						<td>IBGE:</td>
						<td><input type="text" id="ibge" name="ibge" maxlength="20"
							value="${user.ibge}" placeholder="Numero de registro no IBGE"></td>
						<td>Estado:</td>
						<td><input type="text" id="estado" name="estado" maxlength="30"
							value="${user.estado}" placeholder="Digite a UF"></td>
					</tr>
					<tr>
						<td>Ativo:</td>
						<td><input type="checkbox" id="ativo" name="ativo"
						<%
							if (request.getAttribute("user") != null) {
								
								beans.BeanCursoJsp user = (beans.BeanCursoJsp) request.getAttribute("user");
								if (user.isAtivo()) {
									out.print(" ");
									out.print("checked=\"checked\"");
									out.print(" ");
								}
							}
						%>
						>
						</td>
						<td>Foto:</td>
						<td><input type="file" name="foto"/>
						</td>

					</tr>
					<tr>
						<td>Sexo:</td>
						<td>
							<input type="radio" name="sexo"
							<% 
								if (request.getAttribute("user") != null) {
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									if (user.getSexo().equalsIgnoreCase("masculino")){
										out.print(" ");
										out.print("checked=\"checked\"");
										out.print(" ");
									}
									
								}
							%>
							value="masculino">M</input>
							<input type="radio" name="sexo" 
							<% 
								if (request.getAttribute("user") != null) {
									BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
									if (user.getSexo().equalsIgnoreCase("feminino")){
										out.print(" ");
										out.print("checked=\"checked\"");
										out.print(" ");
									}
									
								}
							%>							
							value="feminino">F</input>
						</td>
						<td>
						   Curriculo:	
						</td>
						<td><input type="file" name="curriculo" value="curriculo" >
						 </td>

					</tr>	
					<tr>
						<td>Perfil:</td>
						<td>
							<select id="perfil" name="perfil" style="width: 180px; height: 30px" >
								<option value="nao_informado" 
								<% 
									if (request.getAttribute("user") != null) {
										BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
										if (user.getPerfil() == null){
											out.print(" ");
											out.print("selected=\"selected\"");
											out.print(" ");
										}
									}
								%>								
								>[--SELECIONE--]</option>
								<option value="administrador"
								<% 
									if (request.getAttribute("user") != null ) {
										BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
										if (user.getPerfil() != null){
											if (user.getPerfil().equalsIgnoreCase("administrador")){
												out.print(" ");
												out.print("selected=\"selected\"");
												out.print(" ");
											}
										}
									}
								%>									
								>Administrador(a)</option>
								<option value="funcionario"
								<% 
									if (request.getAttribute("user") != null) {
										BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
											if (user.getPerfil() != null){
												if (user.getPerfil().equalsIgnoreCase("funcionario")){
													out.print(" ");
													out.print("selected=\"selected\"");
													out.print(" ");
												}
											}
									}
								%>									
								>Funcionário(a)</option>
								<option value="gerente"
								<% 
									if (request.getAttribute("user") != null) {
										BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
											if (user.getPerfil() != null){
												if (user.getPerfil().equalsIgnoreCase("gerente")){
													out.print(" ");
													out.print("selected=\"selected\"");
													out.print(" ");
												}
											}
									}
								%>										
								>Gerente</option>
								<option value="secretario"
								<% 
									if (request.getAttribute("user") != null) {
										BeanCursoJsp user = (BeanCursoJsp) request.getAttribute("user");
											if (user.getPerfil() != null){
												if (user.getPerfil().equalsIgnoreCase("secretario")){
													out.print(" ");
													out.print("selected=\"selected\"");
													out.print(" ");
												}
											}
									}
								%>									
								>Secretário(a)</option>
							</select>
						</td>
						<td></td>
						<td></td>
					</tr>									
					<tr>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>

	<h3 align="center" style="color: red;">${msg}</h3>
	
	<form method="post" action="servletPesquisa" style="width: 90%">
	<ul class="form-style-1">
		<li>
			<table>
				<tr>
					<td>Descrição:<input type="text" id="descricaoconsulta" name="descricaoconsulta" style="width: 70%"></td>
					<td><input type="submit" value="Pesquisar"></td>			
			    </tr>
			</table>
		</li>
	</ul>
	</form>
	
	<div class="container">
		<table class="responsive-table">
			<caption>Usuários cadastrados</caption>
			<thead>
				<tr>
					<th scope="col">Id Usuário</th>
					<th scope="col">Login</th>
					<th scope="col">Foto</th>
					<th scope="col">Currículo</th>
					<th scope="col">Nome</th>
					<th scope="col">Cep</th>
					<th scope="col">Rua</th>
					<th scope="col">Bairro</th>
					<th scope="col">Cidade</th>
					<th scope="col">UF</th>
					<th scope="col">Telefones</th>					
					<th scope="col">Excluir</th>
					<th scope="col">Editar</th>


				</tr>
			</thead>

			<tbody>
				<c:forEach items="${usuarios}" var="user">
					<tr>
						<th scope="row"><c:out value="${user.id}"></c:out></th>
						<td data-title="Login"><c:out value="${user.login}"></c:out>
						</td>
						<c:if test="${user.fotoBase64Miniatura.isEmpty() == false || user.fotoBase64Miniatura != null }">
							<td data-title="Foto"> <a href="salvarUsuario?acao=download&tipo=imagen&user=${user.id}">
						<img src='<c:out value="${user.fotoBase64Miniatura}"/>' alt="Imagem User" title="Imagem User" width="32px" height="32px"></a>
						</td>
						</c:if>
						<c:if test="${user.fotoBase64Miniatura == null }">
							<td data-title="Foto">
							<img src="resources/img/userpadrao.png" width="32px" height="32px" onclick="alert('Não possui Imagem')" title="Sem Foto" alt="Sem Foto">
						</td>
						</c:if>
						<c:if test="${user.curriculoBase64 != null }">
						<td data-title="Currículo"> <a href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}">
							<img alt="Currículo" title="Currículo" src="resources/img/pdf.png" width="32px" height="32px"> </a>
						</td>
						</c:if>	
						<c:if test="${user.curriculoBase64 == null }">
						<td data-title="Curriculo"> 
							<img title="Sem currículo" alt="Sem currículo" src="resources/img/nopdf.png" width="32px" height="32px" onclick="alert('Não possui PDF')"> 
						</td>
						</c:if>							

						
											
						<!--  <td data-title="Senha"><c:out value="${user.senha}"></c:out></td>-->
						<td data-title="Nome"><c:out value="${user.nome}"></c:out></td>
						<!-- <td data-title="Telefone"><c:out value="${user.telefone}"></c:out>
						</td>-->
						<td data-title="Cep"><c:out value="${user.cep}"></c:out></td>
						<td data-title="Rua"><c:out value="${user.rua}"></c:out></td>
						<td data-title="Bairro"><c:out value="${user.bairro}"></c:out>
						</td>
						<td data-title="Cidade"><c:out value="${user.cidade}"></c:out>
						</td>
						<td data-title="Estado"><c:out value="${user.estado}"></c:out>
						</td>
						<!-- <td data-title="IBGE"><c:out value="${user.ibge}"></c:out></td> -->
						<td data-title="Telefones"><a
							href="salvarTelefones?acao=addFone&user=${user.id}"> <img
								src="resources/img/telefone.png" width="20px" height="20px"
								title="Telefones" alt="Telefones">
						</a></td>						
						<td data-title="Excluir"><a
							href="salvarUsuario?acao=delete&user=${user.id}" onclick="return confirm('Confirmar a exclusão?')"> <img
								src="resources/img/icon.png" width="20px" height="20px"
								title="Excluir" alt="Excluir">
						</a></td>
						<td data-title="Editar"><a
							href="salvarUsuario?acao=editar&user=${user.id}"> <img
								src="resources/img/editar.png" width="20px" height="20px"
								title="Editar" alt="Editar">
						</a></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert('Informe o Login')
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert('Informe A Senha')
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert('Informe o Nome')
				return false;
			} else if (document.getElementById("telefone").value == '') {
				alert('Informe o Telefone')
				return false;
			} else if (document.getElementById("cep").value == '') {
				alert('Informe o Cep')
				return false;
			}
			return true;
		}

		function consultaCep() {
			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#estado").val(dados.uf);
							$("#ibge").val(dados.ibge);
						} //end if.
						else {
							limpa_formulário_cep()
							alert("CEP não encontrado.");
						}
					});

		}
		function limpa_formulário_cep() {
			// Limpa valores do formulário de cep.
			$("#cep").val("");
			$("#rua").val("");
			$("#bairro").val("");
			$("#cidade").val("");
			$("#estado").val("");
			$("#ibge").val("");
		}
	</script>

</body>

</html>