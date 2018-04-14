package br.uepb.model.acervo;

import java.util.Date;

import br.uepb.model.Autor;
import br.uepb.model.Cidade;
import br.uepb.model.enums.Tipo_anal;
public class Anais implements Acervo{
	private int id;
	private Tipo_anal tipo;
	private String titulo;
	private Autor autor;
	private String nome_congresso;
	private Date anoPublicacao;
	private Cidade local;
	
	public Anais() {
		
	}
	public Anais(int id,Tipo_anal tipo, String titulo, Autor autor, String nome_congresso, Date anoPublicacao, Cidade local){
		this.id = id;
		this.tipo = tipo;
		this.titulo = titulo;
		this.autor = autor;
		this.nome_congresso = nome_congresso;
		this.anoPublicacao = anoPublicacao;
		this.local = local;
	}
	public Tipo_anal getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_anal tipo) {
		this.tipo = tipo;
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
	public String getNome_congresso() {
		return nome_congresso;
	}
	public void setNome_congresso(String nome_congresso) {
		this.nome_congresso = nome_congresso;
	}
	public Date getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(Date anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	public Cidade getLocal() {
		return local;
	}
	public void setLocal(Cidade local) {
		this.local = local;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
