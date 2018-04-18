package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.AreaConhecimentoDAO;
import br.uepb.dao.CursoDAO;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Curso;
import br.uepb.model.enums.Tipo_curso;

public class TesteCaseCurso {
	private Curso curso;
	private CursoDAO cursoDao;
	private AreaConhecimento area;
	private AreaConhecimentoDAO areaDao;
	
	@Before
	public void setup() throws Exception{
		curso = new Curso();
		cursoDao = new CursoDAO();
	}
	
	@Test
	public void testeCreateCurso(){
		curso.setNome("Computação");
		curso.setSigla("CC");
		curso.setTipo(Tipo_curso.GRADUACAO);
		curso.setArea(new AreaConhecimento(1,"EXATAS"));//A area de conhecimento ja deve estar cadastrada
		assertTrue(cursoDao.createCurso(curso));
		
		curso = new Curso();
		curso.setNome("Teste");
		assertFalse(cursoDao.createCurso(curso));
		
		curso.setNome("Computação");
		ArrayList<Curso> lista = new ArrayList<Curso>();
		lista = cursoDao.searchCurso(curso);
		for(Curso c : lista){
			assertTrue(cursoDao.removeCurso(c));
		}
	}
	
	@Test
	public void testeRemoveCurso(){
		curso.setNome("Computação1");
		curso.setSigla("CC");
		curso.setTipo(Tipo_curso.GRADUACAO);
		curso.setArea(new AreaConhecimento(1,"EXATAS"));//A area de conhecimento ja deve está cadastrada
		assertTrue(cursoDao.createCurso(curso));
		
		curso.setNome("Computação2");
		curso.setTipo(Tipo_curso.GRADUACAO);
		curso.setArea(new AreaConhecimento(1,"EXATAS"));//A area de conhecimento ja deve está cadastrada
		assertTrue(cursoDao.createCurso(curso));
		
		curso.setNome("Computação3");
		curso.setTipo(Tipo_curso.GRADUACAO);
		curso.setArea(new AreaConhecimento(1,"EXATAS"));//A area de conhecimento ja deve está cadastrada
		assertTrue(cursoDao.createCurso(curso));
		
		curso.setNome("Computação");
		ArrayList<Curso> lista = new ArrayList<Curso>();
		lista = cursoDao.searchCurso(curso);
		for(Curso c : lista){
			assertTrue(cursoDao.removeCurso(c));
		}
	}
	
	@Test
	public void testeUpdateCurso(){
		curso.setNome("Computação1");
		curso.setSigla("CC");
		curso.setTipo(Tipo_curso.GRADUACAO);
		curso.setArea(new AreaConhecimento(1,"EXATAS"));//A area de conhecimento ja deve está cadastrada
		assertTrue(cursoDao.createCurso(curso));
		
		ArrayList<Curso> lista = new ArrayList<Curso>();
		lista = cursoDao.searchCurso(curso);
		curso = lista.get(0);
		
		curso.setNome("ComputaçãoNovo");
		curso.setTipo(Tipo_curso.POS_GRADUACAO);
		curso.setArea(new AreaConhecimento(1,"EXATAS"));//A area de conhecimento ja deve está cadastrada
		assertTrue(cursoDao.updateCurso(curso));
		
		curso.setNome("Computação");
		lista = new ArrayList<Curso>();
		lista = cursoDao.searchCurso(curso);
		for(Curso c : lista){
			assertTrue(cursoDao.removeCurso(c));
		}
		
	}
}
