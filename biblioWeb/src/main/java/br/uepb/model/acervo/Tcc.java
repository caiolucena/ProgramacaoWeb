package br.uepb.model.acervo;

import java.util.ArrayList;

import br.uepb.model.enums.Tipo_Tcc;

public class Tcc {

	private String titulo;
	private ArrayList<String> autores;
	private String orientadores;
	private Tipo_Tcc tipo;
	private int ano_defesa;
	private String local;
	
	public Tcc(String titulo, ArrayList<String> autores, String orientadores, Tipo_Tcc tipo, int ano_defesa, String local) {
		this.titulo = titulo;
		this.autores = autores;
		this.orientadores = orientadores;
		this.tipo = tipo;
		this.ano_defesa = ano_defesa;
		this.local = local;
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
	public String getOrientadores() {
		return orientadores;
	}
	public void setOrientadores(String orientadores) {
		this.orientadores = orientadores;
	}
	public Tipo_Tcc getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_Tcc tipo) {
		this.tipo = tipo;
	}
	public int getAno_defesa() {
		return ano_defesa;
	}
	public void setAno_defesa(int ano_defesa) {
		this.ano_defesa = ano_defesa;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
}
