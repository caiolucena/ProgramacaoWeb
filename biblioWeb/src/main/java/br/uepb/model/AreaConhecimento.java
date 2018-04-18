package br.uepb.model;

/**
 * Essa classe é utilizada como modelo para um objeto do tipo AreaConhecimento.
 * A classe contém os respectivos getters and setters de seus atributos.
 * @author EquipeACL
 */
public class AreaConhecimento {
	private int id;
	private String nome;
	
	/**
	 * Método construtor da classe AreaConhecimento
	 * Construtor vazio (utilizado para criar um objeto do tipo AreaConhecimento sem parâmetros definidos)
	 */
	public AreaConhecimento() {
		
	}
	
	/**
	 * Método construtor da classe AreaConhecimento (utilizado para criar um objeto do tipo AreaConhecimento com parâmetros definidos)
	 * @param id, id da área do conhecimento
	 * @param nome, nome da área do conhecimento
	 */
	public AreaConhecimento(int id, String nome) {
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
