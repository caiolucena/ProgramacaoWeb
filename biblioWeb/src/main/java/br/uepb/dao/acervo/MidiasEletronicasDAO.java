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
import br.uepb.model.acervo.Livro;
import br.uepb.model.acervo.Midias_Eletronicas;
import br.uepb.model.enums.Tipo_Midia;

public class MidiasEletronicasDAO implements Item_Acervo<Midias_Eletronicas>{
	private Connection con;
	private static final Logger logger = LogManager.getLogger(MidiasEletronicasDAO.class); 
	
	public MidiasEletronicasDAO() throws Exception{
		con = Conexao.iniciarConexao();
	}
	
	public boolean createItemAcervo(Midias_Eletronicas midia) {
		String sql = "insert into midia(titulo,tipo,data_gravacao) values (?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, midia.getTitulo());
			stmt.setString(2,midia.getTipo().toString());
			stmt.setDate(3,(Date)midia.getData_gravacao());
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

	public boolean removeItemAcervo(Midias_Eletronicas midia) {
		String sql = "delete midia where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, midia.getId());
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

	public boolean updateItemAcervo(Midias_Eletronicas midia) {
		String sql = "update into midia set titulo=?,tipo=?,data_gravacao=? where id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, midia.getTitulo());
			stmt.setString(2,midia.getTipo().toString());
			stmt.setDate(3,(Date)midia.getData_gravacao());
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

	public ArrayList<Midias_Eletronicas> searchItemAcervo(Midias_Eletronicas midia) {
		String sql = "select * from midia where titulo=%?%";
		ArrayList<Midias_Eletronicas> midias = new ArrayList<Midias_Eletronicas>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,midia.getTitulo());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
	            Midias_Eletronicas m = new Midias_Eletronicas();
	            m.setTitulo(rs.getString("titulo"));
	            if(rs.getString("tipo").equals(Tipo_Midia.CD)){
	            	m.setTipo(Tipo_Midia.CD);
	            }else if(rs.getString("tipo").equals(Tipo_Midia.DVD)){
	            	m.setTipo(Tipo_Midia.DVD);
	            }
	            m.setData_gravacao(rs.getDate("data_gravacao"));
	            midias.add(m);
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
		return midias;
	}

	
}
