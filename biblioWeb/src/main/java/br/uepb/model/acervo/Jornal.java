package br.uepb.model.acervo;

import java.util.Date;

public class Jornal {
	
	private String titulo;
	private Date data;
	private String edicao;
	public Jornal() {
		
	}
	public Jornal(String titulo, Date data, String edicao) {
		this.titulo = titulo;
		this.data = data;
		this.edicao = edicao;
				
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

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
	
}
