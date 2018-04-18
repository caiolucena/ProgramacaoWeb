package br.uepb.model.usuarios;
/**
 * Essa classe é utilizada como modelo para um objeto do tipo Funcionário;
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Aluno extende a classe Usuário, que contém os atributos e métodos comuns a todos os usuários do sistema.
 * @author EquipeACL
 */
public class Funcionario extends Usuario {
	
	private String nomeUsuario;
	/**
	 * Método construtor da classe Funcionário
	 * @param cpf, número do CPF do Funcionário
	 * @param nomeCompleto, nome completo do Funcionário
	 * @param rg, número do RG do Funcionário
	 * @param naturalidade, cidade natal do Funcionário
	 * @param endereco, endereço completo do Funcionário
	 * @param telefone, numero de telefone do Funcionário
	 * @param email, endereço de email do Funcionário
	 * @param senhaAcesso, senha de acesso ao sistema do Funcionário
	 * @param nomeUsuario, nome de usuario no sistema do Funcionário
	 */
	public Funcionario(int cpf, String nomeCompleto, int rg, String naturalidade, String endereco, int telefone,
			String email, String senhaAcesso, String nomeUsuario) {
		super(cpf, nomeCompleto, rg, naturalidade, endereco, telefone, email, senhaAcesso);
		setNomeUsuario(nomeUsuario);
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
}
