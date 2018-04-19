package br.uepb.dao.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import br.uepb.dao.Conexao;
import br.uepb.model.usuarios.Funcionario;

/**
 * Essa classe é responsável por se conectar com a tabela funcionario do Banco de Dados para operações de inserir, atualizar, remover e buscar. 
 * @author EquipeACL
 *
 */
public class FuncionarioDAO implements Interface_usuario<Funcionario> {

	private Connection con;
	private static final Logger logger = LogManager.getLogger(FuncionarioDAO.class);
	public FuncionarioDAO() {
		try {
			con = Conexao.iniciarConexao();
		} catch (Exception e) {
			logger.error("Conexao nao foi aberta",e);
		}
	}	
	/**
	 * Método para inserir um Funcionario no banco de dados
	 * @param funcionario, objeto do tipo Funcionario
	 * @throws SQLException
	 * @return true, se a inserção dos dados no banco de dados é bem sucedida
	 * @return false, se houver algum problema durante a inserção dos dados no banco de dados
	 */
	public boolean createUsuario(Funcionario funcionario) {

		PreparedStatement stmt = null;

		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement(
					"insert into funcionario(cpf,rg,naturalidade,nomeCompleto,nomeUsuario,endereco,telefone,email,senha) values (?,?,?,?,?,?,?,?,?)");

			stmt.setInt(1, funcionario.getCpf());
			stmt.setInt(2, funcionario.getRg());
			stmt.setString(3, funcionario.getNaturalidade());
			stmt.setString(4, funcionario.getNomeCompleto());
			stmt.setString(5, funcionario.getNomeUsuario());
			stmt.setString(6, funcionario.getEndereco());
			stmt.setInt(7, funcionario.getTelefone());
			stmt.setString(8, funcionario.getEmail());
			stmt.setString(9, funcionario.getSenhaAcesso());

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
	 * Método para remover um Funcionario no banco de dados
	 * @param funcionario, objeto do tipo Funcionario
	 * @throws SQLException
	 * @return true, se a inserção dos dados no banco de dados é bem sucedida
	 * @return false, se houver algum problema durante a inserção dos dados no banco de dados
	 */
	public boolean removeUsuario(Funcionario funcionario) {
		PreparedStatement stmt = null;

		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("delete from funcionario where cpf=?");
			stmt.setInt(1, funcionario.getCpf());

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
	 * Método para atualizar um Funcionario no banco de dados
	 * @param funcionario, objeto do tipo Funcionario
	 * @throws SQLException
	 * @return true, se a inserção dos dados no banco de dados é bem sucedida
	 * @return false, se houver algum problema durante a inserção dos dados no banco de dados
	 */
	public boolean updateUsuario(Funcionario funcionario) {
		PreparedStatement stmt = null;

		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("update funcionario set rg=?,naturalidade=?,nomeCompleto=?,nomeUsuario=?,endereco=?,telefone=?,email=?,senha=? where cpf=?");

			stmt.setInt(1, funcionario.getRg());
			stmt.setString(2, funcionario.getNaturalidade());
			stmt.setString(3, funcionario.getNomeCompleto());
			stmt.setString(4, funcionario.getNomeUsuario());
			stmt.setString(5, funcionario.getEndereco());
			stmt.setInt(6, funcionario.getTelefone());
			stmt.setString(7, funcionario.getEmail());
			stmt.setString(8, funcionario.getSenhaAcesso());
			stmt.setInt(9, funcionario.getCpf());

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
	 * Método para buscar um Funcionario no banco de dados
	 * @param funcionario, objeto do tipo Funcionario
	 * @throws SQLException
	 * @return ArrayList<Funcionario>, se houver um ou mais funcionarios que atendam ao requisito
	 * @return null, se a busca não retornar nenhum resultado	 
	 */
	public ArrayList<Funcionario> searchUsuario(Funcionario funcionario) {
		ArrayList<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Funcionario f; 
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("select * from funcionario where nomeCompleto like ?");
			stmt.setString(1,"%"+funcionario.getNomeCompleto()+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				f = new Funcionario(rs.getInt("cpf"), rs.getString("nomeCompleto"), rs.getInt("rg"), rs.getString("naturalidade"), rs.getString("endereco"), rs.getInt("telefone"), rs.getString("email"), rs.getString("senha"), rs.getString("nomeUsuario"));
				listaFuncionario.add(f);
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
		
		return listaFuncionario;
	}

}
