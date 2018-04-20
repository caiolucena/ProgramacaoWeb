package br.uepb.dao.acervo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.dao.AreaConhecimentoDAO;
import br.uepb.dao.AutorDAO;
import br.uepb.dao.Conexao;
import br.uepb.interfaces.DAO_Acervo;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Autor;
import br.uepb.model.Editora;
import br.uepb.model.acervo.Livro;
/**
 * Essa classe é responsável por se conectar com o Banco de Dados para operações de inserir, atualizar, remover e buscar objetos do tipo Livro
 * Ela implementa a interface Item_Acervo passando o tipo Livro.
 * @author EquipeACL
 */
public class LivroDAO implements DAO_Acervo<Livro>{
	private Connection con;
	private static final Logger logger = LogManager.getLogger(LivroDAO.class); 

	/**
	 * Método para inserir um Livro no banco de dados
	 * @param livro, objeto do tipo Livro
	 * @throws SQLException
	 * @return true, se a operação de inserção for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	public boolean createItemAcervo(Livro livro) {
		String sql = "insert into livro(isbn,titulo,editora_id,ano,edicao,num_pag,area_conhecimento_id) values (?,?,?,?,?,?,?)";
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, livro.getIsbn());
			stmt.setString(2,livro.getTitulo());
			stmt.setInt(3,livro.getEditora().getId());
			stmt.setDate(4,new java.sql.Date(livro.getData().getTime()));
			stmt.setInt(5,livro.getEdicao());
			stmt.setInt(6,livro.getNumero_paginas());
			stmt.setInt(7, livro.getArea().getId());
			stmt.execute();
			
			//inserir em autor_has_livro os ids do array de autores do livro
			for(Autor autor : livro.getAutores()){
				sql = "insert into autor_has_livro(autor_id,livro_isbn)values(?,?);";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1,autor.getId());
				stmt.setLong(2,livro.getIsbn());
				stmt.execute();
			}
		
			return true;
		} catch (SQLException e) {
			if(e.getClass().equals(new com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException().getClass())){
				logger.error("Erro na inserção - Parametros null",e);
			}else{
				logger.error("Erro na inserção",e);
			}
		} catch (Exception e) {
			if(e.getClass().equals(new java.lang.NullPointerException().getClass())){
				logger.error("Erro na inserção - Parametros null",e);
			}else{
				logger.error("Erro na inserção",e);
			}
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada",e);
			}
		}
		return false;
	}

	/**
	 * Método para remover um Livro no banco de dados
	 * @param livro, objeto do tipo Livro
	 * @throws SQLException
	 * @return true, se a operação de remoção for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	public boolean removeItemAcervo(Livro livro) {
		try {
			con = Conexao.iniciarConexao();
			for(Autor a: livro.getAutores()) {
				PreparedStatement stmt2 = null;
				stmt2 = con.prepareStatement("delete from autor_has_livro where autor_id = ? and livro_isbn = ?");
				stmt2.setInt(1, a.getId());
				stmt2.setLong(2, livro.getIsbn());
				stmt2.executeUpdate();
			}
		
			String sql = "delete from livro where isbn=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,livro.getIsbn());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na remoção",e);
		} catch (Exception e) {
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
	
	/**
	 * Método para atualizar um Livro no banco de dados
	 * @param livro, objeto do tipo Livro
	 * @throws SQLException
	 * @return true, se a operação de atualização for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	public boolean updateItemAcervo(Livro livro) {
		String sql = "update livro set titulo=?,editora_id=?,ano=?,edicao=?,num_pag=?,area_conhecimento_id=? where isbn=?";
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,livro.getTitulo());
			stmt.setInt(2,livro.getEditora().getId());
			stmt.setDate(3,new java.sql.Date(livro.getData().getTime()));
			stmt.setInt(4,livro.getEdicao());
			stmt.setInt(5,livro.getNumero_paginas());
			stmt.setInt(6, livro.getArea().getId());
			stmt.setLong(7, livro.getIsbn());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			if(e.getClass().equals(new com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException().getClass())){
				logger.error("Erro na atualização - Parametros null",e);
			}else{
				logger.error("Erro na atualização",e);
			}
		} catch (Exception e) {
			if(e.getClass().equals(new java.lang.NullPointerException().getClass())){
				logger.error("Erro na atualização - Parametros null",e);
			}else{
				logger.error("Erro na atualização",e);
			}
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada",e);
			}
		}
		return false;
	}

	/**
	 * Método para buscar um Livro no banco de dados
	 * @param livro, objeto do tipo Livro
	 * @throws SQLException
	 * @return ArrayList<Livro> livros, lista de livros retornados pela busca
	 */
	public ArrayList<Livro> searchItemAcervo(String titulo) {
		String sql = "select L.isbn as 'isbn', L.titulo as 'titulo', L.ano as 'ano', L.edicao as 'edicao', "
				+ "L.num_pag as 'NumeroDePaginas', E.id as 'editora_id', E.nome as 'editora_nome', A.id as 'area_id', "
				+ "A.nome as 'area_nome'  from livro as L inner join editora as E on L.editora_id = E.id "
				+ "inner join area_conhecimento as A on A.id=L.area_conhecimento_id where L.titulo like ?";
		ArrayList<Livro> livros = new ArrayList<Livro>();
		try {
			con = Conexao.iniciarConexao();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,"%"+titulo+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
	            Livro l = new Livro();
	            l.setIsbn(rs.getLong("isbn"));
	            l.setTitulo(rs.getString("titulo"));
	            
	            //Busca a lista de autores daquele livro
	            AutorDAO autordao = null;
				try {
					autordao = new AutorDAO();
				} catch (Exception e) {
					logger.error("Expeciton em AutorDAO",e);
				}
	            l.setAutores(autordao.buscarAutoresPorISBN(l.getIsbn()));
	            
	            l.setEditora(new Editora(rs.getInt("editora_id"),rs.getString("editora_nome")));
	            
	            l.setData(rs.getDate("ano"));
	            l.setEdicao(rs.getInt("edicao"));
	            l.setNumero_paginas(rs.getInt("NumeroDePaginas"));	            
	            l.setArea(new AreaConhecimento(rs.getInt("area_id"),rs.getString("area_nome")));
	            livros.add(l);
	        }
		} catch (SQLException e) {
			logger.error("Erro na busca",e);
		} catch (Exception e1) {
			logger.error("Erro na busca",e1);
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada",e);
			}
		}
		return livros;
	}

		

}
