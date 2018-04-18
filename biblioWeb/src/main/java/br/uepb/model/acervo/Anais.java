package br.uepb.model.acervo;

import java.util.Date;

import br.uepb.model.Autor;
import br.uepb.model.Cidade;
import br.uepb.model.enums.Tipo_anal;

/**
 * Essa classe é utilizada como modelo para um objeto do tipo Anais.
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Anais implementa a interface Acervo
 * @author EquipeACL
 */
public class Anais implements Acervo{
	private int id;
	private Tipo_anal tipo;
	private String titulo;
	private Autor autor;
	private String nome_congresso;
	private Date anoPublicacao;
	private Cidade local;
	
	/**
	 * Método construtor da classe Anais
	 * Construtor vazio (utilizado para criar um objeto do tipo Anais sem parâmetros definidos)
	 */
	public Anais() {
		
	}
	
	/**
	 * Método construtor da classe Anais (utilizado para criar um objeto do tipo Anais com parâmetros definidos)
	 * @param id, id do anal
	 * @param tipo, Enum do tipo do anal
	 * @param titulo, titulo do anal
	 * @param autor, objeto do tipo Autor referente ao anal
	 * @param nome_congresso, nome do congresso onde o anal foi apresentado
	 * @param anoPublicacao, ano de publicacao do anal
	 * @param local, objeto do tipo Cidade referente ao anal
	 */
	public Anais(int id,Tipo_anal tipo, String titulo, Autor autor, String nome_congresso, Date anoPublicacao, Cidade local){
		setId(id);
		setTipo(tipo);
		setTitulo(titulo);
		setAutor(autor);
		setNome_congresso(nome_congresso);
		setAnoPublicacao(anoPublicacao);
		setLocal(local);
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
