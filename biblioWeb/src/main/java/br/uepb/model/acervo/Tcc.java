package br.uepb.model.acervo;

import java.util.Date;

import br.uepb.model.Autor;
import br.uepb.model.Cidade;
import br.uepb.model.Orientador;
import br.uepb.model.enums.Tipo_tcc;

public class Tcc implements Acervo{ 
	private int id;
	private String titulo;
	private Autor autor;
	private Orientador orientador;
	private Tipo_tcc tipo;
	private Date ano_defesa;
	private Cidade cidade;
	
	public Tcc() {		
	}
	
	public Tcc(int id, String titulo, Autor autor, Orientador orientador, Tipo_tcc tipo, Date ano_defesa, Cidade cidade) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.orientador = orientador;
		this.tipo = tipo;
		this.ano_defesa = ano_defesa;
		this.cidade = cidade;
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
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Orientador getOrientador() {
		return orientador;
	}
	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}
	public Tipo_tcc getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_tcc tipo) {
		this.tipo = tipo;
	}
	public Date getAno_defesa() {
		return ano_defesa;
	}
	public void setAno_defesa(Date ano_defesa) {
		this.ano_defesa = ano_defesa;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
}
