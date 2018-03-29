package testeRevista;


import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.EditoraDAO;
import br.uepb.dao.acervo.RevistaDAO;
import br.uepb.model.Editora;
import br.uepb.model.acervo.Revista;

public class TesteCaseRevista {

	
	Revista rev;
	RevistaDAO revDao;
	Editora ed;
	EditoraDAO edDao;
	ArrayList <Revista> lista;
	
	@Before
	public void setup() throws Exception {
		rev = new Revista();
		revDao = new RevistaDAO();
		ed = new Editora();
		edDao = new EditoraDAO();
		lista = new ArrayList<Revista>();
	}
	
	@Test
	public void testCreate() {
		ed.setNome("Editora Tec");
		
		edDao.createEditora(ed);
		
		ed = edDao.searchEditora(ed.getNome());
		
		rev.setTitulo("MasterClass");
		rev.setNum_pag(355);
		rev.setEditora(ed);
		Date data = new Date(System.currentTimeMillis());
		rev.setData(data);
		
		rev.setEdicao(5);
		
		assertTrue(revDao.createItemAcervo(rev));
		
	}
	@Test
	public void testDelete() {
		assertTrue(revDao.removeItemAcervo(rev));
		assertTrue(edDao.removeEditora(ed));
	}
	
	@Test
	public void testBusca() {
		
		lista = revDao.searchItemAcervo(rev);
		
		for(Revista a:lista) {
			revDao.removeItemAcervo(a);
		}
	}
	
}
