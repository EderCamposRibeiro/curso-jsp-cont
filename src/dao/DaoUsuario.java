package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

/*
 * Classe DaoUsuario
 * Classe Que Provï¿½ os Mï¿½todos e Validaï¿½ï¿½es Para Manipular Dados, e Acesso e Manipulaï¿½ï¿½o do BD
 */
public class DaoUsuario {

	private Connection connection;
	
	/*
	 * Construtor DaoUsuario()
	 * Recebe um Objeto connection da Classe SingleConnection
	 */
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	/*
	 * Mï¿½todo salvar()
	 * Responsï¿½vel Por Fazer a Inserï¿½ï¿½o de Dados (INSERT) no BD
	 * @param BeanCursoJsp usuario = Objeto Usuï¿½rio da Classe BeanCursoJsp
	 */
	public void salvar(BeanCursoJsp usuario) {
		try {
			String sql = "INSERT INTO usuario(login, senha, nome, telefone, cep,"
					+ " rua, bairro, cidade, estado, ibge,"
					+ " fotobase64, contenttype, curriculobase64, contenttypecurriculo,"
					+ " fotobase64miniatura, ativo, sexo, perfil ) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getEstado());
			insert.setString(10, usuario.getIbge());
			insert.setString(11, usuario.getFotoBase64());
			insert.setString(12, usuario.getContentType());
			insert.setString(13, usuario.getCurriculoBase64());
			insert.setString(14, usuario.getContentTypeCurriculo());
			insert.setString(15, usuario.getFotoBase64Miniatura());
			insert.setBoolean(16, usuario.isAtivo());
			insert.setString(17, usuario.getSexo());
			insert.setString(18, usuario.getPerfil());
			insert.execute();
			connection.commit();
		} catch(Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/*
	 * Método listar()
	 * Responsável Por Listar Todos os Usuários do Sistema
	 */
	
	public List<BeanCursoJsp> listar(String descricaoConsulta) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE login <> 'admin' and nome like '%"+ descricaoConsulta+"%' ";
		return consultarUsuarios(sql);
	}
	
	
	
	public List<BeanCursoJsp> listar() throws Exception {
		
		String sql = "SELECT * FROM usuario WHERE login <> 'admin'";
		return consultarUsuarios(sql);	
	}

	private List<BeanCursoJsp> consultarUsuarios(String sql) throws SQLException {
		List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
				beanCursoJsp.setId(resultSet.getLong("id"));
				beanCursoJsp.setLogin(resultSet.getString("login"));
				beanCursoJsp.setSenha(resultSet.getString("senha"));
				beanCursoJsp.setNome(resultSet.getString("nome"));
				beanCursoJsp.setTelefone(resultSet.getString("telefone"));
				beanCursoJsp.setCep(resultSet.getString("cep"));
				beanCursoJsp.setRua(resultSet.getString("rua"));
				beanCursoJsp.setBairro(resultSet.getString("bairro"));
				beanCursoJsp.setCidade(resultSet.getString("cidade"));
				beanCursoJsp.setEstado(resultSet.getString("estado"));
				beanCursoJsp.setIbge(resultSet.getString("ibge"));
				beanCursoJsp.setContentType(resultSet.getString("contenttype"));
				beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
				beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
				beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
				beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
				beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
				beanCursoJsp.setSexo(resultSet.getString("sexo"));
				beanCursoJsp.setPerfil(resultSet.getString("perfil"));
				listar.add(beanCursoJsp);
			}
		return listar;	
	}
	
	/*
	 * Mï¿½todo delete()
	 * Responsï¿½vel Por Fazer a Exclusï¿½o (Delete) no BD
	 * @param String id = Atributo ID do Usuï¿½rio
	 */
	public void delete(String id) {
		if (id != null && !id.isEmpty()) {
			try {
				String sql = "DELETE FROM usuario WHERE id = '"+ id +"' AND login <> 'admin'";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.execute();
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Mï¿½todo consultar()
	 * Responsï¿½vel Por Fazer Consultas (SELECT) no BD
	 * @param String id = Atributo ID do Usuï¿½rio
	 */
	public BeanCursoJsp consultar(String id) throws Exception {
		String sql = "SELECT * FROM usuario WHERE id = '"+ id +"' AND login <> 'admin'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
				beanCursoJsp.setId(resultSet.getLong("id"));
				beanCursoJsp.setLogin(resultSet.getString("login"));
				beanCursoJsp.setSenha(resultSet.getString("senha"));
				beanCursoJsp.setNome(resultSet.getString("nome"));
				//beanCursoJsp.setTelefone(resultSet.getString("telefone"));
				beanCursoJsp.setCep(resultSet.getString("cep"));
				beanCursoJsp.setRua(resultSet.getString("rua"));
				beanCursoJsp.setBairro(resultSet.getString("bairro"));
				beanCursoJsp.setCidade(resultSet.getString("cidade"));
				beanCursoJsp.setEstado(resultSet.getString("estado"));
				beanCursoJsp.setIbge(resultSet.getString("ibge"));
				beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
				beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
				beanCursoJsp.setContentType(resultSet.getString("contenttype"));
				beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
				beanCursoJsp.setContentTypeCurriculo(resultSet.getString("contenttypecurriculo"));
				beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
				beanCursoJsp.setSexo(resultSet.getString("sexo"));
				beanCursoJsp.setPerfil(resultSet.getString("perfil"));
				return beanCursoJsp;
			}
		return null; 
	}
	
	/*
	 * Mï¿½todo validarLogin
	 * Responsï¿½vel Por Validar Login(Nï¿½o Pode Existir 1 Mesmo Login Para 2 Usuï¿½rios Diferentes)
	 * @param String login = Atributo Login do Usuï¿½rio
	 */
	public boolean validarLogin(String login) throws Exception {
		String sql = "SELECT COUNT(1) as qtde FROM usuario WHERE login = '"+ login +"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt("qtde") <= 0;
			}
		return false;
	}
	
	/*
	 * Mï¿½todo validarSenha
	 * Responsï¿½vel Por Validar Senha(Nï¿½o Pode Existir 1 Mesma Senha Para 2 Usuï¿½rios Diferentes)
	 * @param String senha = Atributo Senha do Usuï¿½rio
	 */
	public boolean validarSenha(String senha) throws Exception {
		String sql = "SELECT COUNT(1) as qtde FROM usuario WHERE senha = '"+ senha +"'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt("qtde") <= 0;
			}
		return false;
	}
	
	/*
	 * Mï¿½todo atualizar()
	 * Mï¿½todo Responsï¿½vel Por Atualizar os Dados (UPDATE) no BD
	 * @param BeanCursoJsp usuario = Objeto usuario da Classe BeanCursoJsp
	 */
	public void atualizar(BeanCursoJsp usuario) {
		try {
			StringBuilder sql = new StringBuilder();
			
			
			sql.append(" UPDATE usuario SET login = ?, senha = ?, nome = ?, telefone = ?, cep = ?, ");
			sql.append(" rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ? , ativo = ? , sexo = ?, perfil = ? ");
			
			if (usuario.isAtualizarImage()) {
				sql.append(", fotobase64 = ?, contenttype = ? ");
			}
			if (usuario.isAtualizarPdf()) {
				sql.append(", curriculobase64 = ?, contenttypecurriculo = ? ");
			}
			if (usuario.isAtualizarImage()) {
				sql.append(" , fotobase64miniatura = ? ");
			}
			
			sql.append(" WHERE id = "+ usuario.getId() + " AND login <> 'admin' ");
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getTelefone());
			preparedStatement.setString(5, usuario.getCep());
			preparedStatement.setString(6, usuario.getRua());
			preparedStatement.setString(7, usuario.getBairro());
			preparedStatement.setString(8, usuario.getCidade());
			preparedStatement.setString(9, usuario.getEstado());
			preparedStatement.setString(10, usuario.getIbge());
			preparedStatement.setBoolean(11, usuario.isAtivo());
			preparedStatement.setString(12, usuario.getSexo());
			preparedStatement.setString(13, usuario.getPerfil());
			
			if (usuario.isAtualizarImage() && usuario.isAtualizarPdf()) {
				preparedStatement.setString(14, usuario.getFotoBase64());
				preparedStatement.setString(15, usuario.getContentType());
				preparedStatement.setString(16, usuario.getCurriculoBase64());
				preparedStatement.setString(17, usuario.getContentTypeCurriculo());
				preparedStatement.setString(18, usuario.getFotoBase64Miniatura());

			} else if (usuario.isAtualizarImage() && !usuario.isAtualizarPdf()) {
				preparedStatement.setString(14, usuario.getFotoBase64());
				preparedStatement.setString(15, usuario.getContentType());
				preparedStatement.setString(16, usuario.getFotoBase64Miniatura());
			} else if (!usuario.isAtualizarImage() && usuario.isAtualizarPdf()){
				preparedStatement.setString(14, usuario.getCurriculoBase64());
				preparedStatement.setString(15, usuario.getContentTypeCurriculo());
			}
			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch(Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
