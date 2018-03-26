package br.uepb.dao.acervo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.dao.Conexao;
import br.uepb.dao.Item_Acervo;
import br.uepb.model.acervo.Anais;

public class AnaisDAO implements Item_Acervo<Anais>{
	
	private Connection con;
	private static final Logger logger = LogManager.getLogger(JornalDAO.class);
	
	public AnaisDAO() throws Exception{
		con = Conexao.iniciarConexao();
	}

	@SuppressWarnings("finally")
	public boolean createItemAcervo(Anais anal) {
		
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO anal (tipo, titulo, congresso, ano_pub, cidade_id) VALUES (?,?,?,?,?)");
			stmt.setString(1, anal.getTipo().toString());
			stmt.setString(2, anal.getTitulo());
			stmt.setString(3,anal.getNome_congresso());
			stmt.setDate(4, (Date) anal.getAnoPublicacao());
			stmt.setInt(5, anal.getLocal().getId());
			
		} catch (SQLException e) {
			logger.error("Erro na inserção",e);
			return false;
		} finally {
			try {
				con.close();
				stmt.close();
				logger.info("Conexão fechada na inserção");
				return true;
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conexão na inserção",e);
				return false;
			}
		}
	}

	@SuppressWarnings("finally")
	public boolean removeItemAcervo(Anais anal) {
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM anal WHERE id=?");
			stmt.setInt(1, anal.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("Erro na remoção");
			return false;
		} finally {
			try {
				con.close();
				stmt.close();
				logger.info("Conexão fechada na remoção");
				return true;
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conexão da remoção",e);
				return false;
			}
		}
	}

	@SuppressWarnings("finally")
	public boolean updateItemAcervo(Anais anal) {
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE anal SET tipo=?, titulo = ?, congresso=?,ano_pub =? WHERE id = ?");
			stmt.setString(1, anal.getTipo().toString());
			stmt.setString(2, anal.getTitulo());
			stmt.setString(3,anal.getNome_congresso());
			stmt.setDate(4,(Date) anal.getAnoPublicacao());
			stmt.setInt(5, anal.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			logger.error("Erro ao realizar a atualização",e);
			return false;
		} finally {
			try {
				con.close();
				stmt.close();
				logger.info("Conexão fechada na atualização");
				return true;
			} catch (SQLException e) {
				logger.info("Erro ao fechar a conexão na atualização",e);
				return false;
			}
		}
		
	}
	//TODO implementar o stmt e implementar o resto do método search
	public ArrayList<Anais> searchItemAcervo(Anais anal) {
		
		ArrayList<Anais> listaAnais = new ArrayList<Anais>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT A.id AS id_anal, A.tipo AS tipo_anal, A.titulo AS titulo_anal, A.congresso AS congresso_anal, A.ano_pub AS ano_pub_anal, "
					+ "C.Id AS id_cidade, C.Codigo AS codigo_cidade, C.Nome AS nome_cidade, C.Uf AS uf_cidade, "
					+ "AU.id as id_autor, AU.nome as nome_autor "
					+ "FROM anal as A "
					+ "INNER JOIN cidade AS C ON A.cidade_id = C.Id "
					+ "INNER JOIN autor AS AU ON A.id = anal_has_autor.anal_id AND AU.id = anal_has_autor.autor_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		return null;
	}

	
}
