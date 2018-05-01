//package br.uepb.model.usuarios;
//
//import java.util.ArrayList;
//
//import br.uepb.dao.usuarios.AlunoDAO;
//import br.uepb.interfaces.Interface_manterAluno;
///**
// * Essa classe � utilizada como modelo para um objeto do tipo Usuario;
// * A classe cont�m os respectivos getters and setters de seus atributos.
// * Essa classe � a super classe que os usuarios do sistema herdam seus m�todos e atributos, que s�o comuns a todos.
// * @author EquipeACL
// */
//public class Usuario implements Interface_manterAluno {
//	
//	protected AlunoDAO alunoDAO;
//	
//	protected int cpf;
//	protected String nomeCompleto;
//	protected int rg;
//	protected String naturalidade;
//	protected String endereco;
//	protected int telefone;
//	protected String email;
//	protected String senhaAcesso;
//	
//	/**
//	 * M�todo Construtor da classe Usu�rio
//	 * Construtor vazio (utilizado para criar um objeto do tipo Usuario sem par�metros definidos)
//	 */
//	public Usuario() { 
//		alunoDAO = new AlunoDAO();
//	}
//	
//	/**
//	 * M�todo Construtor da classe Usu�rio
//	 * @param cpf, n�mero do cpf do Usu�rio
//	 * @param nomeCompleto, nome completo do Usu�rio
//	 * @param rg, numero do rg do Usu�rio
//	 * @param naturalidade, cidade natal do Usu�rio
//	 * @param endereco, endere�o completo do Usu�rio
//	 * @param telefone, telefone de contato do Usu�rio
//	 * @param email, endere�o de email do Usu�rio
//	 * @param senhaAcesso, senha de acesso ao sistema do Usu�rio
//	 */
//	public Usuario(int cpf, String nomeCompleto, int rg, String naturalidade, String endereco, int telefone,
//			String email, String senhaAcesso) {
//		setCpf(cpf);
//		setNomeCompleto(nomeCompleto);
//		setRg(rg);
//		setNaturalidade(naturalidade);
//		setEndereco(endereco);
//		setTelefone(telefone);
//		setEmail(email);
//		setSenhaAcesso(senhaAcesso);
//		alunoDAO = new AlunoDAO();
//	}
//	
//
//	/**
//	 * M�todo respons�vel por inserir um objeto do tipo Aluno no sistema
//	 * @param aluno, que � um objeto do tipo Aluno
//	 * @return false, caso haja algum problema na valida��o do objeto recebido por par�metro ou caso haja algum problema durante a inser��o do objeto passado por par�metro no banco de dados.
//	 * @return true, caso haja sucesso na inser��o do objeto recebido por par�metro no Banco de Dados
//	 */
//	public boolean createAluno(Aluno aluno) {
//		if(validaAluno(aluno)) {
//			return alunoDAO.createUsuario(aluno);
//		}
//		return false;
//	}
//	/**
//	 * M�todo respons�vel por atualizar um objeto do tipo Aluno no sistema
//	 * @param aluno, que � um objeto do tipo Aluno
//	 * @return false, caso haja algum problema na valida��o do objeto recebido por par�metro ou caso haja algum problema durante a atualiza��o do objeto passado por par�metro no banco de dados.
//	 * @return true, caso haja sucesso na atualiza��o do objeto recebido por par�metro no Banco de Dados
//	 */
//	public boolean updateAluno(Aluno aluno) {
//		if(validaAluno(aluno)) {
//			return alunoDAO.updateUsuario(aluno);
//		}
//		return false;
//	}
//	
//	/**
//	 * M�todo respons�vel por realizar uma busca de um ou mais objetos do tipo Aluno no sistema
//	 * @param aluno, que � um objeto do tipo Aluno
//	 * @return null, caso haja algum problema na valida��o do objeto recebido por par�metro ou caso a busca do objeto passado por par�metro no banco de dados n�o tenha sucesso.
//	 * @return ArrayList<Aluno>, caso haja sucesso na busca de um ou mais objetos do tipo Aluno passado por par�metro no Banco de Dados
//	 */
//	public ArrayList<Aluno> searchAluno(String nome) {
//		return alunoDAO.searchUsuario(nome);
//	}
//	/**
//	 * M�todo respons�vel por validar um objeto do tipo Aluno
//	 * @param a, um objeto do tipo Aluno
//	 * @return false, caso haja algum problema ao longo da valida��o do objeto passado por par�metro
//	 * @return true, caso haja sucesso no processo de valida��o do objeto passado por par�metro
//	 */
//	public boolean validaAluno(Aluno aluno) {
//		if(aluno == null){
//			return false;
//		}
//		if(aluno.getNomeCompleto() == null ||aluno.getNomeCompleto().equals("") ||
//				aluno.getCpf()<=0 || aluno.getCurso()==null || aluno.getEmail() == null || aluno.getEmail().equals("") ||
//				aluno.getEndereco() == null || aluno.getEndereco().equals("") ||
//				aluno.getMatricula() == null || aluno.getMatricula().equals("") ||
//				aluno.getNaturalidade() == null || aluno.getNaturalidade().equals("") ||
//				aluno.getNivel() == null || aluno.getNivel().equals("") ||
//				aluno.getNomeMae() == null || aluno.getNomeMae().equals("") ||
//				aluno.getPeriodoIngresso() < 0||
//				aluno.getAnoIngresso() == null ||
//				aluno.getRg() <=0 ||
//				aluno.getSenhaAcesso() == null || aluno.getSenhaAcesso().equals("") ||
//				aluno.getTelefone()<=0){
//			return false;
//		}
//		return true;
//	}
//	
//	public int getCpf() {
//		return cpf;
//	}
//	public void setCpf(int cpf) {
//		this.cpf = cpf;
//	}
//	public String getNomeCompleto() {
//		return nomeCompleto;
//	}
//	public void setNomeCompleto(String nomeCompleto) {
//		this.nomeCompleto = nomeCompleto;
//	}
//	public int getRg() {
//		return rg;
//	}
//	public void setRg(int rg) {
//		this.rg = rg;
//	}
//	public String getNaturalidade() {
//		return naturalidade;
//	}
//	public void setNaturalidade(String naturalidade) {
//		this.naturalidade = naturalidade;
//	}
//	public String getEndereco() {
//		return endereco;
//	}
//	public void setEndereco(String endereco) {
//		this.endereco = endereco;
//	}
//	public int getTelefone() {
//		return telefone;
//	}
//	public void setTelefone(int telefone) {
//		this.telefone = telefone;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getSenhaAcesso() {
//		return senhaAcesso;
//	}
//	public void setSenhaAcesso(String senhaAcesso) {
//		this.senhaAcesso = senhaAcesso;
//	}
//}
