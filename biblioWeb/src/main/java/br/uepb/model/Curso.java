package br.uepb.model;

import br.uepb.model.enums.Tipo_Curso;

public class Curso {
	private int id;
	private String nome;
	private AreaConhecimento area;
	private Tipo_Curso tipo;
	
	public Curso(){
		
	}
	public Curso(String nome, AreaConhecimento area, Tipo_Curso tipo) {
		this.nome = nome;
		this.area = area;
		this.tipo = tipo;
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
	public Tipo_Curso getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_Curso tipo) {
		this.tipo = tipo;
	}

}
