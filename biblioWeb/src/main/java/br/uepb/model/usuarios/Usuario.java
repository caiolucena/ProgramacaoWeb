package br.uepb.model.usuarios;

import java.util.ArrayList;

import br.uepb.dao.usuarios.AlunoDAO;

public class Usuario implements Interface_manterAluno {
	
	protected AlunoDAO alunoDAO;
	
	protected int cpf;
	protected String nomeCompleto;
	protected int rg;
	protected String naturalidade;
	protected String endereco;
	protected int telefone;
	protected String email;
	protected String senhaAcesso;
	
	public Usuario() {
		
	}
	public Usuario(int cpf, String nomeCompleto, int rg, String naturalidade, String endereco, int telefone,
			String email, String senhaAcesso) {
		this.cpf = cpf;
		this.nomeCompleto = nomeCompleto;
		this.rg = rg;
		this.naturalidade = naturalidade;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.senhaAcesso = senhaAcesso;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public int getRg() {
		return rg;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenhaAcesso() {
		return senhaAcesso;
	}
	public void setSenhaAcesso(String senhaAcesso) {
		this.senhaAcesso = senhaAcesso;
	}
	public boolean createAluno(Aluno aluno) {
		if(validaAluno(aluno)) {
			return alunoDAO.createUsuario(aluno);
		}
		return false;
	}
	public boolean updateAluno(Aluno aluno) {
		if(validaAluno(aluno)) {
			return alunoDAO.updateUsuario(aluno);
		}
		return false;
	}
	public ArrayList<Aluno> searchAluno(Aluno aluno) {
		if(validaAluno(aluno)) {
			return alunoDAO.searchUsuario(aluno);
		}
		return null;
	}
	
	public boolean validaAluno(Aluno a) {
		// TODO validar o aluno
		return true;
	}
	
}
