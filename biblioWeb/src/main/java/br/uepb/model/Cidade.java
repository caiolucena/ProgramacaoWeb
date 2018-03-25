package br.uepb.model;

public class Cidade {
	
	private int id;
	private int codigo;
	private String nome;
	private String uf;
	
	public Cidade() {
		
	}
	public Cidade(int id, int codigo, String nome, String uf) {
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.uf = uf;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
}
