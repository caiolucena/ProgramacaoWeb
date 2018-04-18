package br.uepb.model.acervo;

import java.util.Date;

import br.uepb.model.enums.Tipo_midia;

/**
 * Essa classe é responsável por criar um objeto do tipo Midias_Eletronicas.
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Midias_Eletronicas implementa a interface Acervo
 * @author EquipeACL
 */
public class Midias_Eletronicas implements Acervo{
	private int id;
	private String titulo;
	private Tipo_midia tipo;
	private Date data_gravacao;
	
	/**
	 * Método construtor da classe Midias_Eletronicas
	 * Construtor vazio (utilizado para criar um objeto do tipo Midias_Eletronicas sem parâmetros definidos)
	 */
	public Midias_Eletronicas(){	
	}
	
	/**
	 * Método construtor da classe Midias_Eletronicas (utilizado para criar um objeto do tipo Midias_Eletronicas com parâmetros definidos)
	 * @param titulo, título da mídia eletrônica
	 * @param tipo, objeto Enum que define o tipo da mída eletrônica
	 * @param data_gravacao, data da gravação da mídia eletrônica
	 */
	public Midias_Eletronicas(String titulo, Tipo_midia tipo, Date data_gravacao) {
		setTitulo(titulo);
		setTipo(tipo);
		setData_gravacao(data_gravacao);
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

	public Tipo_midia getTipo() {
		return tipo;
	}

	public void setTipo(Tipo_midia tipo) {
		this.tipo = tipo;
	}

	public Date getData_gravacao() {
		return data_gravacao;
	}

	public void setData_gravacao(Date data_gravacao) {
		this.data_gravacao = data_gravacao;
	}	

}
