package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.AutorDAO;
import br.uepb.model.Autor;
import br.uepb.model.acervo.Midias_Eletronicas;

public class TesteCaseAutor {
	private Autor autor;
	private AutorDAO autorDao;
	
	@Before
	public void setup() throws Exception{
		autor = new Autor();
		autorDao = new AutorDAO();
	}
	
	@Test
	public void testeCreateAutor(){
		autor.setNome("Autor1");
		assertTrue(autorDao.createItemDependencia(autor));
		
		autor = new Autor();
		assertFalse(autorDao.createItemDependencia(autor));
		
		autor.setNome("Autor1");
		ArrayList<Autor> lista = new ArrayList<Autor>();
		lista = autorDao.searchItemDependencia("Autor1");
		for(Autor a:lista){
			assertTrue(autorDao.removeItemDependencia(a));
		}
	}
	
	@Test
	public void testeRemoveAutor(){
		autor.setNome("Autor1");
		assertTrue(autorDao.createItemDependencia(autor));
		autor.setNome("Autor2");
		assertTrue(autorDao.createItemDependencia(autor));
		autor.setNome("Autor3");
		assertTrue(autorDao.createItemDependencia(autor));
		
		autor.setNome("Autor");
		ArrayList<Autor> lista = new ArrayList<Autor>();
		lista = autorDao.searchItemDependencia("Autor");
		for(Autor a:lista){
			assertTrue(autorDao.removeItemDependencia(a));
		}
	}
	
	@Test
	public void testeUpdateAutor(){
		autor.setNome("Autor1");
		assertTrue(autorDao.createItemDependencia(autor));
		ArrayList<Autor> lista = new ArrayList<Autor>();
		lista = autorDao.searchItemDependencia("Autor1");
		autor = lista.get(0);
		autor.setNome("AutorNovo");
		assertTrue(autorDao.updateItemDependencia(autor));
		
		autor.setNome("Autor");
		lista = new ArrayList<Autor>();
		lista = autorDao.searchItemDependencia("Autor");
		for(Autor a:lista){
			assertTrue(autorDao.removeItemDependencia(a));
		}
	}

}
