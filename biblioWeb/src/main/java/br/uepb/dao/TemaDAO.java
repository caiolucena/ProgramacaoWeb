package br.uepb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.model.AreaConhecimento;
import br.uepb.model.Tema;

public class TemaDAO {
	
	private Connection con;
	private static final Logger logger = LogManager.getLogger(TemaDAO.class);
	
	/**
	 * Método construtor
	 * @throws Exception
	 */
	public TemaDAO() throws Exception{
		con = Conexao.iniciarConexao();
	}
	
	/**
	 * Método para inserir Tema no banco de dados
	 * @param tema
	 * @throws SQLException
	 * @return true or false
	 */
	@SuppressWarnings("finally")
	public boolean createTema(Tema tema) {
		
		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("INSERT INTO tema(nome,areaconhecimento_id) VALUES (?,?)");
			stmt.setString(1, tema.getNome());
			stmt.setInt(2,tema.getArea().getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na inserção "+e);
		} catch (Exception e) {
			logger.error("Erro na inserção "+e);
		} finally{
			try {
				con.close();
				stmt.close();
				logger.info("Conexão Fechada na inserção");
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conexão na inserção "+e);
			}
		}
		return false;

	}
	
	/**
	 * Método para remover Tema do banco de dados
	 * @param tema
	 * @throws SQLException
	 * @return true or false
	 */
	@SuppressWarnings("finally")
	public boolean removeTema(Tema tema) {
		
		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("DELETE FROM tema WHERE id = ?");
			stmt.setInt(1, tema.getId());
			
			stmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			logger.error("Erro na remoção"+e);
		} catch (Exception e) {
			logger.error("Erro na remoção"+e);
		} finally {
			try {
				con.close();
				stmt.close();
				logger.info("Conexão Fechada na remoção");
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conexão na remoção "+e);
			}
		}
		return false;
			
	}
	
	/**
	 * Método para atualizar Tema no banco de dados
	 * @param tema
	 * @throws SQLException
	 * @return true or false
	 */
	@SuppressWarnings("finally")
	public boolean updateTema(Tema tema) {
		
		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("UPDATE tema SET nome = ? WHERE id = ?");
			stmt.setString(1, tema.getNome());
			stmt.setInt(2, tema.getId());
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na atualização "+e);
		} catch (Exception e) {
			logger.error("Erro na atualização "+e);
		} finally {
			try {
				con.close();
				stmt.close();
				logger.info("Conexão Fechada na atualização");
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conexão na atualização "+e);
			}
		}
		return false;	
	}
	
	/**
	 * Método para pesquisar Tema no banco de dados
	 * @param tema
	 * @throws SQLException
	 * @return true or false
	 */
	public ArrayList<Tema> searchTema(Tema tema){
		
		ArrayList<Tema> listaTema = new ArrayList<Tema>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("select t.id as 'tema_id', t.nome as 'tema_nome', a.id as 'area_id', a.nome as 'area_nome' from tema as t inner join area_conhecimento as a on t.areaconhecimento_id = a.id where t.nome like ?");
			stmt.setString(1,"%"+tema.getNome()+"%");			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Tema t = new Tema(rs.getInt("tema_id"),rs.getString("tema_nome"),new AreaConhecimento(rs.getInt("area_id"),rs.getString("area_nome")));
				listaTema.add(t);	
			}
			
		} catch (SQLException e) {
			logger.error("Erro na busca "+e);
		} catch (Exception e) {
			logger.error("Erro na busca "+e);
		} finally {
			try {
				con.close();
				stmt.close();
				logger.info("Conexão Fechada na busca");
				
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conexão na busca "+e);
			}
		}
		
		return listaTema;
	}
}
