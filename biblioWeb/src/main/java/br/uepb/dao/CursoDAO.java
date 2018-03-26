package br.uepb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.model.AreaConhecimento;
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
			return stmt.execute();
		} catch (SQLException e) {
			logger.error("Erro na inserção",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão fechada na inserção");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na inserção",e);
			}
		}
		return false;
	}
	
	public boolean removeCurso(Curso curso){
		String sql = "delete from curso where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,curso.getId());
			return stmt.execute();
		} catch (SQLException e) {
			logger.error("Erro na remoção",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão fechada na remoção");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na remoção",e);
			}
		}
		return false;
	}
	
	public boolean updateCurso(Curso curso){
		String sql = "update curso set nome=?, tipo=?, area_conhecimento_id=? where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, curso.getNome());
			stmt.setString(2,curso.getTipo().toString());
			stmt.setInt(3, curso.getArea().getId());
			stmt.setInt(4, curso.getId());
			return stmt.execute();
		} catch (SQLException e) {
			logger.error("Erro na atualização ",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão fechada na atualização");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na atualização",e);
			}
		}
		return false;
	}
	
	public ArrayList<Curso> searchCurso(Curso curso){
		String sql = "select c.id as 'curso_id', c.nome as 'curso_nome', c.tipo as 'curso_tipo', a.id as 'area_id', a.nome as 'area_nome' from curso as c inner join area_conhecimento as a on c.area_conhecimento_id = a.id where c.nome like ?";
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,"%"+curso.getNome()+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
	            Curso c = new Curso();
	            c.setId(rs.getInt("curso_id"));
	            c.setNome(rs.getString("curso_nome"));
	            if(rs.getString("curso_tipo").equals(Tipo_Curso.graduacao)){
	            	c.setTipo(Tipo_Curso.graduacao);
	            }else if(rs.getString("curso_tipo").equals(Tipo_Curso.pos_graduacao)){
	            	c.setTipo(Tipo_Curso.pos_graduacao);
	            }
	            c.setArea(new AreaConhecimento(rs.getInt("area_id"),rs.getString("area_nome")));
	            cursos.add(c);
	        }
		} catch (SQLException e) {
			logger.error("Erro na busca",e);
		}finally {
			try {
				con.close();
				logger.info("Conexão fechada na busca");
			} catch (SQLException e) {
				logger.error("Conexao não pode ser fechada na busca ",e);
			}
		}
		return cursos;
	}
}
