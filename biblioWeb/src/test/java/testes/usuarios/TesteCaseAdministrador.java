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
import br.uepb.dao.usuarios.AdministradorDAO;
import br.uepb.interfaces.IFAcervo;
import br.uepb.model.AreaConhecimento;
import br.uepb.model.Autor;
import br.uepb.model.Editora;
import br.uepb.model.Tema;
import br.uepb.model.acervo.Livro;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdministrador {
	private Administrador adm;
	private AdministradorDAO admDAO;
	
	@Before
	public void setup() {
		admDAO = new AdministradorDAO();
	}
	
	@Test
	public void createAdministrador() {
		adm = new Administrador(123, "Administrador", 456, "Brasileiro","Rua do beco", 3345, "email@gmail.com","senha","admin1");
		assertTrue(admDAO.createAdministrador(adm));
		assertTrue(admDAO.removeUsuario(adm));
	}
	
	@Test
	public void updateAdministrador() {
		adm = new Administrador(123, "Administrador", 456, "Brasileiro","Rua do beco", 3345, "email@gmail.com","senha","admin1");
		assertTrue(admDAO.createAdministrador(adm));
		adm.setNomeCompleto("NovoNome");
		adm.setSenhaAcesso("novaSenha");
		assertTrue(admDAO.updateUsuario(adm));
	}
	
	@Test
	public void removeAdministrador() {
		adm = new Administrador(123, "Administrador1", 456, "Brasileiro","Rua do beco", 3345, "email@gmail.com","senha","admin1");
		assertTrue(admDAO.createAdministrador(adm));
		adm = new Administrador(123, "Administrador2", 456, "Brasileiro","Rua do beco", 3345, "email@gmail.com","senha","admin2");
		assertTrue(admDAO.createAdministrador(adm));
		adm = new Administrador(123, "Administrador3", 456, "Brasileiro","Rua do beco", 3345, "email@gmail.com","senha","admin3");
		assertTrue(admDAO.createAdministrador(adm));
		adm.setNomeCompleto("Administrador");
		for(Administrador a : admDAO.searchUsuario(adm)){
			assertTrue(admDAO.removeUsuario(a));			
		}
	}
	
	@Test
	public void searchAdministrador() {
		adm = new Administrador(123, "Administrador", 456, "Brasileiro","Rua do beco", 3345, "email@gmail.com","senha","admin1");
		assertTrue(admDAO.createAdministrador(adm));
		Administrador adminis = admDAO.searchUsuario(adm).get(0);
		assertEquals(adm.getNomeCompleto(),adminis.getNomeCompleto());
		assertEquals(adm.getCpf(),adminis.getCpf());
		assertEquals(adm.getEmail(),adminis.getEmail());
		assertEquals(adm.getEndereco(),adminis.getEndereco());
		assertEquals(adm.getNaturalidade(),adminis.getNaturalidade());
		assertEquals(adm.getNomeUsuario(),adminis.getNomeUsuario());
		assertEquals(adm.getRg(),adminis.getRg());
		assertEquals(adm.getSenhaAcesso(),adminis.getSenhaAcesso());
		assertEquals(adm.getTelefone(),adminis.getTelefone());
	}
	
	@After
	public void clean(){
		
		
	}
}
