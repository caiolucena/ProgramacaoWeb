package testes.usuarios;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.AreaConhecimentoDAO;
import br.uepb.dao.AutorDAO;
import br.uepb.dao.EditoraDAO;
import br.uepb.dao.TemaDAO;
import br.uepb.dao.acervo.LivroDAO;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Autor;
import br.uepb.model.Editora;
import br.uepb.model.Tema;
import br.uepb.model.acervo.IFAcervo;
import br.uepb.model.acervo.Livro;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdministrador {
	private Administrador adm;
	private Livro livro;
	private Editora editora;
	private EditoraDAO editoraDao;
	private AreaConhecimento area;
	private AreaConhecimentoDAO areaDao;
	private Tema tema;
	private TemaDAO temaDao;
	private Autor autor1;
	private Autor autor2;
	private AutorDAO autorDao;
	
	@Before
	public void setup() {
		adm = new Administrador();
		livro = new Livro();
		editora = new Editora();
		editoraDao = new EditoraDAO();
		area = new AreaConhecimento();
		areaDao = new AreaConhecimentoDAO();
		tema = new Tema();
		temaDao = new TemaDAO();
		autor1 = new Autor();
		autor2 = new Autor();
		autorDao = new AutorDAO();
		
		editora.setNome("Editora1");
		assertTrue(editoraDao.createEditora(editora));
		editora = editoraDao.searchEditora(editora).get(0);
				
		area.setNome("Area1");
		areaDao.createAreaConhecimento(area);
		area = areaDao.searchAreaConhecimento(area).get(0);
		
		tema.setNome("Tema1");
		tema.setArea(area);
		temaDao.createTema(tema);
		tema = temaDao.searchTema(tema).get(0);
		
		autor1.setNome("Autor1");
		autor2.setNome("Autor2");
		autorDao.createAutor(autor1);
		autorDao.createAutor(autor2);
		autor1 = autorDao.searchAutor(autor1).get(0);
		autor2 = autorDao.searchAutor(autor2).get(0);
	}
	
	@Test
	public void createItemAcervo() {
		
		livro.setIsbn(123456);
		livro.setTitulo("Livro Teste");
		livro.setArea(area);
		livro.setAutores(new ArrayList<Autor>(){{add(autor1);add(autor2);}});
		livro.setEdicao(1);
		livro.setEditora(editora);
		livro.setNumero_paginas(200);
		livro.setData(new Date(System.currentTimeMillis()));		
		assertTrue(adm.createItemAcervo(new LivroDAO(), livro));
		assertTrue(adm.removeItemAcervo(new LivroDAO(), livro));
	}
	
	@Test
	public void updateItemAcervo() {
		livro.setIsbn(123456);
		livro.setTitulo("Livro Teste1");
		livro.setArea(area);
		livro.setAutores(new ArrayList<Autor>(){{add(autor1);add(autor2);}});
		livro.setEdicao(1);
		livro.setEditora(editora);
		livro.setNumero_paginas(200);
		livro.setData(new Date(System.currentTimeMillis()));
		assertTrue(adm.createItemAcervo(new LivroDAO(), livro));
		
		livro.setTitulo("Livro Teste2");
		livro.setNumero_paginas(198);
		livro.setEdicao(4);
		
		assertTrue(adm.updateItemAcervo(new LivroDAO(), livro));
		assertTrue(adm.removeItemAcervo(new LivroDAO(), livro));
	}
	
	@Test
	public void searchItemAcervo() {
		livro.setIsbn(123456);
		livro.setTitulo("Livro Teste1");
		livro.setArea(area);
		livro.setAutores(new ArrayList<Autor>(){{add(autor1);add(autor2);}});
		livro.setEdicao(1);
		livro.setEditora(editora);
		livro.setNumero_paginas(200);
		livro.setData(new Date(System.currentTimeMillis()));
		assertTrue(adm.createItemAcervo(new LivroDAO(), livro));
		
		livro.setIsbn(121233);
		livro.setTitulo("Livro Teste2");
		livro.setArea(area);
		livro.setAutores(new ArrayList<Autor>(){{add(autor1);add(autor2);}});
		livro.setEdicao(2);
		livro.setEditora(editora);
		livro.setNumero_paginas(123);
		livro.setData(new Date(System.currentTimeMillis()));
		assertTrue(adm.createItemAcervo(new LivroDAO(), livro));
		
		livro.setTitulo("Livro Teste");
		for(IFAcervo livroLista : adm.searchItemAcervo(new LivroDAO(), livro)){
			Livro l = (Livro)livroLista;
			assertTrue(adm.removeItemAcervo(new LivroDAO(), l));
		}
	}
	
	@After
	public void clean(){
		temaDao.removeTema(tema);
		areaDao.removeAreaConhecimento(area);		
		editoraDao.removeEditora(editora);		
		autorDao.removeAutor(autor1);
		autorDao.removeAutor(autor2);
		
	}
}
