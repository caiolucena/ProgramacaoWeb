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
	
	public JornalDAO() throws Exception{
		con = Conexao.iniciarConexao();
	}

	public boolean createItemAcervo(Jornal jornal) {		
		String	sql	=	"insert	into	jornal"	+
				"(id,titulo,data,edicao)"	+
				"values	(?,?,?,?)";
		PreparedStatement stmt = null;
		try	{
			//	prepared	statement	para	inserção
			stmt =	con.prepareStatement(sql);
			//	seta	os	valores
			stmt.setInt(1,jornal.getId());
			stmt.setString(2,jornal.getTitulo());
			stmt.setDate(3, (Date) jornal.getData());
			stmt.setString(4,jornal.getEdicao());
			//	executa
			stmt.execute();
		}catch	(SQLException	e)	{
			e.printStackTrace();
			logger.error("JornalDAO: erro na inserção");
			return false;
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("JornalDAO: Conexão Fechada");
				return true;
			}catch(SQLException ex){
				ex.printStackTrace();
				return false;
			}
		}

		
	}

	public boolean removeItemAcervo(Jornal jornal) {
		// TODO Auto-generated method stub
		String	sql	=	"DELETE FROM jornal"	+
				"WHERE id =?";
		PreparedStatement stmt = null;
	    try {
	    	stmt =	con.prepareStatement(sql);
	    	stmt.setInt(1, jornal.getId());
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
	public boolean updateItemAcervo(Jornal jornal) {
		String	sql	=	"UPDATE jornal set titulo =?,data=?,edicao=?"	+
				"WHERE id =?";
		PreparedStatement stmt = null;
	    try {
	    	stmt =	con.prepareStatement(sql);
	    	stmt.setString(1, jornal.getTitulo());
	    	stmt.setDate(2, (Date) jornal.getData());
	    	stmt.setString(3, jornal.getEdicao());
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

	public ArrayList<Jornal> searchItemAcervo(Jornal jornal) {

		PreparedStatement stmt = null;
		ArrayList <Jornal> jornais = new ArrayList <Jornal>();
		ResultSet rs = null;
		try{
			
			stmt = con.prepareStatement("select * from jornal where id = ?");
			stmt .setInt(1,jornal.getId());
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Jornal jor = new Jornal();
				jor.setId(rs.getInt("id"));
				jor.setTitulo(rs.getString("titulo"));
				jor.setData((Date)rs.getDate("data"));
				jor.setEdicao(rs.getString("edicao"));
				
				jornais.add(jornal);
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
