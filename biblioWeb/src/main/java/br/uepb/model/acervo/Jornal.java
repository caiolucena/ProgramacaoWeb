package br.uepb.model.acervo;

import java.util.Date;

public class Jornal extends ItemAcervo{
	
	private int id;
	
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

	
}
