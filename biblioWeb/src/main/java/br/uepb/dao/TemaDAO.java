package br.uepb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.interfaces.DAO_Dependencia;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Tema;
/**
 * Essa classe � respons�vel por se conectar com o Banco de Dados para opera��es de inserir, atualizar, remover e buscar objetos do tipo Tema
 * @author EquipeACL
 */
public class TemaDAO implements DAO_Dependencia<Tema>{
	
	private Connection con;
	private static final Logger logger = LogManager.getLogger(TemaDAO.class);
	
	/**
	 * M�todo para inserir Tema no banco de dados
	 * @param tema, objeto do tipo Tema
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a opera��o de inser��o for bem sucedida
	 * @return false, se ocorrer algum erro na opera��o
	 */
	@SuppressWarnings("finally")
	public boolean createItemDependencia(Tema tema) {
		
		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("INSERT INTO tema(nome,areaconhecimento_id) VALUES (?,?)");
			stmt.setString(1, tema.getNome());
			stmt.setInt(2,tema.getArea().getId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na inser��o ",e);
		} catch (Exception e) {
			logger.error("Erro na inser��o ",e);
		} finally{
			try {
				con.close();
				stmt.close();
				logger.info("Conex�o Fechada na inser��o");
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conex�o na inser��o ",e);
			}
		}
		return false;

	}
	
	/**
	 * M�todo para remover Tema do banco de dados
	 * @param tema, objeto do tipo Tema
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a opera��o de remo��o for bem sucedida
	 * @return false, se ocorrer algum erro na opera��o
	 */
	@SuppressWarnings("finally")
	public boolean removeItemDependencia(Tema tema) {
		
		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("DELETE FROM tema WHERE id = ?");
			stmt.setInt(1, tema.getId());
			
			stmt.executeUpdate();
			return true;
			
		} catch (SQLException e) {
			logger.error("Erro na remo��o",e);
		} catch (Exception e) {
			logger.error("Erro na remo��o",e);
		} finally {
			try {
				con.close();
				stmt.close();
				logger.info("Conex�o Fechada na remo��o");
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conex�o na remo��o ",e);
			}
		}
		return false;
			
	}
	
	/**
	 * M�todo para atualizar Tema no banco de dados
	 * @param tema, objeto do tipo Tema
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a opera��o de atualiza��o for bem sucedida
	 * @return false, se ocorrer algum erro na opera��o
	 */
	@SuppressWarnings("finally")
	public boolean updateItemDependencia(Tema tema) {
		
		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("UPDATE tema SET nome = ? WHERE id = ?");
			stmt.setString(1, tema.getNome());
			stmt.setInt(2, tema.getId());
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na atualiza��o ",e);
		} catch (Exception e) {
			logger.error("Erro na atualiza��o ",e);
		} finally {
			try {
				con.close();
				stmt.close();
				logger.info("Conex�o Fechada na atualiza��o");
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conex�o na atualiza��o ",e);
			}
		}
		return false;	
	}
	
	/**
	 * M�todo para pesquisar Tema no banco de dados
	 * @param tema, objeto do tipo Tema
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return ArrayList<Tema> listaTema, lista de temas retornados pela busca
	 */
	public ArrayList<Tema> searchItemDependencia(String nome){
		
		ArrayList<Tema> listaTema = new ArrayList<Tema>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("select t.id as 'tema_id', t.nome as 'tema_nome', a.id as 'area_id', a.nome as 'area_nome' from tema as t inner join area_conhecimento as a on t.areaconhecimento_id = a.id where t.nome like ?");
			stmt.setString(1,"%"+nome+"%");			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Tema t = new Tema(rs.getInt("tema_id"),rs.getString("tema_nome"),new AreaConhecimento(rs.getInt("area_id"),rs.getString("area_nome")));
				listaTema.add(t);	
			}
			
		} catch (SQLException e) {
			logger.error("Erro na busca ",e);
		} catch (Exception e) {
			logger.error("Erro na busca ",e);
		} finally {
			try {
				con.close();
				stmt.close();
				logger.info("Conex�o Fechada na busca");
				
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conex�o na busca ",e);
			}
		}
		return listaTema;
	}
}
