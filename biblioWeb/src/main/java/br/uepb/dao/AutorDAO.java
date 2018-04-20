package br.uepb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.interfaces.DAO_Dependencia;
import br.uepb.model.Autor;
/**
 * Essa classe é responsável por se conectar com o Banco de Dados para operações de inserir, atualizar, remover e buscar objetos do tipo Autor
 * @author EquipeACL
 */
public class AutorDAO implements DAO_Dependencia<Autor>{
	private Connection con;
	private static final Logger logger = LogManager.getLogger(AutorDAO.class);
	
	/**
	 * Método para inserir Autor no banco de dados
	 * @param autor, objeto do tipo Autor
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a inserção for bem sucedida
	 * @return false, se ocorrer algum error na inserção.
	 */	
	@SuppressWarnings("finally")
	public boolean createItemDependencia(Autor autor){
		String sql = "insert into autor(nome)values(?)";
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, autor.getNome());
			stmt.execute();
			return true;
		} catch(SQLException e) {
			if(e.getClass().equals(new com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException().getClass())){
				logger.error("Erro na inserção - Parametros null",e);
				return false;
			}
			logger.error("Erro na inserção ",e);
			return false;
		} catch (Exception e) {
			if(e.getClass().equals(new java.lang.NullPointerException().getClass())){
				logger.error("Erro na inserção - Parametros null",e);
				return false;
			}
			logger.error("Erro na inserção ",e);
			return false;
		}
		finally {
			try {
				con.close();
				logger.info("Conexão fechada na inserção");
				//return true;//Complicado esse return aqui, porque se inserir mas na hora de fechar a conexao der falha vai retorna false e a interpretação de que não foi inserido
				//E quando acontece uma exceção ba inserção o método return true pois consegue fechar a conexão, mas deveria retorna false pois não inseriu.
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na inserção ",e);
				//return false;
			}
		}
		
	}
	
	/**
	 * Método para remover Autor do banco de dados
	 * @param autor, objeto do tipo Autor
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a remoção for bem sucedida
	 * @return false, se ocorrer algum error na operação.
	 */	
	@SuppressWarnings("finally")
	public boolean removeItemDependencia(Autor autor) {
		String sql = "delete from autor where id=?";
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,autor.getId());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na remoção ",e);
		} catch (Exception e) {
			logger.error("Erro na remoção ",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão fechada na remoção");			
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na remoção ",e);
			}
		}
		return false;
	}
	
	/**
	 * Método para atualizar Autor no banco de dados
	 * @param autor, objeto do tipo Autor
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return true, se a atualização for bem sucedida
	 * @return false, se ocorrer algum error na operação.
	 */	
	@SuppressWarnings("finally")
	public boolean updateItemDependencia(Autor autor) {
		String sql = "update autor set nome=? where id=?";
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,autor.getNome());
			stmt.setInt(2, autor.getId());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na atualização ",e);
		} catch (Exception e) {
			logger.error("Erro na atualização ",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão fechada na atualização");
				//return true;//Mesmo problema do insert

			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na atualização ",e);
				//return false;
			}
		}
		return false;
	}
	
	/**
	 * Método para pesquisar Autor no banco de dados
	 * @param autor, obejto do tipo Autor
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return ArrayList<Autor> autores, lista de autores retornados pela busca. 
	 */	
	public ArrayList<Autor> searchItemDependencia(String nome) {//busca varios autores pelo nome
		String sql = "select * from autor where nome like ?";
		ArrayList<Autor> autores = new ArrayList<Autor>();
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,"%"+nome+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
	            Autor a = new Autor();
	            a.setId(rs.getInt("id"));
	            a.setNome(rs.getString("nome"));
	            autores.add(a);
	        }
		} catch (SQLException e) {
			logger.error("Erro na busca ",e);
		} catch (Exception e) {
			logger.error("Erro na busca ",e);
		}finally {
			try {
				con.close();
				logger.info("Conexao fechada na busca");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada ",e);
			}
		}
		return autores;
	}
	
	/**
	 * Método para pesquisar Autor no banco de dados
	 * @param id_autor, atributo de identificação unico de cada instancia
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return autor, objeto do tipo Autor retornado pela busca. 
	 */		
	public Autor searchItemDependencia(int id_autor) {//busca um unico autor pelo id
		String sql = "select * from autor where id=?";
		Autor autor = new Autor();
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,id_autor);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){	            
				autor.setId(rs.getInt("id"));
				autor.setNome(rs.getString("nome"));
	        }
		} catch (SQLException e) {
			logger.error("Erro na busca",e);
		} catch (Exception e) {
			logger.error("Erro na busca",e);
		}finally {
			try {
				con.close();
				logger.info("Conexao fechada na busca");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na busca ",e);
			}
		}
		return autor;
	}
	
	/**
	 * Método para pesquisar Autor pelo ISBN no banco de dados
	 * @param isbn, numero de identifição unico de cada livro
	 * @throws SQLException
	 * @throws JavaLangException
	 * @return ArrayList<Autor> autores, lista de autores retornados pela busca
	 */
	public ArrayList<Autor> buscarAutoresPorISBN(long isbn) {
		
		String sql = "select * from autor_has_livro inner join autor on autor_id=autor.id where livro_isbn=?";
		ArrayList<Autor> autores = new ArrayList<Autor>();
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,isbn);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
	            Autor a = new Autor();
	            a.setId(rs.getInt("autor_id"));
	            a.setNome(rs.getString("nome"));
	            autores.add(a);
	        }
		} catch (SQLException e) {
			logger.error("Erro na busca ",e);
		} catch (Exception e) {
			logger.error("Erro na busca",e);
		}finally {
			try {
				con.close();
				logger.info("Conexao fechada na busca");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na busca ",e);
			}
		}
		return autores;
	}
}
