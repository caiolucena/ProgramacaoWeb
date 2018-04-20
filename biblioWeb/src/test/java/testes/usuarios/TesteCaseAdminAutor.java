package testes.usuarios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.AutorDAO;
import br.uepb.interfaces.IFDependencia;
import br.uepb.model.Autor;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdminAutor {
	private Administrador adm;
	private Autor autor;
	private AutorDAO autorDAO;
	
	@Before
	public void setup(){
		adm = new Administrador();
		autor = new Autor();
		autorDAO = new AutorDAO();
	}
	
	@Test
	public void create(){
		autor = new Autor("Autor1");
		assertTrue(adm.create(autorDAO,autor));
		assertTrue(adm.remove(autorDAO,autor));
	}
	
	@Test
	public void update(){
		autor = new Autor("Autor1");
		assertTrue(adm.create(autorDAO,autor));
		autor = (Autor)adm.search(autorDAO,"Autor1").get(0);
		autor.setNome("NovoAutor");
		assertTrue(adm.update(autorDAO,autor));
		assertTrue(adm.remove(autorDAO,autor));
	}
	
	@Test
	public void remove(){
		autor = new Autor("Autor1");
		assertTrue(adm.create(autorDAO,autor));
		autor = new Autor("Autor2");
		assertTrue(adm.create(autorDAO,autor));
		autor = new Autor("Autor3");
		assertTrue(adm.create(autorDAO,autor));
		autor.setNome("Autor");
		for(IFDependencia dep:adm.search(autorDAO,"Autor")){
			Autor a = (Autor)dep;
			assertTrue(adm.remove(autorDAO,a));
		}
	}
	
	@Test
	public void search(){
		autor = new Autor("Autor1");
		assertTrue(adm.create(autorDAO,autor));
		autor = (Autor)adm.search(autorDAO,"Autor1").get(0);
		assertEquals("Autor1",autor.getNome());
		assertTrue(adm.remove(autorDAO,autor));
	}

	
}
