package testeLivro;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.EditoraDAO;
import br.uepb.dao.acervo.LivroDAO;
import br.uepb.model.Editora;
import br.uepb.model.acervo.Livro;

public class TesteCaseLivro {
	private Livro livro;
	private LivroDAO livroDao;
	private Editora editora;
	private EditoraDAO editoraDao;
	
	@Before
	public void setup() throws Exception{
		livro = new Livro();
		livroDao = new LivroDAO();
		editora = new Editora();
		editoraDao = new EditoraDAO();
		editora.setNome("Editora1");
		editoraDao.createEditora(editora);//TODO Terminar teste
	}
	
	@Test
	public void testeCreateLivro(){
		livro.setIsbn(123456);
		livro.setTitulo("Livro Teste");
		
	}

}
