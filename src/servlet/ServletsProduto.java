package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

@WebServlet("/salvarProduto")
public class ServletsProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public ServletsProduto() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos";
			String produto = request.getParameter("product");

			RequestDispatcher view = request
					.getRequestDispatcher("/cadastroProduto.jsp");			
			
			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(produto);

				request.setAttribute("produtos", daoProduto.listar());
				
			} else if (acao.equalsIgnoreCase("editar")) {

				BeanProduto beanCursoJsp = daoProduto.consultar(produto);
				
				request.setAttribute("product", beanCursoJsp);
				
			} else if (acao.equalsIgnoreCase("listartodos")) {

				request.setAttribute("produtos", daoProduto.listar());
				
			}
			
			request.setAttribute("categorias", daoProduto.listaCategorias());
			view.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");
			String categoria_id = request.getParameter("categoria_id");

			try {

				String msg = null;
				boolean podeInserir = true;

				if (valor == null || valor.isEmpty()) {
					msg = "Valor R$ deve ser informado";
					podeInserir = false;

				} else if (quantidade == null || quantidade.isEmpty()) {
					msg = "Quantidade deve ser informado";
					podeInserir = false;

				} else if (nome == null || nome.isEmpty()) {
					msg = "Nome deve ser informado";
					podeInserir = false;

				} else if (id == null || id.isEmpty()
						&& !daoProduto.validarNome(nome)) {// QUANDO
															// FDOR
															// PRODUTO
															// NOVO
					msg = "Produto já existe com o mesmo nome!";
					podeInserir = false;

				}

				BeanProduto produto = new BeanProduto();
				produto.setNome(nome);
				produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);

				if (quantidade != null && !quantidade.isEmpty()) {
					produto.setQuantidade(Double.parseDouble(quantidade));
				}

				if (valor != null && !valor.isEmpty()) {
					String valorParse = valor.replaceAll("\\.", "");//10500,20
					valorParse = valorParse.replaceAll("\\,", ".");//10500.00
					produto.setValor(Double.parseDouble(valorParse));
				}
				
				produto.setCategoria_id(!categoria_id.isEmpty() ? Long.parseLong(categoria_id) : null);
				
				if (msg != null) {
					request.setAttribute("msg", msg);
				} else if (id == null || id.isEmpty()
						&& daoProduto.validarNome(nome) && podeInserir) {

					daoProduto.salvar(produto);

				} else if (id != null && !id.isEmpty() && podeInserir) {
					daoProduto.atualizar(produto);
				}

				if (!podeInserir) {
					request.setAttribute("produto", produto);
				}

				RequestDispatcher view = request
						.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", daoProduto.listar());
				request.setAttribute("categorias", daoProduto.listaCategorias());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}