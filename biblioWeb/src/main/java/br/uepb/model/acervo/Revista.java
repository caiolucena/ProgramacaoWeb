package br.uepb.model.acervo;

import java.sql.Date;

public class Revista {
	private String titulo;
	private String editora;
	private Date dataDePublicacao;
	private String edicao;
	private int numeroDePaginas;
	
	public Revista() {
		
	}
	
	public Revista(String titulo, String editora, Date dataDePublicacao, String edicao, int numeroDePaginas) {
		this.titulo = titulo ;
		this.editora = editora;
		this.dataDePublicacao = dataDePublicacao;
		this.edicao = edicao;
		this.numeroDePaginas = numeroDePaginas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Date getDataDePublicacao() {
		return dataDePublicacao;
	}

	public void setDataDePublicacao(Date dataDePublicacao) {
		this.dataDePublicacao = dataDePublicacao;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public int getNumeroDePaginas() {
		return numeroDePaginas;
	}

	public void setNumeroDePaginas(int numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}
	
		
}
