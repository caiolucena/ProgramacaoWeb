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
import br.uepb.interfaces.DAO_Acervo;
import br.uepb.model.Editora;
import br.uepb.model.acervo.Revista;
/**
 * Essa classe � respons�vel por se conectar com o Banco de Dados para opera��es de inserir, atualizar, remover e buscar objetos do tipo Revista
 * Ela implementa a interface Item_Acervo passando o tipo Revista.
 * @author EquipeACL
 */
public class RevistaDAO implements DAO_Acervo<Revista>{

	private Connection con;
	private static final Logger logger = LogManager.getLogger(RevistaDAO.class);
	
	/**
	 * M�todo para inserir Revista no banco de dados
	 * @param revista, objeto do tipo Revista
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a opera��o de inser��o for bem sucedida
	 * @return false, se ocorrer algum erro na opera��o
	 */
	@SuppressWarnings("finally")
	public boolean createItemAcervo(Revista revista) {		
		String	sql	=	"insert	into	revista"	+
				"(id,titulo,editora_id,data,edicao,num_pag)"	+
				"values	(?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try	{
			//	prepared	statement	para	inser��o
			con = Conexao.iniciarConexao();
			stmt =	con.prepareStatement(sql);
			//	seta	os	valores
			stmt.setInt(1,revista.getId());
			stmt.setString(2,revista.getTitulo());
			stmt.setInt(3,revista.getEditora().getId());
			stmt.setDate(4, new java.sql.Date(revista.getData().getTime()));
			stmt.setInt(5,revista.getEdicao());
			stmt.setInt(6, revista.getNum_pag());
			//	executa
			stmt.execute();
			return true;
		}catch	(SQLException	e)	{
			logger.error("Erro na inser��o",e);
		} catch (Exception e) {
			logger.error("Erro na inser��o",e);
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conex�o Fechada");
				
			}catch(SQLException e){
				logger.error("Erro no fechamento da Conex�o",e);
				
			}
		}
		return false;
		
	}
	
	/**
	 * M�todo para remover uma Revista do banco de dados, utilizando seu id
	 * @param revista, objeto do tipo Revista
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a opera��o de remo��o for bem sucedida
	 * @return false, se ocorrer algum erro na opera��o
	 */
	@SuppressWarnings("finally")
	public boolean removeItemAcervo(Revista revista) {
		// TODO Auto-generated method stub
		String	sql	=	"DELETE FROM revista WHERE id =?"; 
				
		PreparedStatement stmt = null;
	    try {
	    	con = Conexao.iniciarConexao();
	    	stmt =	con.prepareStatement(sql);
	    	stmt.setInt(1, revista.getId());
	    	stmt.executeUpdate();
	    }catch(SQLException e) {
	    	logger.error("Erro na remo��o",e);
	    }finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conex�o Fechada");
				return true;
			}catch(SQLException e){
				logger.error("Erro no fechamento da Conex�o",e);
				return false;
			}
		}
	}

	/**
	 * M�todo para atualizar uma Revista no banco de dados
	 * @param revista, objeto do tipo Revista
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a opera��o de atualiza��o for bem sucedida
	 * @return false, se ocorrer algum erro na opera��o
	 */
	@SuppressWarnings("finally")
	public boolean updateItemAcervo(Revista revista) {
		String	sql	=	"UPDATE revista set titulo=?,editora=?,data=?,edica=?,num_pag=? WHERE id =?";
		PreparedStatement stmt = null;
	    try {
	    	con = Conexao.iniciarConexao();
	    	stmt =	con.prepareStatement(sql);
			stmt.setString(1,revista.getTitulo());
			stmt.setInt(2,revista.getEditora().getId());
			stmt.setDate(3, new java.sql.Date(revista.getData().getTime()));
			stmt.setInt(4,revista.getEdicao());
			stmt.setInt(5, revista.getNum_pag());
	    	stmt.executeUpdate();
	    }catch(SQLException e) {
	    	logger.error("Erro ao fazer o update",e);
	    	return false;
	    }finally {
			try {
				stmt.close();
				con.close();
				return true;
			}catch(SQLException e){
				logger.error("Erro ao fechar a conex�o",e);
				return false;
			}
		}

	}

	/**
	 * M�todo para pesquisar uma Revista utilizando o titulo no banco de dados
	 * @param revista, objeto do tipo Revista
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return ArrayList<Revista> revistas, lista de revistas retornadas pela busca
	 */
	public ArrayList<Revista> searchItemAcervo(String titulo) {

		PreparedStatement stmt = null;
		ArrayList <Revista> revistas = new ArrayList <Revista>();
		ResultSet rs = null;
		try{
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("SELECT revista.id as 'id_revista', revista.titulo as 'titulo_revista', revista.data as 'data_revista'," + 
					" revista.edicao as 'edicao_revista', revista.num_pag as 'pag_revista', editora.id as 'id_editora', editora.nome as 'nome_editora'" + 
					" FROM revista INNER JOIN editora ON revista.editora_id = editora.id where revista.titulo like ?;");
			stmt .setString(1,"%"+titulo+"%");
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Revista rev = new Revista();
				rev.setId(rs.getInt("id_revista"));
				rev.setEditora(new Editora(rs.getInt("id_editora"),rs.getString("nome_editora")));
				rev.setTitulo(rs.getString("titulo_revista"));
				rev.setData((Date)rs.getDate("data_revista"));
				rev.setEdicao(rs.getInt("edicao_revista"));	
				rev.setNum_pag(rs.getInt("pag_revista"));
				
				revistas.add(rev);
			}
		
		}catch (SQLException e) {
			logger.error("Erro ao fazer a busca",e);
		} catch (Exception e) {
			logger.error("Erro ao abrir conex�o",e);
		}finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				logger.error("Erro ao fechar a conex�o",e);
			}
		}
		return revistas;
	}
}
