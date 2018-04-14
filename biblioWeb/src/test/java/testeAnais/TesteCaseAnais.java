package testeAnais;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.AutorDAO;
import br.uepb.dao.acervo.AnaisDAO;
import br.uepb.model.Autor;
import br.uepb.model.Cidade;
import br.uepb.model.acervo.Anais;
import br.uepb.model.enums.Tipo_anal;

public class TesteCaseAnais {
	private Anais anais;
	private AnaisDAO anaisDao;
	private Autor autor;
	private AutorDAO autorDao;
	private Cidade cidade;
	@Before
	public void setup() throws Exception {
		anais = new Anais();
		anaisDao = new AnaisDAO();
		autor = new Autor();
		autorDao = new AutorDAO();
		cidade = new Cidade(1294,2504009,"Campina Grande","PB");
		
	}
	
	@Test
	public void testeCreateAnais() {
		autor.setNome("Autor1");
		autorDao.createAutor(autor);
		autor = autorDao.searchAutor(autor).get(0);
		anais.setAnoPublicacao(new Date(System.currentTimeMillis()));
		anais.setAutor(autor);		
		anais.setLocal(cidade);
		anais.setNome_congresso("Congreso1");
		anais.setTipo(Tipo_anal.artigo);
		anais.setTitulo("Titulo1");
		assertTrue(anaisDao.createItemAcervo(anais));
	}
	
	@Test
	public void testeRemoveAnais() {
		anais.setTitulo("Titulo1");
		anais = anaisDao.searchItemAcervo(anais).get(0);
		assertTrue(anaisDao.removeItemAcervo(anais));
	}
	
}
