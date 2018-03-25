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
import br.uepb.dao.Item_Acervo;
import br.uepb.model.acervo.Livro;

public class LivroDAO implements Item_Acervo<Livro>{
	private Connection con;
	private static final Logger logger = LogManager.getLogger(LivroDAO.class); 
	
	public LivroDAO() throws Exception {
		con = Conexao.iniciarConexao();
	}
	public boolean createItemAcervo(Livro livro) {
		String sql = "insert into livro(isbn,titulo,editora_id,ano,edicao,num_pag,area_conhecimento_id) values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, livro.getIsbn());
			stmt.setString(2,livro.getTitulo());
			stmt.setInt(3,livro.getEditora().getId());
			stmt.setDate(4,(Date) livro.getAno_publicacao());
			stmt.setInt(5,livro.getEdicao());
			stmt.setInt(6,livro.getNumero_paginas());
			stmt.setInt(7, livro.getArea().getId());
			return stmt.execute();
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

	public boolean removeItemAcervo(Livro livro) {
		String sql = "delete livro where isbn=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,livro.getIsbn());
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

	public boolean updateItemAcervo(Livro livro) {
		String sql = "update livro set titulo=?,editora_id=?,ano=?,edicao=?,num_pag=?,area_conhecimento_id=? where isbn=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,livro.getTitulo());
			stmt.setInt(2,livro.getEditora().getId());
			stmt.setDate(3,(Date) livro.getAno_publicacao());
			stmt.setInt(4,livro.getEdicao());
			stmt.setInt(5,livro.getNumero_paginas());
			stmt.setInt(6, livro.getArea().getId());
			stmt.setLong(7, livro.getIsbn());
			return stmt.execute();
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

	public ArrayList<Livro> searchItemAcervo(Livro livro) {
		String sql = "select * from livro where titulo=%?%";
		ArrayList<Livro> livros = new ArrayList<Livro>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,livro.getTitulo());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
	            Livro l = new Livro();
	            l.setIsbn(rs.getLong("isbn"));
	            l.setTitulo(rs.getString("titulo"));
	            
	            //Busca a lista de autores daquele livro
	            AutorDAO autordao = new AutorDAO();
	            l.setAutores(autordao.buscarAutoresPorISBN(l.getIsbn()));
	            
	            //Buscar a editora do livro
	            EditoraDAO editoradao = new EditoraDAO();
	            l.setEditora(editadora.buscar(rs.getInt("editora_id")));
	            
	            l.setAno_publicacao(rs.getDate("ano"));
	            l.setEdicao(rs.getInt("edicao"));
	            l.setNumero_paginas(rs.getInt("num_pag"));
	            
	            //Buscar a area do conhecimento do area_conhecimento_id
	            AreaConhecimentoDAO areadao = new AreaConhecimentoDAO();
	            l.setArea(areadao.buscar(rs.getInt("area_conhecimento_id")));
	            livros.add(l);
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
		return livros;
	}

		

}
