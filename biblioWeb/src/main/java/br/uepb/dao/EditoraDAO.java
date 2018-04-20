package br.uepb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.interfaces.DAO_Dependencia;
import br.uepb.model.Autor;
import br.uepb.model.Editora;
/**
 * Essa classe é responsável por se conectar com o Banco de Dados para operações de inserir, atualizar, remover e buscar objetos do tipo Editora
 * @author EquipeACL
 */
public class EditoraDAO implements DAO_Dependencia<Editora>{
	private Connection con;
	private static final Logger logger = LogManager.getLogger(EditoraDAO.class);
	
	/**
	 * Metodo para inserir Editora no banco de dados
	 * @param editora, objeto do tipo Editora
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a operação de inserção for bem sucedida
	 * @return false, se ocorrer algum erro na operação
 	 */	
	@SuppressWarnings("finally")
	public boolean createItemDependencia(Editora editora){
		String sql = "insert into editora(nome)values(?)";
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, editora.getNome());
			stmt.execute();
			return true;
		} catch(SQLException e) {
			if(e.getClass().equals(new com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException().getClass())){
				logger.error("Erro na inserção - Parametros null",e);
			}else{
				logger.error("Erro na inserção ",e);
			}		
		} catch (Exception e) {
			if(e.getClass().equals(new java.lang.NullPointerException().getClass())){
				logger.error("Erro na inserção - Parametros null",e);
			}else{
				logger.error("Erro na inserção ",e);
			}
		}
		finally {
			try {
				con.close();
				logger.info("Conexão fechada na inserção");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na inserção ",e);
			}
		}
		return false;		
	}
	
	/**
	 * Método para remover Editora do banco de dados
	 * @param editora, objeto do tipo Editora
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a operação de remoção for bem sucedida
	 * @return false, se ocorrer algum erro na operação
 	 */	
	@SuppressWarnings("finally")
	public boolean removeItemDependencia(Editora editora) {
		String sql = "delete from editora where id=?";
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,editora.getId());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na remoção ",e);
		} catch (Exception e) {
			logger.error("Erro na remoção ",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão fechada na remoção");				
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na remoção ",e);
			}
		}
		return false;		
	}
	
	/**
	 * Método para atualizar Editora no banco de dados
	 * @param editora, objeto do tipo Editora
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a operação de atualização for bem sucedida
	 * @return false, se ocorrer algum erro na operação
 	 */		
	public boolean updateItemDependencia(Editora editora){
		String sql = "update editora set nome=? where id=?;";
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, editora.getNome());
			stmt.setInt(2,editora.getId());
			stmt.execute();
			return true;
		} catch(SQLException e) {
			if(e.getClass().equals(new com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException().getClass())){
				logger.error("Erro na atualização - Parametros null",e);
				return false;
			}else{
				logger.error("Erro na atualização ",e);
			}		
		} catch (Exception e) {
			if(e.getClass().equals(new java.lang.NullPointerException().getClass())){
				logger.error("Erro na atualização - Parametros null",e);
			}else{
				logger.error("Erro na atualização ",e);
			}
		}
		finally {
			try {
				con.close();
				logger.info("Conexão fechada na atualização");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na atualização ",e);
			}
		}
		return false;		
	}
	
	/**
	 * Método para pesquisar Editora no banco de dados
	 * @param editora, objeto do tipo Editora
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return ArrayList<Editora> editoras, lista de editoras retornadas pela busca
	 */	
	public ArrayList<Editora> searchItemDependencia(String nome) {//busca varias editoras pelo nome
		String sql = "select * from editora where nome like ?";
		ArrayList<Editora> editoras = new ArrayList<Editora>();
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,"%"+nome+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Editora e = new Editora();
	            e.setId(rs.getInt("id"));
	            e.setNome(rs.getString("nome"));
	            editoras.add(e);
	        }
		} catch (SQLException e) {
			logger.error("Erro na busca ",e);
		} catch (Exception e) {
			logger.error("Erro na busca ",e);
		}finally {
			try {
				con.close();
				logger.info("Conexao fechada na busca");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada ",e);
			}
		}
		return editoras;
	}
	
	/**
	 * Método para pesquisar Editora no banco de dados
	 * @param nome, String com o nome da editora buscada
	 * @throws SLQException
	 * @throws JavaLangException
	 * @return editora, objeto do tipo editora retornado pela busca
	 */	
	public Editora searchEditora(String nome) {//busca uma unica editora pelo nome
		String sql = "select * from editora where nome=?;";
		Editora editora = new Editora();
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,nome);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				editora.setId(rs.getInt("id"));
	            editora.setNome(rs.getString("nome"));
	        }
		} catch (SQLException e) {
			logger.error("Erro na busca ",e);
		} catch (Exception e) {
			logger.error("Erro na busca ",e);
		}finally {
			try {
				con.close();
				logger.info("Conexao fechada na busca");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada ",e);
			}
		}
		return editora;
	}
}
