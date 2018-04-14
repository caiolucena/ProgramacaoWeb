package br.uepb.model.acervo;

import java.util.Date;

import br.uepb.model.enums.Tipo_midia;

public class Midias_Eletronicas implements Acervo{
	private int id;
	private String titulo;
	private Tipo_midia tipo;
	private Date data_gravacao;
	
	public Midias_Eletronicas(){	
	}
	public Midias_Eletronicas(String titulo, Tipo_midia tipo, Date data_gravacao) {
		this.titulo = titulo;
		this.tipo = tipo;
		this.data_gravacao = data_gravacao;
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

	public Tipo_midia getTipo() {
		return tipo;
	}

	public void setTipo(Tipo_midia tipo) {
		this.tipo = tipo;
	}

	public Date getData_gravacao() {
		return data_gravacao;
	}

	public void setData_gravacao(Date data_gravacao) {
		this.data_gravacao = data_gravacao;
	}	

}
