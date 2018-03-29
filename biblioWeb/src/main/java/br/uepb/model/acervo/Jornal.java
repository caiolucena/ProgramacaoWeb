package br.uepb.model.acervo;

import java.util.Date;

public class Jornal {
	private int id;
	private String titulo;
	private Date data;
	private int edicao;
	
	public Jornal() {
		
	}
	public Jornal(String titulo, Date data, int edicao) {
		this.titulo = titulo;
		this.data = data;
		this.edicao = edicao;
				
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}
	
}
