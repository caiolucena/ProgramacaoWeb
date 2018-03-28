package br.uepb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.model.Autor;
import br.uepb.model.Editora;

public class EditoraDAO {
	private Connection con;
	private static final Logger logger = LogManager.getLogger(EditoraDAO.class);
	
	/**
	 * Método Construtor
	 * @throws Exception
	 */
	public EditoraDAO() throws Exception{
		con = Conexao.iniciarConexao();		
	}
	
	/**
	 * Metodo para inserir Editora no banco de dados
	 * @param editora
	 * @throws SQLException
	 * @return true or false
 	 */
	
	@SuppressWarnings("finally")
	public boolean createEditora(Editora editora){
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
				return false;
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
	 * @param editora
	 * @throws SQLException
	 * @return true or false
	 */
	
	@SuppressWarnings("finally")
	public boolean removeEditora(Editora editora) {
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
	 * @param editora
	 * @throws SQLException
	 * @return true or false
	 */
	
	public boolean updateEditora(Editora editora){
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
	 * @param editora
	 * @throws SQLException
	 * @return ArrayList<Editora> editoras
	 */
	
	public ArrayList<Editora> searchEditora(Editora editora) {//busca varias editoras pelo nome
		String sql = "select * from editora where nome like ?";
		ArrayList<Editora> editoras = new ArrayList<Editora>();
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,"%"+editora.getNome()+"%");
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
	 * @param id_editora
	 * @throws SLQException
	 * @return editora
	 */
	
	public Editora searchEditora(int id_editora) {//busca uma unica editora pelo id
		String sql = "select * from editora where id=?;";
		Editora editora = new Editora();
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,id_editora);
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
