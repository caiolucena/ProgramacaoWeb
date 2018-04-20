package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.AreaConhecimentoDAO;
import br.uepb.dao.TemaDAO;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Tema;

public class TesteCaseAreaConhecimento {

	private Tema tema;
	private AreaConhecimento area;
	private AreaConhecimentoDAO areaDAO;
	private TemaDAO temaDAO;
	
	@Before
	public void setup() throws Exception {
		tema = new Tema();
		area = new AreaConhecimento();	
		temaDAO = new TemaDAO();
		areaDAO = new AreaConhecimentoDAO();
	}
	
	@Test
	public void testCreateSearchRemoveArea() {
		assertFalse(areaDAO.createItemDependencia(area));
		area.setNome("Saude");
		assertTrue(areaDAO.createItemDependencia(area));
		ArrayList<AreaConhecimento> areaList = new ArrayList<AreaConhecimento>();
		areaList = areaDAO.searchItemDependencia("Saude");
		for(AreaConhecimento a:areaList) {
			areaDAO.removeItemDependencia(a);
		} 
	}
	
	@Test
	public void testUpdateSearchRemove() {
		AreaConhecimento area2 = new AreaConhecimento();
		area2.setNome("Humanas");
		areaDAO.createItemDependencia(area2);
		ArrayList<AreaConhecimento> listArea = new ArrayList<AreaConhecimento>();
		listArea = areaDAO.searchItemDependencia("Humanas");
		for(AreaConhecimento ar: listArea) {
			ar.setNome("Saude");
			areaDAO.updateItemDependencia(ar);
			areaDAO.removeItemDependencia(ar);
		}
		
	}
	
}
