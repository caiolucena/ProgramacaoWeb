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
	private static final Logger  logger = LogManager.getLogger(CidadeDAO.class);
	
	public AreaConhecimentoDAO() throws Exception {
		con = Conexao.iniciarConexao();
	}
	
	@SuppressWarnings("finally")
	public boolean createAreaConhecimento(AreaConhecimento area) {
		
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("INSERT INTO area_conhecimento(nome,tema_id) VALUES(?,?)");
			stmt.setString(1, area.getNome());
			stmt.setInt(2, area.getTema().getId());
			
			stmt.executeUpdate();
			
		} catch	(SQLException e)	{
			e.printStackTrace();
			logger.error("AreaConhecimentoDAO: erro durante a inserção");
			return false;
		} finally {
			try {
				stmt.close();
				con.close();
				logger.info("AreaConhecimentoDAO: Conexão Fechada");
				return true;
			}catch(SQLException e){
				e.printStackTrace();
				logger.error("AreaConhecimentoDAO: erro ao fechar a conexão");
				return false;
			}
		}
	}
	
	public AreaConhecimento searchAreaConhecimento(AreaConhecimento area) {
		
		AreaConhecimento retorno = null;
		Tema tema = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT A.id AS id_area, A.nome AS nome_area, T.id AS id_tema, T.nome as nome_tema FROM area_conhecimento AS A INNER JOIN tema AS T ON A.tema_id = T.id WHERE A.nome LIKE '?'");
			stmt.setString(1,area.getNome());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				tema = new Tema(rs.getInt("id_tema"),rs.getString("nome_tema"));
				retorno = new AreaConhecimento(rs.getInt("id_area"),rs.getString("nome_area"),tema);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("AreaConhecimentoDAO: erro durante a busca da Area do Conhecimento "+area.getNome());
			return retorno;
		} finally {
			try {
				stmt.close();
				con.close();
				logger.info("AreaConhecimentoDAO: Conexão Fechada");
			}catch(SQLException e){
				e.printStackTrace();
				logger.error("AreaConhecimentoDAO: Erro ao fechar a conexão");	
			}		
		}
		
		return retorno;
	}
	
	public ArrayList<AreaConhecimento> searchAllAreaConhecimento(){
		ArrayList<AreaConhecimento> listaAreas = new ArrayList<AreaConhecimento>();
		AreaConhecimento area = null;
		Tema tema = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT A.id AS id_area, A.nome AS nome_area, T.id AS id_tema, T.nome as nome_tema FROM area_conhecimento AS A INNER JOIN tema AS T ON A.tema_id = T.id");
					
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				tema = new Tema(rs.getInt("id_tema"),rs.getString("nome_tema"));
				area = new AreaConhecimento(rs.getInt("id_area"),rs.getString("nome_area"),tema);
				listaAreas.add(area);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("AreaConhecimentoDAO: erro durante a busca da lista das Areas do Conhecimento");
			return null;
		} finally {
			try {
				stmt.close();
				con.close();
				logger.info("AreaConhecimentoDAO: Conexão Fechada");
			}catch(SQLException e){
				e.printStackTrace();
				logger.error("AreaConhecimentoDAO: Erro ao fechar a conexão");	
			}		
		}
		
		return listaAreas;
		
	}
	
	@SuppressWarnings("finally")
	public boolean removeAreaConhecimento(AreaConhecimento area) {
		
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("DELETE FROM area_conhecimento WHERE id =? AND nome = ?");
			stmt.setInt(1, area.getId());
			stmt.setString(2, area.getNome());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("CidadeDAO: erro durante a remoção da Area de Conhecimento "+area.getNome());
			return false;
			
		} finally {
			try {
				stmt.close();
				con.close();
				logger.info("CidadeDAO: Conexão Fechada");
				return true;
			}catch(SQLException e){
				e.printStackTrace();
				logger.error("CidadeDAO: Erro ao fechar a conexão");
				return false;
			}
		}
		
	}
	
}
