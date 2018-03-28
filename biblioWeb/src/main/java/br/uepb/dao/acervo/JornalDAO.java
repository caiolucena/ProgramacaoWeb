package br.uepb.dao.acervo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import org.apache.log4j.LogManager;

import br.uepb.dao.Conexao;
import br.uepb.dao.Item_Acervo;
import br.uepb.model.acervo.Jornal;

public class JornalDAO implements Item_Acervo<Jornal>{

	private Connection con;
	private static final Logger logger = LogManager.getLogger(JornalDAO.class);
	/**
	 * Método Construtor
	 * @throws Exception
	 */
	public JornalDAO() throws Exception{
		con = Conexao.iniciarConexao();
	}
	
	/**
	 * Método para inserir um Jornal no banco de dados
	 * @param jornal
	 * @throws SQLException
	 * @return true or false
	 */

	@SuppressWarnings("finally")
	public boolean createItemAcervo(Jornal jornal) {		
		String	sql	=	"insert	into jornal"	+
				"(titulo,data,edicao)"	+ //tirei o id daqui, porque é autoincremento
				"values	(?,?,?)";
		PreparedStatement stmt = null;
		try	{
			//	prepared	statement	para	inserção
			con = Conexao.iniciarConexao();
			stmt =	con.prepareStatement(sql);
			//	seta	os	valores
			//stmt.setInt(1,jornal.getId()); tirei o id daqui, porque é autoincremento
			stmt.setString(1,jornal.getTitulo());
			stmt.setDate(2, new java.sql.Date(jornal.getData().getTime()));
			stmt.setInt(3,jornal.getEdicao());
			//	executa
			stmt.execute();
			return true;
		}catch	(SQLException	e)	{
			logger.error("Erro na inserção",e);
			
		} catch (Exception e) {
			logger.error("Erro na inserção",e);
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conexão Fechada");
				
			}catch(SQLException e){
				logger.error("Erro ao fechar conexão",e);
				
			}
		}
		return false;
		
	}

	/**
	 * Método para remover um Jornal do banco de dados
	 * @param jornal
	 * @throws SQLException
	 * @return true or false
	 */
	
	@SuppressWarnings("finally")
	public boolean removeItemAcervo(Jornal jornal) {

		String	sql	= "DELETE FROM jornal WHERE id =?";
				
		PreparedStatement stmt = null;
	    try {
	    	con = Conexao.iniciarConexao();
	    	stmt =	con.prepareStatement(sql);
	    	stmt.setInt(1, jornal.getId());
	    	stmt.execute();
	    	logger.info("Remoção feita com sucesso");
	    	return true;
	    	
	    }catch(SQLException e) {
	    	logger.error("Erro ao fazer a remoção",e);

	    } catch (Exception e) {
	    	logger.error("Erro ao fazer a remoção",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão Fechada");
			}catch(SQLException e){
				logger.error("Erro ao fechar conexão",e);
			}
		}
	    return false;
		
	}

	/**
	 * Método para atualizar um jornal no banco de dados
	 * @param jornal
	 * @throws SQLException
	 * @return true or false
	 */
	
	@SuppressWarnings("finally")
	public boolean updateItemAcervo(Jornal jornal) {
		String	sql	=	"UPDATE jornal set titulo =?,data=?,edicao=?"	+
				"WHERE id =?";
		PreparedStatement stmt = null;
	    try {
	    	con = Conexao.iniciarConexao();
	    	stmt =	con.prepareStatement(sql);
	    	stmt.setString(1, jornal.getTitulo());
	    	stmt.setDate(2, (Date) jornal.getData());
	    	stmt.setInt(3, jornal.getEdicao());
	    	stmt.executeUpdate();
	    }catch(SQLException e) {
	    	logger.error("Erro ao fazer o update",e);
	    	return false;
	    }finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conexão Fechada");
				return true;
			}catch(SQLException e){
				logger.error("Erro ao fechar conexão",e);
				return false;
			}
		}

	}
	/**
	 * Método para pesquisar Jornal no banco de dados
	 * @param jornal
	 * @throws SQLException
	 * @return ArrayList <Jornal> jornais
	 */
	
	public ArrayList<Jornal> searchItemAcervo(Jornal jornal) {

		PreparedStatement stmt = null;
		ArrayList <Jornal> jornais = new ArrayList <Jornal>();
		ResultSet rs = null;
		
		try{
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("select * from jornal where titulo like ?");
			stmt .setString(1,"%"+ jornal.getTitulo()+ "%");
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Jornal jor = new Jornal();
				jor.setId(rs.getInt("id"));
				jor.setTitulo(rs.getString("titulo"));
				jor.setData((Date)rs.getDate("data"));
				jor.setEdicao(rs.getInt("edicao"));
				
				jornais.add(jornal);
			}
			
		}catch (SQLException e) {
			logger.error("Erro ao fazer a busca",e);
			
		} catch (Exception e) {
			logger.error("Erro ao abrir a conexão",e);
		}finally {
			try {
				
				rs.close();
				stmt.close();
				con.close();
				logger.info("Conexão Fechada");
				
			}catch(SQLException e){
				logger.error("Erro ao fechar conexão",e);
			}
		}
		return jornais;
	}
}
