package br.uepb.model.usuarios;

import java.util.ArrayList;

import br.uepb.dao.Item_Acervo;
import br.uepb.dao.usuarios.FuncionarioDAO;
import br.uepb.model.acervo.Acervo;

/**
 * Essa classe é utilizada como modelo para um objeto do tipo Administrador.
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Administrador extende a classe Usuário, que contém os atributos e métodos comuns a todos os usuários do sistema.
 * A classe Administrador implementa as interfaces que contém métodos para manter o usuário e manter o acervo.
 * A classe Administrador contém um método único, que serve para remover um aluno do sistema.
 * @author EquipeACL
 */
public class Administrador extends Usuario implements Interface_manterFuncionario,Interface_manterAcervo{
	private FuncionarioDAO funcionarioDAO;
	private String nomeUsuario;
	public Administrador() { 
		funcionarioDAO = new FuncionarioDAO();
	}
	/**
	 * Método construtor da classe Administrador
	 * @param nomeUsuario, que é o nome de usuário do Administrador no sistema 
	 */
	public Administrador(String nomeUsuario) {
		super();
		setNomeUsuario(nomeUsuario);
		this.funcionarioDAO = new FuncionarioDAO();
		
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	/**
	 * Método responsável por remover um objeto do tipo Aluno no sistema
	 * @param aluno, que é um objeto do tipo Aluno
	 * @return false, caso haja algum problema na validação do objeto recebido por parâmetro ou caso haja algum problema durante a remoção do objeto passado por parâmetro no banco de dados.
	 * @return true, caso haja sucesso na inserção do objeto recebido por parâmetro no Banco de Dados
	 */
	public boolean removeAluno(Aluno aluno) {
		if(validaAluno(aluno)) {
			return alunoDAO.removeUsuario(aluno);
		}
		return false;
		
	}
	/**
	 * Método responsável por criar um objeto do tipo Funcionário no sistema
	 * @param f, que é um objeto do tipo Funcionário
	 * @return false, caso haja algum problema na validação do objeto recebido por parâmetro ou caso haja algum problema durante a inserção do objeto passado por parâmetro no banco de dados.
	 * @return true, caso haja sucesso na inserção do objeto Funcionário passado por parâmetro no Banco de Dados
	 */
	public boolean createFuncionario(Funcionario f) {
		if(validarFuncionario(f)) {
			return funcionarioDAO.createUsuario(f);
		}
		return false;
	}
	/**
	 * Método responsável por atualizar um objeto do tipo Funcionário no sistema
	 * @param f, que é um objeto do tipo Funcionário
	 * @return false, caso haja algum problema na validação do objeto recebido por parâmetro ou caso haja algum problema durante a atualização do objeto passado por parâmetro no banco de dados.
	 * @return true, caso haja sucesso na atualização do objeto Funcionário passado por parâmetro no Banco de Dados
	 */
	public boolean updateFuncionario(Funcionario f) {
		if(validarFuncionario(f)) {
			return funcionarioDAO.updateUsuario(f);
		}
		return false;
	}
	/**
	 * Método responsável por remover um objeto do tipo Funcionário no sistema
	 * @param f, que é um objeto do tipo Funcionário
	 * @return false, caso haja algum problema na validação do objeto recebido por parâmetro ou caso haja algum problema durante a remoção do objeto passado por parâmetro no banco de dados.
	 * @return true, caso haja sucesso na remoção do objeto Funcionário passado por parâmetro no Banco de Dados
	 */
	public boolean removeFuncionario(Funcionario f) {
		if(validarFuncionario(f)) {
			return funcionarioDAO.removeUsuario(f);
		}
		return false;
	}
	/**
	 * Método responsável por realizar uma busca de um ou mais objetos do tipo Funcionário no sistema
	 * @param f, que é um objeto do tipo Funcionário
	 * @return null, caso haja algum problema na validação do objeto recebido por parâmetro ou caso a busca do objeto passado por parâmetro no banco de dados não tenha sucesso.
	 * @return ArrayList<Funcionario>, caso haja sucesso na busca de um ou mais objetos do tipo Funcionário passado por parâmetro no Banco de Dados
	 */
	public ArrayList<Funcionario> searchFuncionario(Funcionario f) {
		if(validarFuncionario(f)) {
			return funcionarioDAO.searchUsuario(f);
		}
		return null;
	}

	public boolean createItemAcervo(Item_Acervo itemDao,Acervo item) {
		//TODO Validar dados do item
		return itemDao.createItemAcervo(item);
	}

	public boolean removeItemAcervo(Item_Acervo itemDao,Acervo item) {
		//TODO Validar dados do item
		return itemDao.removeItemAcervo(item);
	}

	public boolean updateItemAcervo(Item_Acervo itemDao,Acervo item) {
		//TODO Validar dados do item
		return itemDao.updateItemAcervo(item);
	}

	public ArrayList<Acervo> searchItemAcervo(Item_Acervo itemDao,Acervo item) {
		//TODO Validar dados do item
		return itemDao.searchItemAcervo(item);
	}
	private boolean validarFuncionario(Funcionario funcionario) {
		if(funcionario == null){
			return false;
		}
		if(funcionario.getCpf()<=0||
				funcionario.getEmail() == null || funcionario.getEmail().equals("")||
				funcionario.getEndereco()==null || funcionario.getEndereco().equals("")||
				funcionario.getNaturalidade()==null || funcionario.getNaturalidade().equals("")||
				funcionario.getNomeCompleto()==null || funcionario.getNomeCompleto().equals("")||
				funcionario.getRg()<=0 ||
				funcionario.getSenhaAcesso() == null || funcionario.getSenhaAcesso().equals("") ||
				funcionario.getTelefone()<=0){
			return false;
		}
		return true;
	}
}
