package testeRevista;


import static org.junit.Assert.*;

import java.sql.Date;

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
	
	@Before
	public void setup() throws Exception {
		rev = new Revista();
		revDao = new RevistaDAO();
		ed = new Editora();
		edDao = new EditoraDAO();
	}
	
	@Test
	public void testCreate() {
		ed.setNome("Editora Tec");
		
		edDao.createEditora(ed);
		
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
		
		
	}
	
}
