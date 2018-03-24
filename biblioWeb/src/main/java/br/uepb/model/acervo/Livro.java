package br.uepb.model.acervo;

import java.util.ArrayList;
import java.util.Date;

public class Livro {
	private long isbn;
	private String titulo;
	private ArrayList<String> autores;
	private String editora;
	private int ano_publicacao;
	private String edicao;
	private int numero_paginas;
	private String area;
	private String tema;
	
	public Livro(long isbn, String titulo, ArrayList<String> autores, String editora, int ano_publicacao, String edicao, int numero_paginas, String area, String tema) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autores = autores;
		this.editora = editora;
		this.ano_publicacao = ano_publicacao;
		this.edicao = edicao;
		this.numero_paginas = numero_paginas;
		this.area = area;
		this.tema = tema;
	}
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public ArrayList<String> getAutores() {
		return autores;
	}
	public void setAutores(ArrayList<String> autores) {
		this.autores = autores;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public int getAno_publicacao() {
		return ano_publicacao;
	}
	public void setAno_publicacao(int ano_publicacao) {
		this.ano_publicacao = ano_publicacao;
	}
	public String getEdicao() {
		return edicao;
	}
	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}
	public int getNumero_paginas() {
		return numero_paginas;
	}
	public void setNumero_paginas(int numero_paginas) {
		this.numero_paginas = numero_paginas;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}	
}
