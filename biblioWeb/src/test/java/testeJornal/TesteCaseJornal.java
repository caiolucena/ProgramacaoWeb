package testeJornal;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.acervo.JornalDAO;
import br.uepb.model.acervo.Jornal;

public class TesteCaseJornal {

	
	private Jornal jornal;
	private JornalDAO jornalDao;
	private ArrayList <Jornal> jornais;
	private ArrayList <Jornal> teste;
	@Before
	public void setup() throws Exception{
		jornal = new Jornal();
		jornalDao = new JornalDAO();
		jornais = new ArrayList <Jornal>();
		teste = new ArrayList <Jornal>();
	}
	
	@Test
	public void testeCreateJornal() {
		jornal.setTitulo("Jornal da Correio");
		jornal.setEdicao(2);
		Date data = new Date(System.currentTimeMillis());
		jornal.setData(data);
		assertTrue(jornalDao.createItemAcervo(jornal));
		jornal = jornalDao.searchItemAcervo(jornal).get(0);
		assertTrue(jornalDao.removeItemAcervo(jornal));
		
	}
	@Test
	public void testeBuscaJornal() {
		jornal.setTitulo("Jornal da Paraiba");
		jornal.setEdicao(5);
		Date data = new Date(System.currentTimeMillis());
		jornal.setData(data);
			
		assertTrue(jornalDao.createItemAcervo(jornal));
		
		Jornal novoJornal = jornalDao.searchItemAcervo(jornal).get(0);
		
		assertTrue(jornal.getTitulo().equals(novoJornal.getTitulo()));
		assertFalse(jornal.getId() == novoJornal.getId());
		
	
	}
	
	@Test
	public void testRemoveJornal() {
		jornal.setTitulo("Jornal da Paraiba");
		jornal.setEdicao(5);
		Date data = new Date(System.currentTimeMillis());
		jornal.setData(data);
		jornal.setId(1);
		
		jornal = jornalDao.searchItemAcervo(jornal).get(0);
		
		assertTrue(jornalDao.removeItemAcervo(jornal));
	}

}
