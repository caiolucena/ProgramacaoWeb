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
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Editora;
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
		String sql = "select L.isbn as 'isbn', L.titulo as 'titulo', L.ano as 'ano', L.edicao as 'edicao', "
				+ "L.num_pag as 'NumeroDePaginas', E.id as 'editora_id', E.nome as 'editora_nome', A.id as 'area_id', "
				+ "A.nome as 'area_nome'  from livro as L inner join editora as E on L.editora_id = E.id "
				+ "inner join area_conhecimento as A on A.id=L.area_conhecimento_id where L.titulo like '%Livro1%'";
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
	            AutorDAO autordao = null;
				try {
					autordao = new AutorDAO();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            l.setAutores(autordao.buscarAutoresPorISBN(l.getIsbn()));
	            
	            l.setEditora(new Editora(rs.getInt("editora_id"),rs.getString("editora_nome")));
	            
	            l.setAno_publicacao(rs.getDate("ano"));
	            l.setEdicao(rs.getInt("edicao"));
	            l.setNumero_paginas(rs.getInt("num_pag"));	            
	            l.setArea(new AreaConhecimento(rs.getInt("area_id"),rs.getString("area_nome")));
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