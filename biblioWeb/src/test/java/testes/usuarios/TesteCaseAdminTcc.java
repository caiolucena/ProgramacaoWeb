package testes.usuarios;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.AutorDAO;
import br.uepb.dao.CidadeDAO;
import br.uepb.dao.OrientadorDAO;
import br.uepb.dao.acervo.TccDAO;
import br.uepb.model.Autor;
import br.uepb.model.Cidade;
import br.uepb.model.Orientador;
import br.uepb.model.acervo.Tcc;
import br.uepb.model.enums.Tipo_tcc;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdminTcc {

	Tcc trabalho;
	TccDAO trabalhoDao;
	Autor autor;
	AutorDAO autorDao;
	Orientador orientador;
	OrientadorDAO orientadorDao;
	Cidade cidade;
	CidadeDAO cidadeDao;
	Administrador adm;

	@Before
	public void setup() throws Exception {
		adm = new Administrador();
		trabalho = new Tcc();
		trabalhoDao = new TccDAO();
		autor = new Autor();
		autorDao = new AutorDAO();
		orientador = new Orientador();
		orientadorDao = new OrientadorDAO();
		cidade = new Cidade(1294, 2504009, "Campina Grande", "PB");
		cidadeDao = new CidadeDAO();

	}

	@Test
	public void updateTcc() {
		orientador.setNome("Orientador1");
		orientador.setFormacao("Graduado");
		assertTrue(orientadorDao.createItemDependencia(orientador));
		orientador = orientadorDao.searchItemDependencia("Orientador1").get(0);

		autor.setNome("Autor1");
		autorDao.createItemDependencia(autor);
		autor = autorDao.searchItemDependencia("Autor1").get(0);

		trabalho.setAutor(autor);
		trabalho.setAno_defesa(new Date(System.currentTimeMillis()));
		trabalho.setCidade(cidade);
		trabalho.setTitulo("Trabalho de conclusao");
		trabalho.setTipo(Tipo_tcc.MONOGRAFIA);
		trabalho.setOrientador(orientador);

		assertTrue(adm.createItemAcervo(new TccDAO(), trabalho));
		trabalho = (Tcc)adm.searchItemAcervo(new TccDAO(),"Trabalho de conclusao").get(0);
		trabalho.setTitulo("Trabalho");
		assertTrue(adm.updateItemAcervo(new TccDAO(), trabalho));
		adm.removeItemAcervo(new TccDAO(), trabalho);

		
	}
	
	@After
	public void limpar() {
		ArrayList<Autor> lista = autorDao.searchItemDependencia("Autor");
		for(Autor a: lista) {
			autorDao.removeItemDependencia(a);
			
		}
		
	}
}
