package testes.usuarios.admin;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.AreaConhecimentoDAO;
import br.uepb.dao.CursoDAO;
import br.uepb.interfaces.IFDependencia;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Curso;
import br.uepb.model.enums.Tipo_curso;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdminCurso {
	private Administrador adm;
	private Curso curso;
	private AreaConhecimento area;
	private CursoDAO cursoDAO;
	private AreaConhecimentoDAO areaDAO;
	
	@Before
	public void setup(){
		adm = new Administrador();
		curso = new Curso();
		cursoDAO = new CursoDAO();
		areaDAO = new AreaConhecimentoDAO();
		area = new AreaConhecimento(1,"EXATAS");
	}
	
	@Test
	public void createCurso(){
		curso = new Curso("Curso", "C", area, Tipo_curso.GRADUACAO);
		assertTrue(adm.create(cursoDAO,curso));
		curso =(Curso)adm.search(cursoDAO,"Curso").get(0);
		assertTrue(adm.remove(cursoDAO,curso));
	}
	
	@Test
	public void removeCurso(){
		curso = new Curso("Curso1", "C1", area, Tipo_curso.GRADUACAO);
		assertTrue(adm.create(cursoDAO,curso));
		
		curso = new Curso("Curso2", "C2", area, Tipo_curso.POS_GRADUACAO);
		assertTrue(adm.create(cursoDAO,curso));
		
		curso = new Curso("Curso3", "C3", area, Tipo_curso.GRADUACAO);
		assertTrue(adm.create(cursoDAO,curso));
		
		curso.setNome("Curso");
		
		for(IFDependencia dep : adm.search(cursoDAO,"Curso")){
			Curso c = (Curso)dep;
			assertTrue(adm.remove(cursoDAO,c));
		}
	}
	
	@Test
	public void updateCurso(){
		curso = new Curso("Curso", "C", area, Tipo_curso.GRADUACAO);
		assertTrue(adm.create(cursoDAO,curso));
		curso = (Curso)adm.search(cursoDAO,"Curso").get(0);
		curso.setNome("NovoNome");
		assertTrue(adm.update(cursoDAO,curso));
		assertTrue(adm.remove(cursoDAO,curso));
	}
	
	@Test
	public void searchCurso(){
		curso = new Curso("Curso", "C", area, Tipo_curso.GRADUACAO);
		assertTrue(adm.create(cursoDAO,curso));
		curso = (Curso)adm.search(cursoDAO,"Curso").get(0);
		assertEquals("Curso",curso.getNome());
		assertEquals("C",curso.getSigla());
		assertEquals(Tipo_curso.GRADUACAO,curso.getTipo());
		assertEquals("EXATAS",curso.getArea().getNome());
		assertEquals(1,curso.getArea().getId());
		assertTrue(adm.remove(cursoDAO,curso));
	}
}

