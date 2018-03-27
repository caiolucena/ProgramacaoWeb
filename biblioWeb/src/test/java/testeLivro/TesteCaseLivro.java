package testeLivro;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.acervo.LivroDAO;
import br.uepb.model.acervo.Livro;

public class TesteCaseLivro {
	private Livro livro;
	private LivroDAO livroDao;
	
	@Before
	public void setup() throws Exception{
		livro = new Livro();
		livroDao = new LivroDAO();
	}
	
	@Test
	public void testeCreateLivro(){
		
	}

}
