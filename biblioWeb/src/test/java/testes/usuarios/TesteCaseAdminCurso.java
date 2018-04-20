package testes.usuarios;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uepb.model.AreaConhecimento;
import br.uepb.model.Curso;
import br.uepb.model.enums.Tipo_curso;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdminCurso {
	private Administrador adm;
	private Curso curso;
	private AreaConhecimento area;
	
	@Before
	public void setup(){
		adm = new Administrador();
		curso = new Curso();
		area = new AreaConhecimento(1,"EXATAS");
	}
	
	@Test
	public void createCurso(){
		curso = new Curso("Curso", "C", area, Tipo_curso.GRADUACAO);
		assertTrue(adm.createCurso(curso));
		curso = adm.searchCurso(curso).get(0);
		assertTrue(adm.removeCurso(curso));
	}
	
	@Test
	public void removeCurso(){
		curso = new Curso("Curso1", "C1", area, Tipo_curso.GRADUACAO);
		assertTrue(adm.createCurso(curso));
		
		curso = new Curso("Curso2", "C2", area, Tipo_curso.POS_GRADUACAO);
		assertTrue(adm.createCurso(curso));
		
		curso = new Curso("Curso3", "C3", area, Tipo_curso.GRADUACAO);
		assertTrue(adm.createCurso(curso));
		
		curso.setNome("Curso");
		
		for(Curso c : adm.searchCurso(curso)){
			assertTrue(adm.removeCurso(c));
		}
	}
	
	@Test
	public void updateCurso(){
		curso = new Curso("Curso", "C", area, Tipo_curso.GRADUACAO);
		assertTrue(adm.createCurso(curso));
		curso = adm.searchCurso(curso).get(0);
		curso.setNome("NovoNome");
		assertTrue(adm.updateCurso(curso));
		assertTrue(adm.removeCurso(curso));
	}
	
	@Test
	public void searchCurso(){
		curso = new Curso("Curso", "C", area, Tipo_curso.GRADUACAO);
		assertTrue(adm.createCurso(curso));
		curso = adm.searchCurso(curso).get(0);
		assertEquals("Curso",curso.getNome());
		assertEquals("C",curso.getSigla());
		assertEquals(Tipo_curso.GRADUACAO,curso.getTipo());
		assertEquals("EXATAS",curso.getArea().getNome());
		assertEquals(1,curso.getArea().getId());
		assertTrue(adm.removeCurso(curso));
	}
}

