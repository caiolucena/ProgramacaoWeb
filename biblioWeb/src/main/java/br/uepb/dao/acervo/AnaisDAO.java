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
import br.uepb.model.Autor;
import br.uepb.model.Cidade;
import br.uepb.model.acervo.Anais;
import br.uepb.model.enums.Tipo_anal;

public class AnaisDAO implements Item_Acervo<Anais>{
	
	private Connection con;
	private static final Logger logger = LogManager.getLogger(AnaisDAO.class);
	
	/**
	 * Método para inserir um Anal de Congresso no banco de dados
	 * @param anal
	 * @throws SQLException
	 * @return true or false
	 */
	
	@SuppressWarnings("finally")
	public boolean createItemAcervo(Anais anal) {
		
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		
		try {
			
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("INSERT INTO anal (tipo,titulo,congresso,ano_pub,cidade_id) VALUES(?,?,?,?,?)");
	
			
			stmt.setString(1, anal.getTipo().toString());
			stmt.setString(2, anal.getTitulo());
			stmt.setString(3,anal.getNome_congresso());
			stmt.setDate(4,new java.sql.Date(anal.getAnoPublicacao().getTime()));
			stmt.setInt(5, anal.getLocal().getId());
			
			stmt.executeUpdate();
			stmt = con.prepareStatement("select id from anal where tipo=? and titulo=? and congresso=? and ano_pub=? and cidade_id=?");
			stmt.setString(1, anal.getTipo().toString());
			stmt.setString(2, anal.getTitulo());
			stmt.setString(3,anal.getNome_congresso());
			stmt.setDate(4,new java.sql.Date(anal.getAnoPublicacao().getTime()));
			stmt.setInt(5, anal.getLocal().getId());
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				anal.setId(rs.getInt("id"));
			}
			
			stmt2 = con.prepareStatement("INSERT INTO anal_has_autor(anal_id, autor_id) VALUES(?,?)");
			stmt2.setInt(1, anal.getId());
			stmt2.setInt(2, anal.getAutor().getId());
			
			stmt2.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			logger.error("Erro na inserção",e);
		} catch (Exception e) {
			logger.error("Erro na inserção",e);
		} finally {
			try {
				con.close();
				stmt.close();
				stmt2.close();
				logger.info("Conexão fechada na inserção");
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conexão na inserção",e);
			}
		}
		return false;
	}
	
	/**
	 * Método para remover um Anal de Congresso do banco de dados
	 * @param anal
	 * @throws SQLException
	 * @return true or false
	 */
	
	@SuppressWarnings("finally")
	public boolean removeItemAcervo(Anais anal) {
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("DELETE FROM anal_has_autor WHERE anal_id = ? AND autor_id = ? ");
			stmt.setInt(1, anal.getId());
			stmt.setInt(2,anal.getAutor().getId());
			
			stmt.executeUpdate();
			
			stmt2 = con.prepareStatement("DELETE FROM anal WHERE id=?");
			stmt2.setInt(1, anal.getId());
			
			stmt2.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na remoção",e);
		} catch (Exception e) {
			logger.error("Erro na remoção",e);
		} finally {
			try {
				con.close();
				stmt.close();
				stmt2.close();
				logger.info("Conexão fechada na remoção");
			} catch (SQLException e) {
				logger.error("Erro ao fechar a conexão da remoção",e);
			}
		}
		return false;
	}
	
	/**
	 * Método para atualizar um Anal de Congresso no banco de dados
	 * @param anal
	 * @throws SQLException
	 * @return true or false
	 * 
	 */

	@SuppressWarnings("finally")
	public boolean updateItemAcervo(Anais anal) {
		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("UPDATE anal SET tipo=?, titulo = ?, congresso=?,ano_pub =? WHERE id = ?");
			stmt.setString(1, anal.getTipo().toString());
			stmt.setString(2, anal.getTitulo());
			stmt.setString(3,anal.getNome_congresso());
			stmt.setDate(4,(Date) anal.getAnoPublicacao());
			stmt.setInt(5, anal.getId());
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro ao realizar a atualização",e);
		} catch (Exception e) {
			logger.error("Erro ao realizar a atualização",e);
		} finally {
			try {
				con.close();
				stmt.close();
				logger.info("Conexão fechada na atualização");
			} catch (SQLException e) {
				logger.info("Erro ao fechar a conexão na atualização",e);
			}
		}
		return false;
		
	}
	
	/**
	 * Método para pesquisar Anais de Congresso no banco de dados
	 * @param anal
	 * @throws SQLException
	 * @return ArrayList<Anais> listaAnais
	 */

	public ArrayList<Anais> searchItemAcervo(Anais anal) {
		
		ArrayList<Anais> listaAnais = new ArrayList<Anais>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("select A.id as id_anal, A.tipo as tipo_anal, A.titulo as titulo_anal, A.congresso as congresso_anal, A.ano_pub as ano_anal,"
					+ "C.Id as id_cidade, C.Codigo as codigo_cidade, C.Nome as nome_cidade, C.Uf as uf_cidade, "
					+ "AU.id as id_autor, AU.nome as nome_autor "
					+ "from anal as A "
					+ "inner join anal_has_autor on A.id = anal_has_autor.anal_id "
					+ "inner join autor as AU on AU.id = anal_has_autor.autor_id "
					+ "inner join cidade as C on A.cidade_id = C.Id "
					+ "where titulo like ? ");
			stmt.setString(1,"%"+anal.getTitulo()+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cidade local = new Cidade(rs.getInt("id_cidade"), rs.getInt("codigo_cidade"), rs.getString("nome_cidade"), rs.getString("uf_cidade"));
				Autor autor = new Autor(rs.getInt("id_autor"), rs.getString("nome_autor"));
				Anais a = new Anais(rs.getInt("id_anal"), Enum.valueOf(Tipo_anal.class, rs.getString("tipo_anal")), rs.getString("titulo_anal"), autor, rs.getString("congresso_anal"), rs.getDate("ano_anal"), local);
				listaAnais.add(a);
			}
			
		} catch (SQLException e) {
			logger.error("Erro na busca",e);
		} catch (Exception e) {
			logger.error("Erro na busca",e);
		} finally {
			try {
				con.close();
				stmt.close();
				logger.info("Busca realizada com sucesso ");
			} catch (SQLException e) {
				logger.error("Erro ao fechar conexão",e);
			}
			
		}
		
		return listaAnais;
	}

	
}
