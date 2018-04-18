package br.uepb.model.usuarios;

import java.sql.Date;
import java.text.DateFormat;

import br.uepb.model.Curso;
import br.uepb.model.enums.Tipo_nivel_aluno;
/**
 * Essa classe é responsável por criar um objeto do tipo Aluno;
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Aluno extende a classe Usuário, que contém os atributos e métodos comuns a todos os usuários do sistema.
 * @author EquipeACL
 */
public class Aluno extends Usuario {
	
	private String matricula;
	private String nomeMae;
	private Curso curso;
	private Tipo_nivel_aluno nivel;
	private Date anoIngresso;
	private int periodoIngresso;

	/**
	 * Método construtor da classe Aluno
	 * Construtor vazio (utilizado para criar um objeto do tipo Aluno sem parâmetros definidos)
	 */
	public Aluno() {	
	}
	/**
	 * Método construtor da classe Aluno (utilizado para instanciar objetos durante a busca de um objeto do tipo Aluno no Banco de Dados)
	 * @param matricula, matrícula do aluno
	 * @param cpf, número do CPF do aluno
	 * @param rg, número do RG do aluno
	 * @param naturalidade, cidade natal do aluno
	 * @param nomeCompleto, nome completo do aluno
	 * @param nomeMae, nome completo da mãe do aluno
	 * @param endereco, endereço do aluno
	 * @param telefone, telefone para contato do aluno
	 * @param curso, Objeto do tipo Curso referente ao curso que o aluno ingressou
	 * @param nivel, Enum do nivel do aluno (se é graduando, mestrando, doutorando ou pós-doutorando)
	 * @param email, endereço de email do aluno
	 * @param anoIngresso, o ano de ingresso do aluno no curso
	 * @param periodoIngresso, o período de ingresso do aluno no curso
	 * @param senhaAcesso, a senha de acesso ao sistema do aluno
	 */
	public Aluno(String matricula, int cpf, int rg, String naturalidade, String nomeCompleto, String nomeMae,
			String endereco, int telefone, Curso curso, Tipo_nivel_aluno nivel, String email, Date anoIngresso,
			int periodoIngresso, String senhaAcesso) {
		super(cpf, nomeCompleto, rg, naturalidade, endereco, telefone, email, senhaAcesso);
		setMatricula(matricula);
		setCurso(curso);
		setNivel(nivel);
		setAnoIngresso(anoIngresso);
		setPeriodoIngresso(periodoIngresso);
	}
	/**
	 * Método construtor da classe Aluno (utilizado como objeto que será passado por parâmetro durante a inserção de um objeto do tipo Aluno no Banco de Dados
	 * @param cpf, número do CPF do aluno
	 * @param rg, número do RG do aluno
	 * @param naturalidade, cidade natal do aluno
	 * @param nomeCompleto, nome completo do aluno
	 * @param nomeMae, nome completo da mãe do aluno
	 * @param endereco, endereço do aluno
	 * @param telefone, telefone para contato do aluno
	 * @param curso, Objeto do tipo Curso referente ao curso que o aluno ingressou
	 * @param nivel, Enum do nivel do aluno (se é graduando, mestrando, doutorando ou pós-doutorando)
	 * @param email, endereço de email do aluno
	 * @param anoIngresso, o ano de ingresso do aluno no curso
	 * @param periodoIngresso, o período de ingresso do aluno no curso
	 * @param senhaAcesso, a senha de acesso ao sistema do aluno
	 */
	public Aluno(int cpf, int rg, String naturalidade, String nomeCompleto, String nomeMae,
			String endereco, int telefone, Curso curso, Tipo_nivel_aluno nivel, String email, Date anoIngresso, int periodoIngresso,
			String senhaAcesso) {
		super(cpf, nomeCompleto, rg, naturalidade, endereco, telefone, email, senhaAcesso);

		setNomeMae(nomeMae);
		setCurso(curso);
		setNivel(nivel);
		setAnoIngresso(anoIngresso);
		setPeriodoIngresso(periodoIngresso);
		gerarMatrcula();
		
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Tipo_nivel_aluno getNivel() {
		return nivel;
	}
	public void setNivel(Tipo_nivel_aluno nivel) {
		this.nivel = nivel;
	}
	public Date getAnoIngresso() {
		return anoIngresso;
	}
	public void setAnoIngresso(Date anoIngresso) {
		this.anoIngresso = anoIngresso;
	}
	public int getPeriodoIngresso() {
		return periodoIngresso;
	}
	public void setPeriodoIngresso(int periodoIngresso) {
		this.periodoIngresso = periodoIngresso;
	}
	/**
	 * Método utilizaod para gerar a matrícula do aluno, utilizando os atributos da própria classe
	 */
	public void gerarMatrcula() {
		
		this.matricula = "";
		this.matricula+= this.nivel+this.curso.getSigla()+this.anoIngresso.toString().substring(2, 4)+this.periodoIngresso;
		
	}
	
}

