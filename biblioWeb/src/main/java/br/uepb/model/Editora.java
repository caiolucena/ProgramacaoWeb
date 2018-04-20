package br.uepb.model;

/**
 * Essa classe é utilizada como modelo para um objeto do tipo Editora.
 * A classe contém os respectivos getters and setters de seus atributos.
 * @author EquipeACL
 */
public class Editora {
	private int id;
	private String nome;
	
	/**
	 * Método construtor da classe Editora
	 * Construtor vazio (utilizado para criar um objeto do tipo Editora sem parâmetros definidos)
	 */
	public Editora() {

	}
	
	public Editora(String nome){
		setNome(nome);
	}
	
	/**
	 * Método construtor da classe Editora (utilizado para criar um objeto do tipo Editora com parâmetros definidos)
	 * @param id, id da editora
	 * @param nome, nome da editora
	 */
	public Editora(int id, String nome) {
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
