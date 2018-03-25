package br.uepb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.model.Autor;
import br.uepb.model.Curso;
import br.uepb.model.enums.Tipo_Curso;

public class CursoDAO {
	private Connection con;
	private static final Logger logger = LogManager.getLogger(AutorDAO.class);
	
	public CursoDAO() throws Exception{
		con = Conexao.iniciarConexao();
	}
	
	public boolean createCurso(Curso curso){
		String sql = "insert into curso(nome,tipo,area_conhecimento_id)values(?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, curso.getNome());
			stmt.setString(2,curso.getTipo().toString());
			stmt.setInt(3, curso.getArea().getId());
			//TODO INSERIR faltar adicionar o id da subarea tema
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
	
	public boolean removeCurso(Curso curso){
		String sql = "delete curso where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,curso.getId());
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
	
	public boolean updateCurso(Curso curso){
		String sql = "update curso set nome=?, tipo=?, area_conhecimento_id=?, area_conhecimento_tema_id=? where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, curso.getNome());
			stmt.setString(2,curso.getTipo().toString());
			stmt.setInt(3, curso.getArea().getId());
			//TODO ATUALIZAR faltar adicionar o id da subarea tema
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
	
	public ArrayList<Curso> searchCurso(Curso curso){
		String sql = "select * from curso where nome=%?%";
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,curso.getNome());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
	            Curso c = new Curso();
	            c.setId(rs.getInt("id"));
	            c.setNome(rs.getString("nome"));
	            if(rs.getString("tipo").equals(Tipo_Curso.graduacao)){
	            	c.setTipo(Tipo_Curso.graduacao);
	            }else if(rs.getString("tipo").equals(Tipo_Curso.pos_graduacao)){
	            	c.setTipo(Tipo_Curso.pos_graduacao);
	            }
	            AreaConhecimentoDAO adao = new AreaConhecimentoDAO();
	            c.setArea(adao.buscarArea(rs.getInt("area_conhecimento_id")));
	            //TODO Buscar e adiconar subarea ao curso
	            
	            cursos.add(c);
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
		return cursos;
	}
}
