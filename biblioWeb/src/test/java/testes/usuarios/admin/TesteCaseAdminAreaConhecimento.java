package testes.usuarios.admin;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.AreaConhecimentoDAO;
import br.uepb.interfaces.IFDependencia;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdminAreaConhecimento {
	private AreaConhecimento areaConhecimento;
	private Administrador admin;
	private AreaConhecimentoDAO areaDAO;
	
	@Before
	public void setup(){
		admin = new Administrador();
		areaConhecimento = new AreaConhecimento();
		areaDAO = new AreaConhecimentoDAO();
	}
	
	@Test
	public void testeCreateAreaConhecimento(){
		areaConhecimento.setNome("Area1");
		assertTrue(admin.create(areaDAO,areaConhecimento));
		areaConhecimento = (AreaConhecimento)admin.search(areaDAO,"Area1").get(0);
		assertTrue(admin.remove(areaDAO,areaConhecimento));
	}
	
	@Test
	public void testeRemoveAreaConhecimento(){
		areaConhecimento.setNome("Area1");
		assertTrue(admin.create(areaDAO,areaConhecimento));
		areaConhecimento.setNome("Area2");
		assertTrue(admin.create(areaDAO,areaConhecimento));
		areaConhecimento.setNome("Area3");
		assertTrue(admin.create(areaDAO,areaConhecimento));
		areaConhecimento.setNome("Area");
		for( IFDependencia dep: admin.search(areaDAO,"Area")){
			AreaConhecimento area = (AreaConhecimento)dep;
			assertTrue(admin.remove(areaDAO,area));
		}		
	}
	
	@Test
	public void testeUpdateAreaConhecimento(){
		areaConhecimento.setNome("Area1");
		assertTrue(admin.create(areaDAO,areaConhecimento));
		areaConhecimento = (AreaConhecimento)admin.search(areaDAO,"Area1").get(0);
		areaConhecimento.setNome("NovaArea");
		assertTrue(admin.update(areaDAO,areaConhecimento));
		assertTrue(admin.remove(areaDAO,areaConhecimento));
	}
	
	@Test
	public void testeSearchAreaConhecimento(){
		areaConhecimento.setNome("Area1");
		assertTrue(admin.create(areaDAO,areaConhecimento));
		areaConhecimento =(AreaConhecimento) admin.search(areaDAO,"Area1").get(0);
		assertEquals("Area1",areaConhecimento.getNome());
		assertTrue(admin.remove(areaDAO,areaConhecimento));
	}
}
