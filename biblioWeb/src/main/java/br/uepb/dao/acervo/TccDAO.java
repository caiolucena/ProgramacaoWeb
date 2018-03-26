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
import br.uepb.model.Orientador;
import br.uepb.model.acervo.Tcc;
import br.uepb.model.enums.Tipo_Tcc;

public class TccDAO implements Item_Acervo<Tcc>{
	
	private Connection con;
	private static final Logger logger = LogManager.getLogger(RevistaDAO.class);
	
	public TccDAO() throws Exception {
		con = Conexao.iniciarConexao(); 
	}

	@SuppressWarnings("finally")
	public boolean createItemAcervo(Tcc tcc) {

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO tcc(id,titulo,autor_id,orientador_id,tipo,defesa,cidade_id) VALUES(?,?,?,?,?,?,?)");
			stmt.setInt(1,tcc.getId());
			stmt.setString(2, tcc.getTitulo());
			stmt.setInt(3, tcc.getAutor().getId());
			stmt.setInt(4,tcc.getOrientador().getId());
			stmt.setString(5, tcc.getTipo().toString());
			stmt.setDate(6, (Date) tcc.getAno_defesa());
			stmt.setInt(7, tcc.getCidade().getId());
			
			stmt.executeUpdate();			
			
		} catch	(SQLException	e)	{
			e.printStackTrace();
			logger.error("TccDAO: erro na inserção");
			return false;
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("TccDAO: Conexão Fechada");
				return true;
			}catch(SQLException ex){
				ex.printStackTrace();
				logger.error("TccDAO: erro no fechamento da Conexão");
				return false;
			}
		}
		
	}

	@SuppressWarnings("finally")
	public boolean removeItemAcervo(Tcc tcc) {

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("DELETE FROM tcc WHERE id=?");
			stmt.setInt(1, tcc.getId());
			
			stmt.executeUpdate();
			
		}  catch (SQLException	e)	{
			e.printStackTrace();
			logger.error("TccDAO: erro na remoção");
			return false;
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("TccDAO: Conexão Fechada");
				return true;
			}catch(SQLException ex){
				ex.printStackTrace();
				logger.error("TccDAO: erro no fechamento da Conexão");
				return false;
			}
		}
		
	}

	@SuppressWarnings("finally")
	public boolean updateItemAcervo(Tcc tcc) {

		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("UPDATE tcc SET titulo=?, tipo=?, defesa = ?  WHERE id=?");
			stmt.setString(1, tcc.getTitulo());
			stmt.setString(2, tcc.getTipo().toString());
			stmt.setDate(3,(Date) tcc.getAno_defesa());
			stmt.setInt(4, tcc.getId());
			
			stmt.executeUpdate();
			
		}  catch (SQLException	e)	{
			e.printStackTrace();
			logger.error("TccDAO: erro na atualização");
			return false;
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("TccDAO: Conexão Fechada");
				return true;
			}catch(SQLException ex){
				ex.printStackTrace();
				logger.error("TccDAO: erro no fechamento da Conexão");
				return false;
			}
		}
	}

	public ArrayList<Tcc> searchItemAcervo(Tcc tcc) {
		
		ArrayList<Tcc> listaTcc = new ArrayList<Tcc>();		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.prepareStatement("SELECT T.id AS id_tcc, T.titulo as titulo_tcc, T.tipo as tipo_tcc, T.defesa as defesa_tcc"
					+ " A.id as id_autor, A.nome AS nome_autor, "
					+ "O.id AS id_orientador, O.nome AS nome_orientador, O.formacao AS formacao_orientador, "
					+ "C.Id as id_cidade, C.Codigo AS codigo_cidade, C.Nome AS nome_cidade, C.Uf AS uf_cidade "
					+ "FROM tcc AS T "
					+ "INNER JOIN autor AS A ON T.autor_id = A.id "
					+ "INNER JOIN cidade AS C ON T.cidade_id = C.id "
					+ "INNER JOIN orientador AS O ON T.orientador_id = O.id  "
					+ "WHERE T.titulo LIKE '%?%'\r\n");
			stmt.setString(1,tcc.getTitulo());
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Autor autor = new Autor(rs.getInt("id_autor"),rs.getString("nome_autor"));			
				Orientador orientador = new Orientador(rs.getInt("id_orientador"),rs.getString("nome_orientador"),rs.getString("formacao_orientador"));
				Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getInt("codigo_cidade"), rs.getString("nome_cidade"), rs.getString("uf_cidade"));
				Tcc TCC = new Tcc(rs.getInt("id_tcc"), rs.getString("titulo_tcc"), autor, orientador, Tipo_Tcc.valueOf(Tipo_Tcc.class,rs.getString("tipo_tcc")), rs.getDate("defesa_tcc"), cidade);
				listaTcc.add(TCC);	
			}
			
			
		} catch (SQLException	e)	{
			e.printStackTrace();
			logger.error("TccDAO: erro na busca");
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("TccDAO: Conexão Fechada");
			}catch(SQLException ex){
				ex.printStackTrace();
				logger.error("TccDAO: erro no fechamento da Conexão");
			}
		}
		
		return listaTcc;
	}
	
}
