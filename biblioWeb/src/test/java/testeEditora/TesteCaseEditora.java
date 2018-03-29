package testeEditora;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.EditoraDAO;
import br.uepb.model.Editora;

public class TesteCaseEditora {

	private Editora ed;
	private EditoraDAO edDao;
	private ArrayList <Editora> listaEd;
	@Before
	public void setup() throws Exception {
		ed = new Editora();
		edDao = new EditoraDAO();
		listaEd = new ArrayList<Editora>();
		
	}
	
	@Test
	public void testCreateEditora() {
		ed.setNome("Abril");
		assertTrue(edDao.createEditora(ed));
		assertFalse(edDao.createEditora(new Editora()));
	}

	@Test
	public void testBuscaDeleteEditora() {
		ed.setNome("Abril");
		listaEd = edDao.searchEditora(ed);
		System.out.println(listaEd.size());
		for(Editora a:listaEd) {
			assertTrue(edDao.removeEditora(a));
		}
	}
}
