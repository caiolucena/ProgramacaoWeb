package testes;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import br.uepb.dao.usuarios.AlunoDAO;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Curso;
import br.uepb.model.enums.Tipo_curso;
import br.uepb.model.enums.Tipo_nivel_aluno;
import br.uepb.model.usuarios.Aluno;

public class TesteCaseAluno {
	
	@Test
	public void test() throws Exception {
		AreaConhecimento area = new AreaConhecimento(1,"EXATAS");
		Curso c = new Curso("sad","as", area, Tipo_curso.graduacao);
		c.setId(1);
		Aluno a = new Aluno(123,123,"CG","Lucas","Cleo","rua 2",123,c,Tipo_nivel_aluno.G,"qwe",new Date(System.currentTimeMillis()),1,"123");
		System.out.println(a.getMatricula());
		
		AlunoDAO dao = new AlunoDAO();
		assertTrue(dao.createUsuario(a));
	}

}
