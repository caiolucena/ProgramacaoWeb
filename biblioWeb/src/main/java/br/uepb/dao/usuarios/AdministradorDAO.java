package br.uepb.dao.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.dao.Conexao;
import br.uepb.model.usuarios.Administrador;
import br.uepb.model.usuarios.Funcionario;
/**
 * Essa classe é responsável por se conectar com a tabela funcionario do Banco de Dados para operações de inserir, atualizar, remover e buscar objetos do tipo Administrador. 
 * @author EquipeACL
 *
 */
public class AdministradorDAO {
	private Connection con;
	private static final Logger logger = LogManager.getLogger(AdministradorDAO.class);
	public AdministradorDAO() {
		try {
			con = Conexao.iniciarConexao();
		} catch (Exception e) {
			logger.error("Conexao nao foi aberta",e);
		}
	}	
	/**
	 * Método para inserir um Administrador no banco de dados
	 * @param adm, objeto do tipo Administrador
	 * @throws SQLException
	 * @return true, se a inserção dos dados no banco de dados é bem sucedida
	 * @return false, se houver algum problema durante a inserção dos dados no banco de dados
	 */
	public boolean createAdministrador(Administrador adm) {

		PreparedStatement stmt = null;

		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement(
					"insert into funcionario(cpf,rg,naturalidade,nomeCompleto,nomeUsuario,endereco,telefone,email,senha,admin) values (?,?,?,?,?,?,?,?,?,?)");

			stmt.setInt(1, adm.getCpf());
			stmt.setInt(2, adm.getRg());
			stmt.setString(3, adm.getNaturalidade());
			stmt.setString(4, adm.getNomeCompleto());
			stmt.setString(5, adm.getNomeUsuario());
			stmt.setString(6, adm.getEndereco());
			stmt.setInt(7, adm.getTelefone());
			stmt.setString(8, adm.getEmail());
			stmt.setString(9, adm.getSenhaAcesso());
			stmt.setInt(10, 1);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na inserção", e);
		} catch (Exception e) {
			logger.error("Erro na inserção", e);
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				logger.error("Erro ao fechar conexão", e);
			}
		}

		return false;
	}

	/**
	 * Método para remover um Administrador no banco de dados
	 * @param adm, objeto do tipo Administrador
	 * @throws SQLException
	 * @return true, se a inserção dos dados no banco de dados é bem sucedida
	 * @return false, se houver algum problema durante a inserção dos dados no banco de dados
	 */
	public boolean removeUsuario(Administrador adm) {
		PreparedStatement stmt = null;

		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("delete from funcionario where cpf=? and admin = 1");
			stmt.setInt(1, adm.getCpf());

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na remoção", e);
		} catch (Exception e) {
			logger.error("Erro na remoção", e);
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				logger.error("Erro ao fechar conexão", e);
			}
		}
		return false;
	}

	/**
	 * Método para atualizar um Administrador no banco de dados
	 * @param adm, objeto do tipo Administrador
	 * @throws SQLException
	 * @return true, se a inserção dos dados no banco de dados é bem sucedida
	 * @return false, se houver algum problema durante a inserção dos dados no banco de dados
	 */
	public boolean updateUsuario(Administrador adm) {
		PreparedStatement stmt = null;

		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("update funcionario set rg=?,naturalidade=?,nomeCompleto=?,nomeUsuario=?,endereco=?,telefone=?,email=?,senha=? where cpf=?");

			stmt.setInt(1, adm.getRg());
			stmt.setString(2, adm.getNaturalidade());
			stmt.setString(3, adm.getNomeCompleto());
			stmt.setString(4, adm.getNomeUsuario());
			stmt.setString(5, adm.getEndereco());
			stmt.setInt(6, adm.getTelefone());
			stmt.setString(7, adm.getEmail());
			stmt.setString(8, adm.getSenhaAcesso());
			stmt.setInt(9, adm.getCpf());

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na atualização", e);
		} catch (Exception e) {
			logger.error("Erro na atualização", e);
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				logger.error("Erro ao fechar conexão", e);
			}
		}		return false;
	}

	/**
	 * Método para buscar um Administrador no banco de dados
	 * @param adm, objeto do tipo Administrador
	 * @throws SQLException
	 * @return ArrayList<Administrador>, se houver um ou mais administradores que atendam ao requisito
	 * @return null, se a busca não retornar nenhum resultado	 
	 */
	public ArrayList<Administrador> searchUsuario(Administrador adm) {
		ArrayList<Administrador> listaAdministradores = new ArrayList<Administrador>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Administrador  administrador; 
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("select * from funcionario where nomeCompleto like ? and admin = 1");
			stmt.setString(1,"%"+adm.getNomeCompleto()+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				administrador = new Administrador(rs.getInt("cpf"), rs.getString("nomeCompleto"), rs.getInt("rg"), rs.getString("naturalidade"), rs.getString("endereco"), rs.getInt("telefone"), rs.getString("email"), rs.getString("senha"), rs.getString("nomeUsuario"));
				listaAdministradores.add(administrador);
			}
			
		} catch (SQLException e) {
			logger.error("Erro na busca", e);
		} catch (Exception e) {
			logger.error("Erro na busca", e);
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				logger.error("Erro ao fechar conexão", e);
			}
		}
		
		return listaAdministradores;
	}
}
