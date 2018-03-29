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
import br.uepb.model.Editora;
import br.uepb.model.acervo.Revista;

public class RevistaDAO implements Item_Acervo<Revista>{

	private Connection con;
	private static final Logger logger = LogManager.getLogger(RevistaDAO.class);
	
	public RevistaDAO() throws Exception{
		con = Conexao.iniciarConexao();
	}
	@SuppressWarnings("finally")
	public boolean createItemAcervo(Revista revista) {		
		String	sql	=	"insert	into	revista"	+
				"(id,titulo,editora_id,data,edicao,num_pag)"	+
				"values	(?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try	{
			//	prepared	statement	para	inserção
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
		}catch	(SQLException	e)	{
			logger.error("Erro na inserção",e);
			return false;
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conexão Fechada");
				return true;
			}catch(SQLException e){
				logger.error("Erro no fechamento da Conexão",e);
				return false;
			}
		}

		
	}
	@SuppressWarnings("finally")
	public boolean removeItemAcervo(Revista revista) {
		// TODO Auto-generated method stub
		String	sql	=	"DELETE FROM revista"	+
				"WHERE id =?";
		PreparedStatement stmt = null;
	    try {
	    	con = Conexao.iniciarConexao();
	    	stmt =	con.prepareStatement(sql);
	    	stmt.setInt(1, revista.getId());
	    	stmt.executeUpdate();
	    }catch(SQLException e) {
	    	logger.error("Erro na remoção",e);
	    }finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conexão Fechada");
				return true;
			}catch(SQLException e){
				logger.error("Erro no fechamento da Conexão",e);
				return false;
			}
		}
	}

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
				logger.error("Erro ao fechar a conexão",e);
				return false;
			}
		}

	}

	public ArrayList<Revista> searchItemAcervo(Revista revista) {

		PreparedStatement stmt = null;
		ArrayList <Revista> revistas = new ArrayList <Revista>();
		ResultSet rs = null;
		try{
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("SELECT revista.id as 'id_revista', revista.titulo as 'titulo_revista', revista.data as 'data_revista'," + 
					"revista.edicao as 'edicao_revista', revista.num_pag as 'pag_revista', editora.id as 'id_editora', editora.nome as 'nome_editora'" + 
					"FROM revista INNER JOIN editora ON revista.editora_id = editora.id where revista.titulo like %?%;");
			stmt .setString(1,revista.getTitulo());
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Revista rev = new Revista();
				rev.setId(rs.getInt("id_revista"));
				rev.setEditora(new Editora(rs.getInt("id_editora"),rs.getString("nome_editora")));
				rev.setTitulo(rs.getString("titulo"));
				rev.setData((Date)rs.getDate("data"));
				rev.setEdicao(rs.getInt("edicao"));	
				rev.setNum_pag(rs.getInt("pag_revista"));
				
				revistas.add(revista);
			}
		
		}catch (SQLException e) {
			logger.error("Erro ao fazer a busca",e);
		} catch (Exception e) {
			logger.error("Erro ao abrir conexão",e);
		}finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			}catch(SQLException e){
				logger.error("Erro ao fechar a conexão",e);
			}
		}
		return revistas;
	}
}
