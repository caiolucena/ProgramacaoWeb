package testes.usuarios;

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
	private CursoDAO cursoDao;
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
		assertTrue(cursoDao.createItemDependencia(curso));
		curso = cursoDao.searchItemDependencia("Computacao").get(0);
	}
	
	@Test
	public void testeCreateAluno() throws Exception {
		aluno = new Aluno(123,123,"CG","Lucas","Cleo","rua 2",123,curso,Tipo_nivel_aluno.G,"qwe",new Date(System.currentTimeMillis()),1,"123");
		assertTrue(usuario.createAluno(aluno));
		aluno = usuario.searchAluno("Lucas").get(0);
		assertTrue(administrador.removeAluno(aluno));
	}
	
	@Test
	public void testeRemoveAluno() throws Exception {
		aluno = new Aluno(123,123,"CG","Lucas","Cleo","rua 2",123,curso,Tipo_nivel_aluno.G,"qwe",new Date(System.currentTimeMillis()),1,"123");
		assertTrue(usuario.createAluno(aluno));
		aluno = new Aluno(123,123,"CG","Lucas Cosmo","Cleo","rua 3",123,curso,Tipo_nivel_aluno.G,"qwe",new Date(System.currentTimeMillis()),1,"123");
		aluno.setNomeCompleto("Lucas");
		assertTrue(usuario.createAluno(aluno));
		for(Aluno a:usuario.searchAluno("Lucas")){
			assertTrue(administrador.removeAluno(a));
		}	
		
	}
	
	@Test
	public void testeUpdateAluno() throws Exception {
		aluno = new Aluno(123,123,"CG","Lucas","Cleo","rua 2",123,curso,Tipo_nivel_aluno.G,"qwe",new Date(System.currentTimeMillis()),1,"123");
		assertTrue(usuario.createAluno(aluno));
		aluno = usuario.searchAluno("Lucas").get(0);
		aluno.setNomeCompleto("Lucas Cosmo");
		aluno.setEmail("email@gmail.com");
		assertTrue(usuario.updateAluno(aluno));	
		assertEquals("Lucas Cosmo",aluno.getNomeCompleto());
		assertEquals("email@gmail.com",aluno.getEmail());
		assertTrue(administrador.removeAluno(aluno));
	}
	
	@Test
	public void testeValidaAluno() {
		aluno = new Aluno();
		assertFalse(usuario.createAluno(aluno));
		aluno.setCpf(-1);
		assertFalse(usuario.createAluno(aluno));
		aluno.setCpf(123);
		aluno.setCurso(new Curso());
		assertFalse(usuario.createAluno(aluno));
		aluno.setCurso(curso);
		assertFalse(usuario.createAluno(aluno));
		aluno.setEmail("");
		assertFalse(usuario.createAluno(aluno));
		aluno.setEmail("email@gmail.com");
		assertFalse(usuario.createAluno(aluno));
		aluno.setEndereco("");
		assertFalse(usuario.createAluno(aluno));
		aluno.setEndereco("Rua da ladeira");
		assertFalse(usuario.createAluno(aluno));
		aluno.setMatricula("");
		assertFalse(usuario.createAluno(aluno));
		aluno.setMatricula("GCP101");
		assertFalse(usuario.createAluno(aluno));
		aluno.setNaturalidade("");
		assertFalse(usuario.createAluno(aluno));
		aluno.setNaturalidade("Brasil");
		assertFalse(usuario.createAluno(aluno));
		aluno.setNivel(null);
		assertFalse(usuario.createAluno(aluno));
		aluno.setNivel(Tipo_nivel_aluno.D);
		assertFalse(usuario.createAluno(aluno));
		aluno.setNomeCompleto("");
		assertFalse(usuario.createAluno(aluno));
		aluno.setNomeCompleto("Aluno1");
		assertFalse(usuario.createAluno(aluno));
		aluno.setNomeMae("");
		assertFalse(usuario.createAluno(aluno));
		aluno.setNomeMae("Mae");
		assertFalse(usuario.createAluno(aluno));
		aluno.setPeriodoIngresso(-1);
		assertFalse(usuario.createAluno(aluno));
		aluno.setAnoIngresso(null);
		assertFalse(usuario.createAluno(aluno));
		aluno.setAnoIngresso(new Date(System.currentTimeMillis()));
		assertFalse(usuario.createAluno(aluno));
		aluno.setPeriodoIngresso(1);
		assertFalse(usuario.createAluno(aluno));
		aluno.setRg(0);
		assertFalse(usuario.createAluno(aluno));
		aluno.setRg(123);
		assertFalse(usuario.createAluno(aluno));
		aluno.setSenhaAcesso("");
		assertFalse(usuario.createAluno(aluno));
		aluno.setSenhaAcesso("senha");
		assertFalse(usuario.createAluno(aluno));
		aluno.setTelefone(-1);
		assertFalse(usuario.createAluno(aluno));
		aluno.setTelefone(12345);
		assertTrue(usuario.createAluno(aluno));
		aluno = usuario.searchAluno("Aluno1").get(0);
		administrador.removeAluno(aluno);
	}
	
	@After
	public void remove(){
		curso.setNome("Computacao");
		if(cursoDao.searchItemDependencia("Computacao").size() >= 1){
			for(Curso c:cursoDao.searchItemDependencia("Computacao")){
				assertTrue(cursoDao.removeItemDependencia(c));
			}
		}		
		
	}

}
