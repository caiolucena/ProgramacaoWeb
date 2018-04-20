package testes.usuarios;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.EditoraDAO;
import br.uepb.dao.acervo.RevistaDAO;
import br.uepb.interfaces.IFAcervo;
import br.uepb.model.Editora;
import br.uepb.model.acervo.Revista;
import br.uepb.model.enums.Tipo_midia;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdminRevista {
	
	private Administrador adm;
	private Revista revista;
	Editora editora;
	EditoraDAO editoraDao;
	
	@Before
	public void setUp() throws Exception {
		adm = new Administrador();
		revista = new Revista();
		editora = new Editora();
		editora.setNome("Editora1");
		assertTrue(editoraDao.createItemDependencia(editora));
		editora = editoraDao.searchItemDependencia("Editora1").get(0);
				
	}

	@Test
	public void createMidiaEletronica() {
		revista = new Revista("revista do meu tcc do meu tcc", editora, new Date(System.currentTimeMillis()),2,200);
		assertTrue(adm.createItemAcervo(new RevistaDAO(), revista));
		
		revista = (Revista) adm.searchItemAcervo(new RevistaDAO(),revista.getTitulo()).get(0);
		assertTrue(adm.removeItemAcervo(new RevistaDAO(), revista));
		
		assertFalse(adm.createItemAcervo(new RevistaDAO(), new Revista()));
	}
	
	@Test
	public void removeMidiaEletronica() {
		revista = new Revista("revista do meu tcc do meu tcc", editora, new Date(System.currentTimeMillis()),2,200);
		assertTrue(adm.createItemAcervo(new RevistaDAO(), revista));
		
		revista = new Revista("revista do meu tcc do meu tcc", editora, new Date(System.currentTimeMillis()),2,200);
		assertTrue(adm.createItemAcervo(new RevistaDAO(), revista));
		
		revista = new Revista("revista do meu tcc do meu tcc", editora, new Date(System.currentTimeMillis()),2,200);
		assertTrue(adm.createItemAcervo(new RevistaDAO(), revista));
		
		for(IFAcervo dep:adm.searchItemAcervo(new RevistaDAO(),"revista do meu tcc")){
			Revista e = (Revista) dep;
			assertTrue(adm.removeItemAcervo(new RevistaDAO(),e));
		}

	}
	
	@Test
	public void updateMidiaEletronica() {
		revista = new Revista("revista do meu tcc do meu tcc", editora, new Date(System.currentTimeMillis()),2,200);
		assertTrue(adm.createItemAcervo(new RevistaDAO(), revista));
		
		revista = (Revista) adm.searchItemAcervo(new RevistaDAO(),revista.getTitulo()).get(0);
		revista.setTitulo("revista do meu mestrado");
		
		assertTrue(adm.updateItemAcervo(new RevistaDAO(), revista));
		assertTrue(adm.removeItemAcervo(new RevistaDAO(), revista));
		
		
	}
	
}
