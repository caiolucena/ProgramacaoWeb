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
		
		assertFalse(areaDAO.createAreaConhecimento(area));
		area.setNome("Saude");
		assertTrue(areaDAO.createAreaConhecimento(area));
		ArrayList<AreaConhecimento> areaList = new ArrayList<AreaConhecimento>();
		areaList = areaDAO.searchAreaConhecimento(area);
		for(AreaConhecimento a:areaList) {
			areaDAO.removeAreaConhecimento(a);
		} 
	}
	
	@Test
	public void testUpdateSearchRemove() {
		AreaConhecimento area2 = new AreaConhecimento();
		area2.setNome("Humanas");
		areaDAO.createAreaConhecimento(area2);
		ArrayList<AreaConhecimento> listArea = new ArrayList<AreaConhecimento>();
		listArea = areaDAO.searchAreaConhecimento(area2);
		for(AreaConhecimento ar: listArea) {
			ar.setNome("Saude");
			areaDAO.updateAreaConhecimento(ar);
			areaDAO.removeAreaConhecimento(ar);
		}
		
	}
	
}
