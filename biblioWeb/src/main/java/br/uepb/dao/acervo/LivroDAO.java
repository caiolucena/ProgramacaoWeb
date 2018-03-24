package br.uepb.dao.acervo;

import java.sql.Connection;

import br.uepb.dao.Conexao;
import br.uepb.dao.Item_Acervo;
import br.uepb.model.acervo.Livro;

public class LivroDAO implements Item_Acervo<Livro>{
	private Connection con;
	public LivroDAO() throws Exception {
		con = Conexao.iniciarConexao();
	}
	public boolean createItemAcervo(Livro objeto) {
		String sql = "insert into livro(isbn,titulo,editora_id,ano,edicao,num_pag,area_conhecimento_id) values ()"
		return false;
	}

	public boolean removeItemAcervo(Livro objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateItemAcervo(Livro objeto) {
		// TODO Auto-generated method stub
		return false;
	}

	public Livro searchItemAcervo(Livro objeto) {
		// TODO Auto-generated method stub
		return null;
	}

		

}
