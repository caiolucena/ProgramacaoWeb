package br.uepb.model.acervo;

import java.sql.Date;

import br.uepb.model.Editora;
/**
 * Essa classe é utilizada como modelo para um objeto do tipo Revista.
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Revista implementa a interface Acervo
 * @author EquipeACL
 */
public class Revista implements Acervo{
	private int id;
	private String titulo;
	private Editora editora;
	private Date data;
	private int edicao;
	private int num_pag;
	
	/**
	 * Método construtor da classe Revista
	 * Construtor vazio (utilizado para criar um objeto do tipo Revista sem parâmetros definidos)
	 */
	public Revista() {		
	}
	/**
	 * Método construtor da classe Revista (utilizado para criar um objeto do tipo Revista com parâmetros definidos)
	 * @param titulo, titulo da revista
	 * @param editora, objeto do tipo Editora referente a revista
	 * @param dataDePublicacao, data de publicacao da revista
	 * @param edicao, edicao da revista
	 * @param numeroDePaginas, numero de paginas da revista
	 */
	public Revista(String titulo, Editora editora, Date dataDePublicacao, int edicao, int numeroDePaginas) {
		setTitulo(titulo);
		setEditora(editora);
		setData(dataDePublicacao);
		setEdicao(edicao);
		setNum_pag(numeroDePaginas);
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

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date dataDePublicacao) {
		this.data = dataDePublicacao;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public int getNum_pag() {
		return num_pag;
	}

	public void setNum_pag(int numeroDePaginas) {
		this.num_pag = numeroDePaginas;
	}
	
		
}
