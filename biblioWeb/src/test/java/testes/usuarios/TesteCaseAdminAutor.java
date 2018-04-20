package testes.usuarios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uepb.model.Autor;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdminAutor {
	private Administrador adm;
	private Autor autor;
	
	@Before
	public void setup(){
		adm = new Administrador();
		autor = new Autor();
	}
	
	@Test
	public void createAutor(){
		autor = new Autor("Autor1");
		assertTrue(adm.createAutor(autor));
		assertTrue(adm.removeAutor(autor));
	}
	
	@Test
	public void updateAutor(){
		autor = new Autor("Autor1");
		assertTrue(adm.createAutor(autor));
		autor = adm.searchAutor(autor).get(0);
		autor.setNome("NovoAutor");
		assertTrue(adm.updateAutor(autor));
		assertTrue(adm.removeAutor(autor));
	}
	
	@Test
	public void removeAutor(){
		autor = new Autor("Autor1");
		assertTrue(adm.createAutor(autor));
		autor = new Autor("Autor2");
		assertTrue(adm.createAutor(autor));
		autor = new Autor("Autor3");
		assertTrue(adm.createAutor(autor));
		autor.setNome("Autor");
		for(Autor a:adm.searchAutor(autor)){
			assertTrue(adm.removeAutor(a));
		}
	}
	
	@Test
	public void searchAutor(){
		autor = new Autor("Autor1");
		assertTrue(adm.createAutor(autor));
		autor = adm.searchAutor(autor).get(0);
		assertEquals("Autor1",autor.getNome());
		assertTrue(adm.removeAutor(autor));
	}

	
}
