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
				"(id,titulo,data,edicao)"	+
				"values	(?,?,?,?)";
		PreparedStatement stmt = null;
		try	{
			//	prepared	statement	para	inserção
			stmt =	con.prepareStatement(sql);
			//	seta	os	valores
			stmt.setInt(1,revista.getId());
			stmt.setString(2,revista.getTitulo());
			stmt.setDate(3, (Date) revista.getData());
			stmt.setString(4,revista.getEdicao());
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
		String	sql	=	"UPDATE revista set titulo =?,data=?,edicao=?"	+
				"WHERE id =?";
		PreparedStatement stmt = null;
	    try {
	    	stmt =	con.prepareStatement(sql);
	    	stmt.setString(1, revista.getTitulo());
	    	stmt.setDate(2, (Date) revista.getData());
	    	stmt.setString(3, revista.getEdicao());
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
		ArrayList <Revista> jornais = new ArrayList <Revista>();
		ResultSet rs = null;
		try{
			
			stmt = con.prepareStatement("select * from revista where id = ?");
			stmt .setInt(1,revista.getId());
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Revista jor = new Revista();
				jor.setId(rs.getInt("id"));
				jor.setTitulo(rs.getString("titulo"));
				jor.setData((Date)rs.getDate("data"));
				jor.setEdicao(rs.getString("edicao"));
				
				jornais.add(revista);
			}

		return jornais;
		
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
