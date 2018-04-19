package testes;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.acervo.MidiasEletronicasDAO;
import br.uepb.model.acervo.Midias_Eletronicas;
import br.uepb.model.enums.Tipo_midia;
import br.uepb.model.usuarios.Administrador;

public class testeCaseAdministrador {
	private Administrador adm;
	
	@Before
	public void setup() {
		adm = new Administrador();
	}
	
	@Test
	public void createItemAcervo() {
		assertTrue(adm.createItemAcervo(new MidiasEletronicasDAO(), new Midias_Eletronicas("Midia",Tipo_midia.DVD,new Date(System.currentTimeMillis()))));
	}
}
