package br.uepb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import br.uepb.model.Autor;

public class AutorDAO {
	private Connection con;
	private static final Logger logger = LogManager.getLogger(AutorDAO.class);
	
	public AutorDAO() throws Exception{
		con = Conexao.iniciarConexao();
	}
	
	public boolean createAutor(Autor autor){
		String sql = "insert into autor(nome)values(?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, autor.getNome());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na inserção",e);
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada",e);
			}
		}
		return false;
	}
	
	public boolean removeAutor(Autor autor) {
		String sql = "delete from autor where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,autor.getId());
			return stmt.execute();
		} catch (SQLException e) {
			logger.error("Erro na remoção",e);
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada",e);
			}
		}
		return false;
	}
	
	public boolean updateAutor(Autor autor) {
		String sql = "update autor set nome=? where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,autor.getNome());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na atualização",e);
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada",e);
			}
		}
		return false;
	}
	
	public ArrayList<Autor> searchAutor(Autor autor) {
		String sql = "select * from autor where nome like ?";
		ArrayList<Autor> autores = new ArrayList<Autor>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,"%"+autor.getNome()+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
	            Autor a = new Autor();
	            a.setId(rs.getInt("id"));
	            a.setNome(rs.getString("nome"));
	            autores.add(a);
	        }
		} catch (SQLException e) {
			logger.error("Erro na busca",e);
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada",e);
			}
		}
		return autores;
	}
	
	public Autor searchAutor(int id_autor) {
		String sql = "select * from autor where id=?";
		Autor autor = new Autor();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,id_autor);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){	            
				autor.setId(rs.getInt("id"));
				autor.setNome(rs.getString("nome"));
	        }
		} catch (SQLException e) {
			logger.error("Erro na busca",e);
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada",e);
			}
		}
		return autor;
	}
	
	//TODO Acho que essa responsabilidade não é de autor
	public ArrayList<Autor> buscarAutoresPorISBN(long isbn) {
		
		String sql = "select * from autor_has_livro inner join autor on autor_id=autor.id where livro_isbn=?";
		ArrayList<Autor> autores = new ArrayList<Autor>();
		try {
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
			logger.error("Erro na busca",e);
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada",e);
			}
		}
		return autores;
	}
}
