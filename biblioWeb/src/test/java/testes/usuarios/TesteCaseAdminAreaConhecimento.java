package testes.usuarios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdminAreaConhecimento {
	private AreaConhecimento areaConhecimento;
	private Administrador admin;
	
	@Before
	public void setup(){
		admin = new Administrador();
		areaConhecimento = new AreaConhecimento();
	}
	
	@Test
	public void testeCreateAreaConhecimento(){
		areaConhecimento.setNome("Area1");
		assertTrue(admin.createAreaConhecimento(areaConhecimento));
		areaConhecimento = admin.searchAreaConhecimento(areaConhecimento).get(0);
		assertTrue(admin.removeAreaConhecimento(areaConhecimento));
	}
	
	@Test
	public void testeRemoveAreaConhecimento(){
		areaConhecimento.setNome("Area1");
		assertTrue(admin.createAreaConhecimento(areaConhecimento));
		areaConhecimento.setNome("Area2");
		assertTrue(admin.createAreaConhecimento(areaConhecimento));
		areaConhecimento.setNome("Area3");
		assertTrue(admin.createAreaConhecimento(areaConhecimento));
		areaConhecimento.setNome("Area");
		for(AreaConhecimento area : admin.searchAreaConhecimento(areaConhecimento)){
			assertTrue(admin.removeAreaConhecimento(area));
		}		
	}
	
	@Test
	public void testeUpdateAreaConhecimento(){
		areaConhecimento.setNome("Area1");
		assertTrue(admin.createAreaConhecimento(areaConhecimento));
		areaConhecimento = admin.searchAreaConhecimento(areaConhecimento).get(0);
		areaConhecimento.setNome("NovaArea");
		assertTrue(admin.updateAreaConhecimento(areaConhecimento));
		assertTrue(admin.removeAreaConhecimento(areaConhecimento));
	}
	
	@Test
	public void testeSearchAreaConhecimento(){
		areaConhecimento.setNome("Area1");
		assertTrue(admin.createAreaConhecimento(areaConhecimento));
		areaConhecimento = admin.searchAreaConhecimento(areaConhecimento).get(0);
		assertEquals("Area1",areaConhecimento.getNome());
		assertTrue(admin.removeAreaConhecimento(areaConhecimento));
	}
}
