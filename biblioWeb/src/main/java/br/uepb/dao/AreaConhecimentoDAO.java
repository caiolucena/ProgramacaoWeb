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

public class AreaConhecimentoDAO {
	
	private Connection con;
	private static final Logger  logger = LogManager.getLogger(AreaConhecimentoDAO.class);
	
	/**
	 * Método Construtor
	 * @throws Exception
	 */
	public AreaConhecimentoDAO() throws Exception {
		con = Conexao.iniciarConexao();
	}
	
	/**
	 * Método para inserir Area de Conhecimento no banco de dados
	 * @param area
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true or false
	 */
	
	@SuppressWarnings("finally")
	public boolean createAreaConhecimento(AreaConhecimento area) {
		
		PreparedStatement stmt = null;

		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("INSERT INTO area_conhecimento(nome) VALUES(?)");
			stmt.setString(1, area.getNome());			
			stmt.executeUpdate();
			return true;
		} catch	(SQLException e)	{
			logger.error("Erro durante a inserção "+e);
		} catch (Exception e) {
			logger.error("Erro durante a inserção "+e);
		} finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conexão Fechada na inserção");
			
			}catch(SQLException e){
				logger.error("Erro ao fechar a conexão na inserção "+e);
			}
		}
		return false;
	}
    
	/**
	 * Método para pesquisar Area de Conhecimento no banco de dados
	 * @param area
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return ArrayList<AreaConhecimento> listaAreas
	 */
	
	public ArrayList<AreaConhecimento> searchAreaConhecimento(AreaConhecimento area){
		ArrayList<AreaConhecimento> listaAreas = new ArrayList<AreaConhecimento>();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			con = Conexao.iniciarConexao();
			//stmt = con.prepareStatement("SELECT A.id AS id_area, A.nome AS nome_area, T.id AS id_tema, T.nome as nome_tema FROM area_conhecimento AS A INNER JOIN tema AS T ON A.tema_id = T.id WHERE A.nome LIKE '%?%'");
			stmt = con.prepareStatement("SELECT * from area_conhecimento where nome LIKE ?");
			stmt.setString(1,"%"+area.getNome()+"%");			
			rs = stmt.executeQuery();			
			while(rs.next()) {
				AreaConhecimento a = new AreaConhecimento(rs.getInt("id"),rs.getString("nome"));				
				listaAreas.add(a);
			}
		} catch (SQLException e) {
			logger.error("Erro durante a busca "+e);
			return null;
		} catch (Exception e) {
			logger.error("Erro durante a busca "+e);
			return null;
		} finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conexão Fechada na busca");
			}catch(SQLException e){
				logger.error("Erro ao fechar a conexão na busca "+e);	
			}		
		}
		
		return listaAreas;
		
	}
	
	/**
	 * Método para remover Area de Conhecimento do banco de dados
	 * @param area
	 * @throws SQLException,
	 * @throws JavaLangException
	 * @return true or false
	 */
	
	@SuppressWarnings("finally")
	public boolean removeAreaConhecimento(AreaConhecimento area) {
		
		PreparedStatement stmt = null;

		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("DELETE FROM area_conhecimento WHERE id =?");
			stmt.setInt(1, area.getId());
		
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro durante a remoção "+e);
			
		} catch (Exception e) {
			logger.error("Erro durante a remoção "+e);
		} finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conexão Fechada na remoção");
			}catch(SQLException e){
				logger.error("Erro ao fechar a conexão na remoção "+e);
			}
		}
		return false;

	}
	
	/**
	 * Método para atualizar Area de Conhecimento no banco de dados
	 * @param area
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true or false
	 */
	
	@SuppressWarnings("finally")
	public boolean updateAreaConhecimento(AreaConhecimento area) {
		
		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("UPDATE area_conhecimento SET nome = ? WHERE id = ?");
			stmt.setString(1, area.getNome());
			stmt.setInt(2, area.getId());			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro ao atualizar "+e);
		} catch (Exception e) {
			logger.error("Erro ao atualizar "+e);
		} finally {
			
			try {
				con.close();
				stmt.close();
				logger.info("Conexão fechada na atualização");
			} catch (SQLException e) {
				logger.error("Erro ao fechar conexão na atualização "+e);
			}
		}
		return false;
	}
}
