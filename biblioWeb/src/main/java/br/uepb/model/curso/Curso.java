package br.uepb.model.curso;

import br.uepb.model.enums.Tipo_Curso;

public class Curso {
	
	private String nome;
	private String area;
	private Tipo_Curso tipo;
	
	public Curso(String nome, String area, Tipo_Curso tipo) {
		this.nome = nome;
		this.area = area;
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Tipo_Curso getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_Curso tipo) {
		this.tipo = tipo;
	}

}
