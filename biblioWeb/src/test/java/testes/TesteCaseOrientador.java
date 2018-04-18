package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.OrientadorDAO;
import br.uepb.model.Orientador;


public class TesteCaseOrientador {
	private Orientador orientador;
	private OrientadorDAO orientadorDao;
	
	@Before
	public void setup() throws Exception{
		orientador = new Orientador();
		orientadorDao = new OrientadorDAO();
	}
	
	@Test
	public void testeCreateOrientador(){
		orientador.setNome("Orientador1");
		orientador.setFormacao("Graduado");
		assertTrue(orientadorDao.createOrientador(orientador));
		
		orientador = new Orientador();
		assertFalse(orientadorDao.createOrientador(orientador));
		
		orientador.setNome("Orientador1");
		ArrayList<Orientador> lista = new ArrayList<Orientador>();
		lista = orientadorDao.searchOrientador(orientador);
		for(Orientador o:lista){
			assertTrue(orientadorDao.removeOrientador(o));
		}
	}
	
	@Test
	public void testeRemoveOrientador(){
		orientador.setNome("Orientador1");
		orientador.setFormacao("Graducação");
		assertTrue(orientadorDao.createOrientador(orientador));
		orientador.setNome("Orientador2");
		orientador.setFormacao("PosGraducação");
		assertTrue(orientadorDao.createOrientador(orientador));
		orientador.setNome("Orientador3");
		orientador.setFormacao("Graducação");
		assertTrue(orientadorDao.createOrientador(orientador));
		
		orientador.setNome("Orientador");
		ArrayList<Orientador> lista = new ArrayList<Orientador>();
		lista = orientadorDao.searchOrientador(orientador);
		for(Orientador o:lista){
			assertTrue(orientadorDao.removeOrientador(o));
		}
	}
	
	@Test
	public void testeUpdateOrientador(){
		orientador.setNome("Orientador1");
		orientador.setFormacao("Graducação");
		assertTrue(orientadorDao.createOrientador(orientador));
		ArrayList<Orientador> lista = new ArrayList<Orientador>();
		lista = orientadorDao.searchOrientador(orientador);
		orientador = lista.get(0);
		orientador.setNome("OrientadorNovo");
		orientador.setFormacao("PosGraducação");
		assertTrue(orientadorDao.updateOrientador(orientador));
		
		orientador.setNome("Orientador");
		lista = new ArrayList<Orientador>();
		lista = orientadorDao.searchOrientador(orientador);
		for(Orientador o:lista){
			assertTrue(orientadorDao.removeOrientador(o));
		}
	}

}
