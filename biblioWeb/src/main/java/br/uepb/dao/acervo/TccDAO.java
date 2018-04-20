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
import br.uepb.interfaces.DAO_Acervo;
import br.uepb.model.Autor;
import br.uepb.model.Cidade;
import br.uepb.model.Orientador;
import br.uepb.model.acervo.Tcc;
import br.uepb.model.enums.Tipo_tcc;
/**
 * Essa classe é responsável por se conectar com o Banco de Dados para operações de inserir, atualizar, remover e buscar objetos do tipo Tcc
 * Ela implementa a interface Item_Acervo passando o tipo Tcc.
 * @author EquipeACL
 */
public class TccDAO implements DAO_Acervo<Tcc>{
	
	private Connection con;
	private static final Logger logger = LogManager.getLogger(TccDAO.class);

	/**
	 * Método para inserir um Tcc no banco de dados
	 * @param tcc, objeto do tipo Tcc
	 * @throws SQLExcption
	 * @throws JavaLangException
	  * @return true, se a operação de inserção for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	@SuppressWarnings("finally")
	public boolean createItemAcervo(Tcc tcc) {

		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao(); 
			stmt = con.prepareStatement("INSERT INTO tcc(id,titulo,autor_id,orientador_id,tipo,defesa,cidade_id) VALUES(?,?,?,?,?,?,?)");
			stmt.setInt(1,tcc.getId());
			stmt.setString(2, tcc.getTitulo());
			stmt.setInt(3, tcc.getAutor().getId());
			stmt.setInt(4,tcc.getOrientador().getId());
			stmt.setString(5, tcc.getTipo().toString());
			stmt.setDate(6, new java.sql.Date(tcc.getAno_defesa().getTime()));
			stmt.setInt(7, tcc.getCidade().getId());
			
			stmt.executeUpdate();			
			return true;
		} catch	(SQLException	e)	{
			logger.error("Erro na inserção",e);
		} catch (Exception e) {
			logger.error("Erro na inserção",e);
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conexão Fechada");
			}catch(SQLException e){
				logger.error("Erro no fechamento da Conexão",e);
			}
		}
		return false;
	}

	/**
	 * Método para remover um Tcc no banco de dados, utilizando o seu id
	 * @param tcc, objeto do tipo Tcc
	 * @throws SQLExcption
	 * @throws JavaLangException
	 * @return true, se a operação de remoção for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	@SuppressWarnings("finally")
	public boolean removeItemAcervo(Tcc tcc) {

		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao(); 
			stmt = con.prepareStatement("DELETE FROM tcc WHERE id=?");
			stmt.setInt(1, tcc.getId());
			
			stmt.executeUpdate();
			return true;
		}  catch (SQLException	e)	{
			logger.error("Erro na remoção",e);
		} catch (Exception e) {
			logger.error("Erro na remoção",e);
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conexão Fechada");
			}catch(SQLException e){
				logger.error("Erro no fechamento da Conexão",e);
			}
		}
		return false;
	}

	/**
	 * Método para atualizar um Tcc no banco de dados, utilizando o seu id
	 * @param tcc, objeto do tipo Tcc
	 * @throws SQLExcption
	 * @throws JavaLangException
	 * @return true, se a operação de atualização for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	@SuppressWarnings("finally")
	public boolean updateItemAcervo(Tcc tcc) {

		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao(); 
			stmt = con.prepareStatement("UPDATE tcc SET titulo=?, tipo=?, defesa = ?  WHERE id=?");
			stmt.setString(1, tcc.getTitulo());
			stmt.setString(2, tcc.getTipo().toString());
			stmt.setDate(3, new java.sql.Date(tcc.getAno_defesa().getTime()));
			stmt.setInt(4, tcc.getId());
			
			stmt.executeUpdate();
			return true;
		}  catch (SQLException	e)	{
			logger.error("Erro na atualização",e);
		} catch (Exception e) {
			logger.error("Erro na atualização",e);
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conexão Fechada");
			}catch(SQLException e){
				logger.error("Erro no fechamento da Conexão",e);
			}
		}
		return false;
	}
	/**
	 * Método para pesquisar Tcc no banco de dados, utilizando seu titulo
	 * @param tcc, objeto do tipo Tcc
	 * @throws SQLExcption
	 * @throws JavaLangException
	 * @return ArrayList<Tcc> listaTcc, lista de tccs retornados pela busca
	 */
	public ArrayList<Tcc> searchItemAcervo(String titulo) {
		
		ArrayList<Tcc> listaTcc = new ArrayList<Tcc>();		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			con = Conexao.iniciarConexao(); 
			stmt = con.prepareStatement("SELECT T.id AS id_tcc, T.titulo as titulo_tcc, T.tipo as tipo_tcc, T.defesa as defesa_tcc, "
					+ "A.id as id_autor, A.nome AS nome_autor, "
					+ "O.id AS id_orientador, O.nome AS nome_orientador, O.formacao AS formacao_orientador, "
					+ "C.Id as id_cidade, C.Codigo AS codigo_cidade, C.Nome AS nome_cidade, C.Uf AS uf_cidade "
					+ "FROM tcc AS T "
					+ "INNER JOIN autor AS A ON T.autor_id = A.id "
					+ "INNER JOIN cidade AS C ON T.cidade_id = C.id "
					+ "INNER JOIN orientador AS O ON T.orientador_id = O.id  "
					+ "WHERE T.titulo LIKE ?");
			stmt.setString(1,"%"+titulo+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Autor autor = new Autor(rs.getInt("id_autor"),rs.getString("nome_autor"));			
				Orientador orientador = new Orientador(rs.getInt("id_orientador"),rs.getString("nome_orientador"),rs.getString("formacao_orientador"));
				Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getInt("codigo_cidade"), rs.getString("nome_cidade"), rs.getString("uf_cidade"));
				Tcc TCC = new Tcc(rs.getInt("id_tcc"), rs.getString("titulo_tcc"), autor, orientador, Tipo_tcc.valueOf(Tipo_tcc.class,rs.getString("tipo_tcc")), rs.getDate("defesa_tcc"), cidade);
				listaTcc.add(TCC);	
			}
			
			
		} catch (SQLException	e)	{
			logger.error("Erro na busca",e);
		} catch (Exception e) {
			logger.error("Erro na busca",e);
		}finally {
			try {
				stmt.close();
				con.close();
				logger.info("Conexão Fechada");
			}catch(SQLException e){
				logger.error("Erro no fechamento da Conexão",e);
			}
		}
		
		return listaTcc;
	}
	
}
