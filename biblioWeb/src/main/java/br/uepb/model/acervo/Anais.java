package br.uepb.model.acervo;

import java.util.ArrayList;
import java.util.Date;

import br.uepb.model.enums.Tipo_Anal;
public class Anais {

	private Tipo_Anal tipo;
	private String titulo;
	private ArrayList<String> autores;
	private String nome_congresso;
	private Date anoPublicacao;
	private String local;
	public Anais() {
		
	}
	public Anais(Tipo_Anal tipo, String titulo, ArrayList<String> autores, String nome_congresso, Date anoPublicacao, String local){
		this.tipo = tipo;
		this.titulo = titulo;
		this.autores = autores;
		this.nome_congresso = nome_congresso;
		this.anoPublicacao = anoPublicacao;
		this.local = local;
	}
	public Tipo_Anal getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_Anal tipo) {
		this.tipo = tipo;
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
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
}
