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
import br.uepb.model.acervo.Revista;

public class RevistaDAO implements Item_Acervo<Revista>{

	private Connection con;
	private static final Logger logger = LogManager.getLogger(RevistaDAO.class);
	
	public RevistaDAO() throws Exception{
		con = Conexao.iniciarConexao();
	}

	public boolean createItemAcervo(Revista revista) {		
		String	sql	=	"insert	into	revista"	+
				"(id,titulo,editora,data,edicao,num_pag)"	+
				"values	(?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try	{
			//	prepared	statement	para	inserção
			stmt =	con.prepareStatement(sql);
			//	seta	os	valores
			stmt.setInt(1,revista.getId());
			stmt.setString(2,revista.getTitulo());
			stmt.setInt(3,revista.getEditora().getId());
			stmt.setDate(4, (Date) revista.getData());
			stmt.setString(5,revista.getEdicao());
			stmt.setInt(6, revista.getNum_pag());
			//	executa
			stmt.execute();
		}catch	(SQLException	e)	{
			e.printStackTrace();
			logger.error("RevistaDAO: erro na inserção");
			return false;
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("RevistaDAO: Conexão Fechada");
				return true;
			}catch(SQLException ex){
				ex.printStackTrace();
				logger.error("RevistaDAO: erro no fechamento da Conexão");
				return false;
			}
		}

		
	}

	public boolean removeItemAcervo(Revista revista) {
		// TODO Auto-generated method stub
		String	sql	=	"DELETE FROM revista"	+
				"WHERE id =?";
		PreparedStatement stmt = null;
	    try {
	    	stmt =	con.prepareStatement(sql);
	    	stmt.setInt(1, revista.getId());
	    	stmt.executeUpdate();
	    }catch(SQLException ex) {
	    	ex.printStackTrace();
	    }finally {
			try {
				stmt.close();
				con.close();
				return true;
			}catch(SQLException ex){
				ex.printStackTrace();
				return false;
			}
		}
	}

	@SuppressWarnings("finally")
	public boolean updateItemAcervo(Revista revista) {
		String	sql	=	"UPDATE revista set titulo=?,editora=?,data=?,edica=?,num_pag=?"	+
				"WHERE id =?";
		PreparedStatement stmt = null;
	    try {
	    	stmt =	con.prepareStatement(sql);
			stmt.setString(1,revista.getTitulo());
			stmt.setInt(2,revista.getEditora().getId());
			stmt.setDate(3, (Date) revista.getData());
			stmt.setString(4,revista.getEdicao());
			stmt.setInt(5, revista.getNum_pag());
	    	stmt.executeUpdate();
	    }catch(SQLException ex) {
	    	ex.printStackTrace();
	    }finally {
			try {
				stmt.close();
				con.close();
				return true;
			}catch(SQLException ex){
				ex.printStackTrace();
				return false;
			}
		}

	}

	public ArrayList<Revista> searchItemAcervo(Revista revista) {

		PreparedStatement stmt = null;
		ArrayList <Revista> revistas = new ArrayList <Revista>();
		ResultSet rs = null;
		try{
			
			stmt = con.prepareStatement("select * from revista where id = ?");
			stmt .setInt(1,revista.getId());
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Revista rev = new Revista();
				rev.setId(rs.getInt("id"));
				rev.setTitulo(rs.getString("titulo"));
				rev.setData((Date)rs.getDate("data"));
				rev.setEdicao(rs.getString("edicao"));	
				
				revistas.add(revista);
			}

		return revistas;
		
		}catch (SQLException e) {
			throw new RuntimeException (e);
		}finally {
			try {
				rs.close();
				stmt.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}
}
