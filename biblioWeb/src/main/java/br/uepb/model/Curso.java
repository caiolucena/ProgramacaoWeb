package br.uepb.model;

import br.uepb.model.enums.Tipo_curso;

public class Curso {
	private int id;
	private String nome;
	private String sigla;
	private AreaConhecimento area;
	private Tipo_curso tipo;
	
	public Curso(){
		
	}
	public Curso(String nome, String sigla, AreaConhecimento area, Tipo_curso tipo) {
		this.nome = nome;
		this.sigla = sigla;
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
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public AreaConhecimento getArea() {
		return area;
	}
	public void setArea(AreaConhecimento area) {
		this.area = area;
	}
	public Tipo_curso getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_curso tipo) {
		this.tipo = tipo;
	}

}
