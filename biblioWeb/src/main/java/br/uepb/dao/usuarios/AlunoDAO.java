package br.uepb.dao.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



import br.uepb.dao.Conexao;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Curso;
import br.uepb.model.enums.Tipo_curso;
import br.uepb.model.enums.Tipo_nivel_aluno;
import br.uepb.model.usuarios.Aluno;


/**
 * Essa classe é responsável por se conectar com a tabela Aluno do Banco de Dados para operações de inserir, atualizar, remover e buscar. 
 * @author EquipeACL
 *
 */
public class AlunoDAO implements Interface_usuario<Aluno> {
	
	private Connection con;
	private static final Logger logger = LogManager.getLogger(AlunoDAO.class);
	
	/**
	 * Método para inserir um Aluno no banco de dados
	 * @param aluno, objeto do tipo Aluno
	 * @throws SQLException
	 * @return true, se a inserção dos dados no banco de dados é bem sucedida
	 * @return false, se houver algum problema durante a inserção dos dados no banco de dados
	 */
	public boolean createUsuario(Aluno aluno) {
		
		PreparedStatement stmt = null;
		
		try {
			
			con = Conexao.iniciarConexao();
			PreparedStatement stmtConsulta = con.prepareStatement("select max(id) from aluno");
			ResultSet rs = stmtConsulta.executeQuery();
			int id = 1;
			if(rs.next()) {
				id = rs.getInt("max(id)")+1;
			}
			stmt = con.prepareStatement("insert into aluno (matricula,cpf,rg,naturalidade,nomeCompleto,nomeMae,"
					+ "endereco,telefone,nivel,email,anoIngresso,periodoIngresso,senha,curso_id) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, aluno.getMatricula()+id);
			stmt.setInt(2, aluno.getCpf());
			stmt.setInt(3, aluno.getRg());
			stmt.setString(4,aluno.getNaturalidade());
			stmt.setString(5,aluno.getNomeCompleto());
			stmt.setString(6, aluno.getNomeMae());
			stmt.setString(7, aluno.getEndereco());
			stmt.setInt(8, aluno.getTelefone());
			stmt.setString(9,aluno.getNivel().toString());
			stmt.setString(10, aluno.getEmail());
			stmt.setDate(11, aluno.getAnoIngresso());
			stmt.setInt(12,aluno.getPeriodoIngresso());
			stmt.setString(13, aluno.getSenhaAcesso());
			stmt.setInt(14, aluno.getCurso().getId());
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na inserção",e);
		} catch (Exception e) {
			logger.error("Erro na inserção",e);
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				logger.error("Erro ao fechar conexão",e);
			}
		}
		return false;
	}
	/**
	 * Método para remover um Aluno no banco de dados
	 * @param aluno, objeto do tipo Aluno
	 * @throws SQLException
	 * @return true, se a remoção dos dados no banco de dados é bem sucedida
	 * @return false, se houver algum problema durante a remoção dos dados no banco de dados
	 */
	public boolean removeUsuario(Aluno aluno) {
		
		PreparedStatement stmt = null;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("delete from aluno where matricula = ?");
			stmt.setString(1, aluno.getMatricula());
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.error("Erro na remoção",e);
		} catch (Exception e) {
			logger.error("Erro na remoção",e);
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (SQLException e) {
				logger.error("Erro ao fechar conexão",e);
			}
		}
		return false;
	}

	/**
	 * Método para atualizar um Aluno no banco de dados
	 * @param aluno, objeto do tipo Aluno
	 * @throws SQLException
	 * @return true, se a atualização dos dados no banco de dados é bem sucedida
	 * @return false, se houver algum problema durante a atualização dos dados no banco de dados
	 */
	public boolean updateUsuario(Aluno aluno) {

		PreparedStatement stmt = null;

		try {

			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("update aluno set cpf=?,rg=?,naturalidade=?,nomeCompleto=?,nomeMae=?,endereco=?,telefone=?,nivel=?,email=?,anoIngresso=?,periodoIngresso=?,senha=?,curso_id=? where matricula = ?");
			stmt.setInt(1, aluno.getCpf());
			stmt.setInt(2, aluno.getRg());
			stmt.setString(3, aluno.getNaturalidade());
			stmt.setString(4, aluno.getNomeCompleto());
			stmt.setString(5, aluno.getNomeMae());
			stmt.setString(6, aluno.getEndereco());
			stmt.setInt(7, aluno.getTelefone());
			stmt.setString(8, aluno.getNivel().toString());
			stmt.setString(9, aluno.getEmail());
			stmt.setDate(10, aluno.getAnoIngresso());
			stmt.setInt(11, aluno.getPeriodoIngresso());
			stmt.setString(12, aluno.getSenhaAcesso());
			stmt.setInt(13, aluno.getCurso().getId());
			stmt.setString(14, aluno.getMatricula());

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
		}
		return false;
	}
	
	/**
	 * Método para buscar um ou mais Alunos no banco de dados
	 * @param aluno, objeto do tipo Aluno
	 * @throws SQLException
	 * @return ArrayList<Aluno>, se houver um ou mais alunos que atendam ao requisito
	 * @return null, se a busca não retornar nenhum resultado
	 */
	public ArrayList<Aluno> searchUsuario(Aluno aluno) {
		ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AreaConhecimento area;
		Curso curso;
		Aluno a;
		
		try {
			con = Conexao.iniciarConexao();
			stmt = con.prepareStatement("select A.matricula as aluno_matricula, A.cpf as aluno_cpf, A.rg as aluno_rg, A.naturalidade as aluno_naturalidade, " + 
					"A.nomeCompleto as aluno_nomeCompleto, A.nomeMae as aluno_nomeMae, A.endereco as aluno_endereco, A.telefone as aluno_telefone, " + 
					"A.nivel as aluno_nivel, A.email as aluno_email, A.anoIngresso as aluno_anoIngresso, A.periodoIngresso as aluno_periodoIngresso, A.senha as aluno_senha, " + 
					"C.id as curso_id, C.nome as curso_nome, C.sigla as curso_sigla, C.tipo as curso_tipo, " + 
					"AC.id as areaConhecimento_id, AC.nome as areaConhecimento_nome " + 
					"from aluno as A " + 
					"inner join curso as C on A.curso_id = C.id " + 
					"inner join area_conhecimento as AC on C.area_conhecimento_id =  AC.id " + 
					"where A.nomeCompleto like ?");
			stmt.setString(1,"%"+aluno.getNomeCompleto()+"%");
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				area = new AreaConhecimento(rs.getInt("areaConhecimento_id"),rs.getString("areaConhecimento_nome"));
				curso = new Curso(rs.getInt("curso_id"),rs.getString("curso_nome"), rs.getString("curso_sigla"), area, Tipo_curso.valueOf(rs.getString("curso_tipo")));
				a = new Aluno(rs.getString("aluno_matricula"),rs.getInt("aluno_cpf"), rs.getInt("aluno_rg"), rs.getString("aluno_naturalidade"), rs.getString("aluno_nomeCompleto"), rs.getString("aluno_nomeMae"),
						rs.getString("aluno_endereco"), rs.getInt("aluno_telefone"), curso, Tipo_nivel_aluno.valueOf(rs.getString("aluno_nivel")), rs.getString("aluno_email"), rs.getDate("aluno_anoIngresso"), rs.getInt("aluno_periodoIngresso"), rs.getString("aluno_senha"));
				listaAluno.add(a);				
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
		return listaAluno;
	}
	
		

}
