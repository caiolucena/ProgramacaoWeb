package br.uepb.model;

/**
 * Essa classe é utilizada como modelo para um objeto do tipo Cidade.
 * A classe contém os respectivos getters and setters de seus atributos.
 * @author EquipeACL
 */
public class Cidade {
	private int id;
	private int codigo;
	private String nome;
	private String uf;
	
	/**
	 * Método construtor da classe Cidade
	 * Construtor vazio (utilizado para criar um objeto do tipo Cidade sem parâmetros definidos)
	 */
	public Cidade() {
		
	}
	
	/**
	 * Método construtor da classe Cidade (utilizado para criar um objeto do tipo Cidade com parâmetros definidos)
	 * @param id, id da cidade
	 * @param codigo, codigo da cidade
	 * @param nome, nome da cidade
	 * @param uf, união federativa da cidade
	 */
	public Cidade(int id, int codigo, String nome, String uf) {
		setId(id);
		setCodigo(codigo);
		setNome(nome);
		setUf(uf);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
}
