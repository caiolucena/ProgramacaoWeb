package br.uepb.model.acervo;

import java.util.Date;

/**
 * Essa classe é utilizada como modelo para um objeto do tipo ItemAcervo.
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe ItemAcervo é a super classe dos itens de acervo (jornal, livro, midias_eletrônicas)
 * @author EquipeACL
 */
public class ItemAcervo {

	protected Date data;
	protected int edicao;
	protected String titulo;
	
	
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}
