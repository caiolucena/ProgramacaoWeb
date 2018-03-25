package br.uepb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.model.Cidade;

public class CidadeDAO {
	
	private Connection con;
	private static final Logger  logger = LogManager.getLogger(CidadeDAO.class);
	
	public CidadeDAO() throws Exception {
		con = Conexao.iniciarConexao();
	}
	
	@SuppressWarnings("finally")
	public boolean createCidade(Cidade cidade) {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO cidade (Codigo,Nome,Uf) values (?,?,?)");
			stmt.setInt(1,cidade.getCodigo());
			stmt.setString(2, cidade.getNome());
			stmt.setString(3,cidade.getUf());
			
			stmt.executeUpdate();
			
		} catch	(SQLException e)	{
			e.printStackTrace();
			logger.error("CidadeDAO: erro durante a inserção");
			return false;
		} finally {
			try {
				stmt.close();
				con.close();
				logger.info("CidadeDAO: Conexão Fechada");
				return true;
			}catch(SQLException e){
				e.printStackTrace();
				logger.error("CidadeDAO: erro ao fechar a conexão");
				return false;
			}
		}	
	}
	
	public Cidade searchCidade(Cidade cidade) {
		Cidade retorno = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM cidade WHERE Nome = ?");
			stmt.setString(1,cidade.getNome());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
			retorno = new Cidade(
						rs.getInt("Id"),
						rs.getInt("Codigo"),
						rs.getString("Nome"),
						rs.getString(""));
			}
			
		} catch	(SQLException e)	{
			e.printStackTrace();
			logger.error("CidadeDAO: erro durante a busca por cidade");
			return retorno;
			
		} finally {
			try {
				stmt.close();
				con.close();
				logger.info("CidadeDAO: Conexão Fechada");
			}catch(SQLException e){
				e.printStackTrace();
				logger.error("CidadeDAO: Erro ao fechar a conexão");
			}
		}
		
		return retorno;	
	}
	
	public ArrayList<Cidade> searchAllCidades() {
		
		ArrayList<Cidade> listaCidades = new ArrayList<Cidade>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM cidades");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cidade cidade = new Cidade(
						rs.getInt("Id"),
						rs.getInt("Codigo"),
						rs.getString("Nome"),
						rs.getString(""));
				listaCidades.add(cidade);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("CidadeDAO: erro durante a busca por cidades");
			return null;
		} finally {
			try {
				stmt.close();
				con.close();
				logger.info("CidadeDAO: Conexão Fechada");
			}catch(SQLException e){
				e.printStackTrace();
				logger.error("CidadeDAO: Erro ao fechar a conexão");
			}
		}
		
		return listaCidades;
	}
	
	
	@SuppressWarnings("finally")
	public boolean removeCidade(Cidade cidade) {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM cidade WHERE Codigo=? AND Nome=? AND Uf=?");
			stmt.setInt(1, cidade.getCodigo());
			stmt.setString(2, cidade.getNome());
			stmt.setString(3, cidade.getUf());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("CidadeDAO: erro durante a remoção da cidade "+cidade.getNome());
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
	
	@SuppressWarnings("finally")
	public boolean updateCidade(Cidade cidade) {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE cidade SET Codigo=?, Nome=?, Uf=? WHERE id=?");
			stmt.setInt(1, cidade.getCodigo());
			stmt.setString(2, cidade.getNome());
			stmt.setString(3, cidade.getUf());
			stmt.setInt(4, cidade.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("CidadeDAO: erro durante a atualização da cidade "+cidade.getNome());
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
