package br.uepb.model;

public class AreaConhecimento {
	
	private int id;
	private String nome;
	private Tema tema;
	
	public AreaConhecimento(int id, String nome, Tema tema) {
		this.id = id;
		this.nome = nome;
		this.tema = tema;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	

}
