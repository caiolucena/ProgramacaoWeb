package br.uepb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import br.uepb.model.Orientador;

public class OrientadorDAO {
	private Connection con;
	private static final Logger logger = LogManager.getLogger(OrientadorDAO.class);
	
	public OrientadorDAO() throws Exception{
		con = Conexao.iniciarConexao();
	}
	
	public boolean createOrientador(Orientador orientador){
		String sql = "insert into autor(nome,formacao)values(?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, orientador.getNome());
			stmt.setString(2, orientador.getFormacao());
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
	
	public boolean removeAutor(Orientador orientador) {
		String sql = "delete autor where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1,orientador.getId());
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
	
	public boolean updateAutor(Orientador orientador) {
		String sql = "update autor set nome=?, formacao=? where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,orientador.getNome());
			stmt.setString(2, orientador.getFormacao());
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
	
	public ArrayList<Orientador> searchAutor(Orientador orientador) {
		String sql = "select * from orientador where nome=%?%";
		ArrayList<Orientador> orientadores = new ArrayList<Orientador>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,orientador.getNome());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
	            Orientador o = new Orientador();
	            o.setId(rs.getInt("id"));
	            o.setNome(rs.getString("nome"));
	            o.setFormacao(rs.getString("formacao"));
	            orientadores.add(o);
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
		return orientadores;
	}
}
