package br.uepb.model.acervo;

import java.util.Date;

import br.uepb.model.Autor;
import br.uepb.model.Cidade;
import br.uepb.model.Orientador;
import br.uepb.model.enums.Tipo_tcc;
/**
 * Essa classe é utilizada como modelo para um objeto do tipo Tcc.
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Tcc implementa a interface Acervo
 * @author EquipeACL
 */
public class Tcc implements IFAcervo{ 
	private int id;
	private String titulo;
	private Autor autor;
	private Orientador orientador;
	private Tipo_tcc tipo;
	private Date ano_defesa;
	private Cidade cidade;
	
	/**
	 * Método construtor da classe Tcc
	 * Construtor vazio (utilizado para criar um objeto do tipo Tcc sem parâmetros definidos)
	 */
	public Tcc() {		
	}
	/**
	 * Método construtor da classe Tcc (utilizado para criar um objeto do tipo Tcc com parâmetros definidos)
	 * @param id, id do tcc
	 * @param titulo, titulo do tcc
	 * @param autor, objeto do tipo Autor referente ao tcc
	 * @param orientador, objeto do tipo Orientador referente ao tcc
	 * @param tipo, Enum que define o tipo do tcc
	 * @param ano_defesa, ano da defesa do tcc
	 * @param cidade, cidade da defesa do tcc
	 */
	public Tcc(int id, String titulo, Autor autor, Orientador orientador, Tipo_tcc tipo, Date ano_defesa, Cidade cidade) {
		setId(id);
		setTitulo(titulo);
		setAutor(autor);
		setOrientador(orientador);
		setTipo(tipo);
		setAno_defesa(ano_defesa);
		setCidade(cidade);
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
	public boolean validaItem() {
		return true;
	}
	
}
