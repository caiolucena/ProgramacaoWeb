package br.uepb.model.usuarios;

import java.util.ArrayList;
import br.uepb.dao.AreaConhecimentoDAO;
import br.uepb.dao.AutorDAO;
import br.uepb.dao.CursoDAO;
import br.uepb.dao.EditoraDAO;
import br.uepb.dao.usuarios.FuncionarioDAO;
import br.uepb.interfaces.DAO_Acervo;
import br.uepb.interfaces.DAO_Dependencia;
import br.uepb.interfaces.IFAcervo;
import br.uepb.interfaces.IFDependencia;
import br.uepb.interfaces.Interface_manterAcervo;
import br.uepb.interfaces.Interface_manterDependencias;
import br.uepb.interfaces.Interface_manterFuncionario;
import br.uepb.interfaces.DAO_Acervo;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Autor;
import br.uepb.model.Curso;
import br.uepb.model.Editora;

/**
 * Essa classe é utilizada como modelo para um objeto do tipo Administrador.
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Administrador extende a classe Usuário, que contém os atributos e métodos comuns a todos os usuários do sistema.
 * A classe Administrador implementa as interfaces que contém métodos para manter o usuário e manter o acervo.
 * A classe Administrador contém um método único, que serve para remover um aluno do sistema.
 * @author EquipeACL
 */
public class Administrador extends Usuario implements Interface_manterFuncionario,Interface_manterAcervo, Interface_manterDependencias{
	private FuncionarioDAO funcionarioDAO;
	private AreaConhecimentoDAO areaDAO;
	private CursoDAO cursoDAO;
	private EditoraDAO editoraDAO;
	private AutorDAO autorDAO;
	private String nomeUsuario;
	
	/**
	 * Método construtor da classe Administrador 
	 */
	public Administrador() {
		this.funcionarioDAO = new FuncionarioDAO();
		this.areaDAO = new AreaConhecimentoDAO();
		this.cursoDAO = new CursoDAO();
		this.editoraDAO = new EditoraDAO();
		this.autorDAO = new AutorDAO();
	}

	/**
	 * Método construtor da classe Administrador
	 * @param nomeUsuario, que é o nome de usuário do Administrador no sistema 
	 */
	public Administrador(String nomeUsuario) {
		setNomeUsuario(nomeUsuario);
		this.funcionarioDAO = new FuncionarioDAO();
		this.areaDAO = new AreaConhecimentoDAO();
		this.cursoDAO = new CursoDAO();
		this.editoraDAO = new EditoraDAO();
		this.autorDAO = new AutorDAO();
	}
	
	/**
	 * Método construtor da classe Administrador
	 * @param cpf, valor inteiro correspondente ao cpf do Administrador do sistema
	 * @param nomeCompleto, que é o nome do Administrador do sistema
	 * @param rg, valor inteiro correspondete ao RG do Administrador do sistema
	 * @param naturalidade, que indica a naturalidade do Administrador do sistema
	 * @param endereco, que é o endereço do Administrador do sistema
	 * @param telefone, que é o numero de telefone do Administrador do sistema
	 * @param email, que é o email de contato do Administrador do sistema
	 * @param senhaAcesso, que é a senha de acesso ao sistema do Administrador
	 * @param nomeUsuario, que é o nome de usuario do Administrador do sistema
	 */
	public Administrador(int cpf, String nomeCompleto, int rg, String naturalidade, String endereco, int telefone,
			String email, String senhaAcesso,String nomeUsuario) {
		super(cpf,nomeCompleto,rg,naturalidade,endereco,telefone,email,senhaAcesso);
		setNomeUsuario(nomeUsuario);
		this.funcionarioDAO = new FuncionarioDAO();
		this.areaDAO = new AreaConhecimentoDAO();
		this.cursoDAO = new CursoDAO();
		this.editoraDAO = new EditoraDAO();
		this.autorDAO = new AutorDAO();
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
	
	// - - - - - - - - - - - - - - - - - - - - - MANTER FUNCIONARIO - - - - - - - - - - - - - - - - - - - - - - -
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
	public ArrayList<Funcionario> searchFuncionario(String nome) {
		return funcionarioDAO.searchUsuario(nome);		
	}
	
	// - - - - - - - - - - - - - - - - - - - - - MANTER ACERVO - - - - - - - - - - - - - - - - - - - - - - -
	/**
	 * Método responsável por inserir um objeto que implementa a interface Acervo no banco de dados
	 * @param itemDao, que é um objeto especifica qual o tipo de objeto DAO
	 * @param item, que é o objeto que deve ser salvo no banco
	 * @return true, se a inserção for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	public boolean createItemAcervo(DAO_Acervo itemDao,IFAcervo item) {
		if(item.validaItem()) {
			return itemDao.createItemAcervo(item);
		}
		return false;
	}
	/**
	 * Método responsável por remover um objeto do acervo do banco de dados do sistema
	 * @param itemDao, que é um objeto especifica qual o tipo de objeto DAO
	 * @param item, que é o objeto que deve ser salvo no banco
	 * @return true, se a remoção for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	public boolean removeItemAcervo(DAO_Acervo itemDao,IFAcervo item) {
		if(item.validaItem()) {
			return itemDao.removeItemAcervo(item);
		}
		return false;
	}
	/**
	 * Método responsável por atualizar um objeto do acervo do banco de dados do sistema
	 * @param itemDao, que é um objeto especifica qual o tipo de objeto DAO
	 * @param item, que é o objeto que deve ser salvo no banco
	 * @return true, se a atualização for bem sucedida
	 * @return false, se ocorrer algum erro na operação
	 */
	public boolean updateItemAcervo(DAO_Acervo itemDao,IFAcervo item) {
		if(item.validaItem()) {
			return itemDao.updateItemAcervo(item);
		}
		return false;
	}
	/**
	 * Método responsável por realizar uma busca de um ou mais objetos de acervo do banco de dados do sistema
	 * @param itemDao, que é um objeto especifica qual o tipo de objeto DAO
	 * @param item, que é o objeto que armazenar dados que devem ser buscados
	 * @return null, caso haja algum problema na validação do objeto recebido por parâmetro ou caso a busca do objeto passado por parâmetro no banco de dados não tenha sucesso.
	 * @return ArrayList<IFAcervo>, caso haja sucesso na busca de um ou mais objetos do acervo do banco de dados passado por parâmetro
	 */
	public ArrayList<IFAcervo> searchItemAcervo(DAO_Acervo itemDao,String titulo) {
		return itemDao.searchItemAcervo(titulo);		
	}
	/**
	 * Método responsável por validar os parametros dos objetos do tipo Funcionario
	 * @param funcionario, que é o objeto do tipo Funcionario que deve ser validado
	 * @return true, se os parametros do funcionario estiverem validos
	 * @return false, se algum dos parametros estiverem nulos ou vazios.
	 */
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
	
	
	
	// - - - - - - - - - - - - - - - - - - - - - MANTER DEPENDENCIAS - - - - - - - - - - - - - - - - - - - - - - -
	
	
	public boolean create(DAO_Dependencia itemDAO, IFDependencia item) {
		if(item.validaDependencia()){
			return itemDAO.createItemDependencia(item);
		}
		return false;
	}

	public boolean update(DAO_Dependencia itemDAO, IFDependencia item) {
		if(item.validaDependencia()){
			return itemDAO.updateItemDependencia(item);
		}
		return false;
	}

	public boolean remove(DAO_Dependencia itemDAO, IFDependencia item) {
		if(item.validaDependencia()){
			return itemDAO.removeItemDependencia(item);
		}
		return false;
	}

	public ArrayList<IFDependencia> search(DAO_Dependencia itemDAO, String nome) {
		return itemDAO.searchItemDependencia(nome);
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}

