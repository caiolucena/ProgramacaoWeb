package br.uepb.model;

public class Tema {
	
	private int id;
	private String nome;
	private AreaConhecimento area;
	
	public Tema(){
		
	}
	public Tema(int id, String nome,AreaConhecimento area) {
		this.id = id;
		this.nome = nome;
		this.area = area;
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
	
	public AreaConhecimento getArea() {
		return area;
	}

	public void setArea(AreaConhecimento area) {
		this.area = area;
	}
}
