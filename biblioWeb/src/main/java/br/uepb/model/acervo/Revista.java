package br.uepb.model.acervo;

import java.sql.Date;

import br.uepb.model.Editora;

public class Revista {
	private int id;
	
	private String titulo;
	private Editora editora;
	private Date data;
	private int edicao;
	private int num_pag;
	
	public Revista() {		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Revista(String titulo, Editora editora, Date dataDePublicacao, int edicao, int numeroDePaginas) {
		this.titulo = titulo ;
		this.editora = editora;
		this.data = dataDePublicacao;
		this.edicao = edicao;
		this.num_pag = numeroDePaginas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date dataDePublicacao) {
		this.data = dataDePublicacao;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public int getNum_pag() {
		return num_pag;
	}

	public void setNum_pag(int numeroDePaginas) {
		this.num_pag = numeroDePaginas;
	}
	
		
}
