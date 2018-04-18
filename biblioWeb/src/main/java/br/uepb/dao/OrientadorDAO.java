package br.uepb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.model.Orientador;
/**
 * Essa classe é responsável por se conectar com o Banco de Dados para operações de inserir, atualizar, remover e buscar objetos do tipo Orientador
 * @author EquipeACL
 */
public class OrientadorDAO {
	private Connection con;
	private static final Logger logger = LogManager.getLogger(OrientadorDAO.class);
		
	/**
	 * Método para inserir Orientador no banco de dados
	 * @param orientador, objeto do tipo Orientador
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a operação de inserção for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	public boolean createOrientador(Orientador orientador){
		String sql = "insert into orientador(nome,formacao)values(?,?)";
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, orientador.getNome());
			stmt.setString(2, orientador.getFormacao());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			if(e.getClass().equals(new com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException().getClass())){
				logger.error("Erro na inserção - Parametros null",e);
				return false;
			}
			logger.error("Erro na inserção",e);
		} catch (Exception e) {
			if(e.getClass().equals(new java.lang.NullPointerException().getClass())){
				logger.error("Erro na inserção - Parametros null",e);
				return false;
			}
			logger.error("Erro na inserção",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão fechada na inserção");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na inserção",e);
			}
		}
		return false;
	}
	
	/**
	 * Método para remover Orientador do banco de dados
	 * @param orientador, objeto do tipo Orientador
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a operação de remoção for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	public boolean removeOrientador(Orientador orientador) {
		String sql = "delete from orientador where id=?";
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,orientador.getId());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na remoção",e);
		} catch (Exception e) {
			logger.error("Erro naremoção",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão fechada na remoção");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na remoção",e);
			}
		}
		return false;
	}
	
	/**
	 * Método para atualizar Orientador no banco de dados
	 * @param orientador, objeto do tipo Orientador
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a operação de atualização for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	public boolean updateOrientador(Orientador orientador) {
		String sql = "update orientador set nome=?, formacao=? where id=?";
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,orientador.getNome());
			stmt.setString(2, orientador.getFormacao());
			stmt.setInt(3, orientador.getId());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na atualização",e);
		} catch (Exception e) {
			logger.error("Erro na atualização",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão fechada na atualização");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na atualização",e);
			}
		}
		return false;
	}
	
	/**
	 * Método para pesquisar Orientador no banco de dados
	 * @param orientador, objeto do tipo Orientador
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return ArrayList<Curso> orientadores, lista de orientadores retornados pela busca
	 */
	public ArrayList<Orientador> searchOrientador(Orientador orientador) {
		String sql = "select * from orientador where nome like ?";
		ArrayList<Orientador> orientadores = new ArrayList<Orientador>();
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,"%"+orientador.getNome()+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
	            Orientador o = new Orientador(rs.getInt("id"),rs.getString("nome"),rs.getString("formacao"));
	            orientadores.add(o);
	        }
		} catch (SQLException e) {
			logger.error("Erro na busca",e);
		} catch (Exception e) {
			logger.error("Erro na busca",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão fechada na busca");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na busca",e);
			}
		}
		return orientadores;
	}
}
