package testes.usuarios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uepb.model.Editora;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdminEditora {
	private Administrador adm;
	private Editora editora;
	
	@Before
	public void setup(){
		adm = new Administrador();
		editora = new Editora();
	}
	
	@Test
	public void createEditora(){
		editora = new Editora("Editora");
		assertTrue(adm.createEditora(editora));
		editora = adm.searchEditora(editora).get(0);
		assertTrue(adm.removeEditora(editora));
	}
	
	@Test
	public void removeEditora(){
		editora = new Editora("Editora1");
		assertTrue(adm.createEditora(editora));
		
		editora = new Editora("Editora2");
		assertTrue(adm.createEditora(editora));
		
		editora = new Editora("Editora3");
		assertTrue(adm.createEditora(editora));
		editora.setNome("Editora");
		for(Editora e:adm.searchEditora(editora)){
			assertTrue(adm.removeEditora(e));
		}
	}
	
	@Test
	public void updateEditora(){
		editora = new Editora("Editora");
		assertTrue(adm.createEditora(editora));
		editora = adm.searchEditora(editora).get(0);
		editora.setNome("NovaEditora");
		assertTrue(adm.updateEditora(editora));
		assertTrue(adm.removeEditora(editora));
	}
	
	@Test
	public void searchEditora(){
		editora = new Editora("Editora");
		assertTrue(adm.createEditora(editora));
		editora = adm.searchEditora(editora).get(0);
		assertEquals("Editora",editora.getNome());
		assertTrue(adm.removeEditora(editora));
	}

}
