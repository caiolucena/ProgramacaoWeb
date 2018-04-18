package br.uepb.model.acervo;

import java.util.ArrayList;
import java.util.Date;

import br.uepb.model.AreaConhecimento;
import br.uepb.model.Autor;
import br.uepb.model.Editora;

/**
 * Essa classe é utilizada como modelo para um objeto do tipo Livro.
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Livro estende a classe ItemAcervo
 * @author EquipeACL
 */
public class Livro extends ItemAcervo{
	private long isbn;

	private ArrayList<Autor> autores;
	private Editora editora;
	
	private int numero_paginas;
	private AreaConhecimento area;
	
	/**
	 * Método construtor da classe Livro
	 * Construtor vazio (utilizado para criar um objeto do tipo Livro sem parâmetros definidos)
	 */
	public Livro() {		
	}
	
	/**
	 * Método construtor da classe Livro (utilizado para criar um objeto do tipo Livro com parâmetros definidos)
	 * @param isbn, isbn do livro
	 * @param titulo, titulo do livro
	 * @param autores, array de objetos do tipo Autor com os autores do livro
	 * @param editora, objeto do tipo Editora referente ao livro
	 * @param ano_publicacao, ano de publicacao do livro
	 * @param edicao, edicao do livro
	 * @param numero_paginas, numero de paginas do livro
	 * @param area, objeto do tipo AreaConhecimento referente ao livro
	 */
	public Livro(long isbn, String titulo, ArrayList<Autor> autores, Editora editora, Date ano_publicacao, int edicao, int numero_paginas, AreaConhecimento area) {
		setIsbn(isbn);
		setTitulo(titulo);
		setAutores(autores);
		setEditora(editora);
		setData(ano_publicacao);
		setEdicao(edicao);
		setNumero_paginas(numero_paginas);
		setArea(area);
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
