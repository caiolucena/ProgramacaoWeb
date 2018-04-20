package testes.usuarios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.EditoraDAO;
import br.uepb.interfaces.IFDependencia;
import br.uepb.model.Editora;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdminEditora {
	private Administrador adm;
	private Editora editora;
	private EditoraDAO editoraDAO;
	
	@Before
	public void setup(){
		adm = new Administrador();
		editora = new Editora();
		editoraDAO = new EditoraDAO();
	}
	
	@Test
	public void createEditora(){
		editora = new Editora("Editora");
		assertTrue(adm.create(editoraDAO, editora));
		editora = (Editora) adm.search(editoraDAO, "Editora").get(0);
		assertTrue(adm.remove(editoraDAO,editora));
	}
	
	@Test
	public void removeEditora(){
		editora = new Editora("Editora1");
		assertTrue(adm.create(editoraDAO,editora));
		
		editora = new Editora("Editora2");
		assertTrue(adm.create(editoraDAO,editora));
		
		editora = new Editora("Editora3");
		assertTrue(adm.create(editoraDAO,editora));
		
		for(IFDependencia dep:adm.search(editoraDAO,"Editora")){
			Editora e = (Editora) dep;
			assertTrue(adm.remove(editoraDAO,e));
		}
	}
	
	@Test
	public void updateEditora(){
		editora = new Editora("Editora");
		assertTrue(adm.create(editoraDAO,editora));
		editora = (Editora) adm.search(editoraDAO,"Editora").get(0);
		editora.setNome("NovaEditora");
		assertTrue(adm.update(editoraDAO,editora));
		assertTrue(adm.remove(editoraDAO,editora));
	}
	
	@Test
	public void searchEditora(){
		editora = new Editora("Editora");
		assertTrue(adm.create(editoraDAO,editora));
		editora =(Editora)adm.search(editoraDAO,"Editora").get(0);
		assertEquals("Editora",editora.getNome());
		assertTrue(adm.remove(editoraDAO,editora));
	}

}
