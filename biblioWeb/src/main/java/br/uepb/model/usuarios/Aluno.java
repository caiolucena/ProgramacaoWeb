package br.uepb.model.usuarios;

import java.sql.Date;
import java.text.DateFormat;

import br.uepb.model.Curso;
import br.uepb.model.enums.Tipo_nivel_aluno;

public class Aluno extends Usuario {
	
	private String matricula;
	private String nomeMae;
	private Curso curso;
	private Tipo_nivel_aluno nivel;
	private Date anoIngresso;
	private int periodoIngresso;

	
	public Aluno() {	
	}
		
	public Aluno(String matricula, int cpf, int rg, String naturalidade, String nomeCompleto, String nomeMae,
			String endereco, int telefone, Curso curso, Tipo_nivel_aluno nivel, String email, Date anoIngresso,
			int periodoIngresso, String senhaAcesso) {
		super(cpf, nomeCompleto, rg, naturalidade, endereco, telefone, email, senhaAcesso);
		this.nomeMae = nomeMae;
		this.curso = curso;
		this.nivel = nivel;
		this.anoIngresso = anoIngresso;
		this.periodoIngresso = periodoIngresso;
	}

	public Aluno(int cpf, int rg, String naturalidade, String nomeCompleto, String nomeMae,
			String endereco, int telefone, Curso curso, Tipo_nivel_aluno nivel, String email, Date anoIngresso, int periodoIngresso,
			String senhaAcesso) {
		super(cpf, nomeCompleto, rg, naturalidade, endereco, telefone, email, senhaAcesso);

		this.nomeMae = nomeMae;
		this.curso = curso;
		this.nivel = nivel;
		this.anoIngresso = anoIngresso;
		this.periodoIngresso = periodoIngresso;
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
	public void gerarMatrcula() {
		
		this.matricula = "";
		this.matricula+= this.nivel+this.curso.getSigla()+this.anoIngresso.toString().substring(2, 4)+this.periodoIngresso;
		
	}
	
}

