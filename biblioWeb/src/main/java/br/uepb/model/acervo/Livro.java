package br.uepb.model.acervo;

import java.util.ArrayList;
import java.util.Date;

import br.uepb.model.AreaConhecimento;
import br.uepb.model.Autor;
import br.uepb.model.Editora;

public class Livro extends ItemAcervo{
	private long isbn;

	private ArrayList<Autor> autores;
	private Editora editora;
	
	private int numero_paginas;
	private AreaConhecimento area;
		
	public Livro() {		
	}
	public Livro(long isbn, String titulo, ArrayList<Autor> autores, Editora editora, Date ano_publicacao, int edicao, int numero_paginas, AreaConhecimento area) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.autores = autores;
		this.editora = editora;
		this.data = ano_publicacao;
		this.edicao = edicao;
		this.numero_paginas = numero_paginas;
		this.area = area;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public ArrayList<Autor> getAutores() {
		return autores;
	}

	public void setAutores(ArrayList<Autor> autores) {
		this.autores = autores;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}


	public int getNumero_paginas() {
		return numero_paginas;
	}

	public void setNumero_paginas(int numero_paginas) {
		this.numero_paginas = numero_paginas;
	}

	public AreaConhecimento getArea() {
		return area;
	}

	public void setArea(AreaConhecimento area) {
		this.area = area;
	}
	
}
