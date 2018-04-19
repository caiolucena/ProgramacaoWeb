package testes;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.CursoDAO;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Curso;
import br.uepb.model.enums.Tipo_curso;
import br.uepb.model.enums.Tipo_nivel_aluno;
import br.uepb.model.usuarios.Administrador;
import br.uepb.model.usuarios.Aluno;
import br.uepb.model.usuarios.Usuario;

public class TesteCaseAluno {
	CursoDAO cursoDao;
	private Aluno aluno;
	private Usuario usuario;
	private Administrador administrador;
	private AreaConhecimento area;
	private Curso curso;
	
	@Before
	public void setup(){
		usuario = new Usuario();
		administrador = new Administrador();
		cursoDao = new CursoDAO();
		area = new AreaConhecimento(1,"EXATAS");
		curso = new Curso("Computacao","CP", area, Tipo_curso.GRADUACAO);
		assertTrue(cursoDao.createCurso(curso));
		curso = cursoDao.searchCurso(curso).get(0);
		
	}
	
	@Test
	public void testeCreateAluno() throws Exception {
		aluno = new Aluno(123,123,"CG","Lucas","Cleo","rua 2",123,curso,Tipo_nivel_aluno.G,"qwe",new Date(System.currentTimeMillis()),1,"123");
		assertTrue(usuario.createAluno(aluno));
		aluno = usuario.searchAluno(aluno).get(0);
		assertTrue(administrador.removeAluno(aluno));
	}
	
	@Test
	public void testeRemoveAluno() throws Exception {
		aluno = new Aluno(123,123,"CG","Lucas","Cleo","rua 2",123,curso,Tipo_nivel_aluno.G,"qwe",new Date(System.currentTimeMillis()),1,"123");
		assertTrue(usuario.createAluno(aluno));
		aluno = new Aluno(123,123,"CG","Lucas Cosmo","Cleo","rua 3",123,curso,Tipo_nivel_aluno.G,"qwe",new Date(System.currentTimeMillis()),1,"123");
		aluno.setNomeCompleto("Lucas");
		assertTrue(usuario.createAluno(aluno));
		for(Aluno a:usuario.searchAluno(aluno)){
			assertTrue(administrador.removeAluno(a));
		}	
		
	}
	
	@Test
	public void testeUpdateAluno() throws Exception {
		aluno = new Aluno(123,123,"CG","Lucas","Cleo","rua 2",123,curso,Tipo_nivel_aluno.G,"qwe",new Date(System.currentTimeMillis()),1,"123");
		assertTrue(usuario.createAluno(aluno));
		aluno.setNomeCompleto("Lucas Cosmo");
		aluno.setEmail("email@gmail.com");
		assertTrue(usuario.updateAluno(aluno));
		aluno = usuario.searchAluno(aluno).get(0);
		assertEquals("Lucas Cosmo",aluno.getNomeCompleto());
		assertEquals("email@gmail.com",aluno.getEmail());
		assertTrue(administrador.removeAluno(aluno));
	}
	
	@After
	public void remove(){
		curso.setNome("Computacao");
		if(cursoDao.searchCurso(curso).size() >= 1){
			for(Curso c:cursoDao.searchCurso(curso)){
				assertTrue(cursoDao.removeCurso(c));
			}
		}		
		
	}

}
