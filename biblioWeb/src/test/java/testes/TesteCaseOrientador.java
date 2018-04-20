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
		assertTrue(orientadorDao.createItemDependencia(orientador));
		
		orientador = new Orientador();
		assertFalse(orientadorDao.createItemDependencia(orientador));
		
		orientador.setNome("Orientador1");
		ArrayList<Orientador> lista = new ArrayList<Orientador>();
		lista = orientadorDao.searchItemDependencia("Orientador");
		for(Orientador o:lista){
			assertTrue(orientadorDao.removeItemDependencia(o));
		}
	}
	
	@Test
	public void testeRemoveOrientador(){
		orientador.setNome("Orientador1");
		orientador.setFormacao("Graducação");
		assertTrue(orientadorDao.createItemDependencia(orientador));
		orientador.setNome("Orientador2");
		orientador.setFormacao("PosGraducação");
		assertTrue(orientadorDao.createItemDependencia(orientador));
		orientador.setNome("Orientador3");
		orientador.setFormacao("Graducação");
		assertTrue(orientadorDao.createItemDependencia(orientador));
		
		orientador.setNome("Orientador");
		ArrayList<Orientador> lista = new ArrayList<Orientador>();
		lista = orientadorDao.searchItemDependencia("Orientador");
		for(Orientador o:lista){
			assertTrue(orientadorDao.removeItemDependencia(o));
		}
	}
	
	@Test
	public void testeUpdateOrientador(){
		orientador.setNome("Orientador1");
		orientador.setFormacao("Graducação");
		assertTrue(orientadorDao.createItemDependencia(orientador));
		ArrayList<Orientador> lista = new ArrayList<Orientador>();
		lista = orientadorDao.searchItemDependencia("Orientador");
		orientador = lista.get(0);
		orientador.setNome("OrientadorNovo");
		orientador.setFormacao("PosGraducação");
		assertTrue(orientadorDao.updateItemDependencia(orientador));
		
		orientador.setNome("Orientador");
		lista = new ArrayList<Orientador>();
		lista = orientadorDao.searchItemDependencia("Orientador");
		for(Orientador o:lista){
			assertTrue(orientadorDao.removeItemDependencia(o));
		}
	}

}
