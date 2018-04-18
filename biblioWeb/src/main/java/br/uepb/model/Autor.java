package br.uepb.model;

/**
 * Essa classe é utilizada como modelo para um objeto do tipo Autor.
 * A classe contém os respectivos getters and setters de seus atributos.
 * @author EquipeACL
 */
public class Autor {
	private int id;
	private String nome;
	
	/**
	 * Método construtor da classe Autor
	 * Construtor vazio (utilizado para criar um objeto do tipo Autor sem parâmetros definidos)
	 */
	public Autor(){
		
	}
	
	/**
	 * Método construtor da classe Autor (utilizado para criar um objeto do tipo Autor com parâmetros definidos)
	 * @param id, id do autor
	 * @param nome, nome do autor
	 */
	public Autor(int id, String nome) {
		setId(id);
		setNome(nome);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}
