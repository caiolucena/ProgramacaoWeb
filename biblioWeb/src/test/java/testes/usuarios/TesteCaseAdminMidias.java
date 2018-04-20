package testes.usuarios;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.acervo.MidiasEletronicasDAO;
import br.uepb.interfaces.IFAcervo;
import br.uepb.model.acervo.Midias_Eletronicas;
import br.uepb.model.enums.Tipo_midia;
import br.uepb.model.usuarios.Administrador;

public class TesteCaseAdminMidias {
	
	private Administrador adm;
	private Midias_Eletronicas dvd;
	
	@Before
	public void setUp() throws Exception {
		adm = new Administrador();
		dvd = new Midias_Eletronicas();
	}

	@Test
	public void createMidiaEletronica() {
		dvd = new Midias_Eletronicas("dvd do meu tcc do meu tcc", Tipo_midia.DVD, new Date(System.currentTimeMillis()));
		assertTrue(adm.createItemAcervo(new MidiasEletronicasDAO(), dvd));
		
		dvd = (Midias_Eletronicas) adm.searchItemAcervo(new MidiasEletronicasDAO(),dvd.getTitulo()).get(0);
		assertTrue(adm.removeItemAcervo(new MidiasEletronicasDAO(), dvd));
		
		assertFalse(adm.createItemAcervo(new MidiasEletronicasDAO(), new Midias_Eletronicas()));
	}
	
	@Test
	public void removeMidiaEletronica() {
		dvd = new Midias_Eletronicas("dvd do meu tcc", Tipo_midia.DVD, new Date(System.currentTimeMillis()));
		assertTrue(adm.createItemAcervo(new MidiasEletronicasDAO(), dvd));
		
		dvd = new Midias_Eletronicas("dvd do meu tcc", Tipo_midia.DVD, new Date(System.currentTimeMillis()));
		assertTrue(adm.createItemAcervo(new MidiasEletronicasDAO(), dvd));
		
		dvd = new Midias_Eletronicas("dvd do meu tcc", Tipo_midia.DVD, new Date(System.currentTimeMillis()));
		assertTrue(adm.createItemAcervo(new MidiasEletronicasDAO(), dvd));
		
		for(IFAcervo dep:adm.searchItemAcervo(new MidiasEletronicasDAO(),"dvd do meu tcc")){
			Midias_Eletronicas e = (Midias_Eletronicas) dep;
			assertTrue(adm.removeItemAcervo(new MidiasEletronicasDAO(),e));
		}

	}
	
	@Test
	public void updateMidiaEletronica() {
		dvd = new Midias_Eletronicas("dvd do meu tcc", Tipo_midia.DVD, new Date(System.currentTimeMillis()));
		assertTrue(adm.createItemAcervo(new MidiasEletronicasDAO(), dvd));
		
		dvd = (Midias_Eletronicas) adm.searchItemAcervo(new MidiasEletronicasDAO(),dvd.getTitulo()).get(0);
		dvd.setTitulo("dvd do meu mestrado");
		
		assertTrue(adm.updateItemAcervo(new MidiasEletronicasDAO(), dvd));
		assertTrue(adm.removeItemAcervo(new MidiasEletronicasDAO(), dvd));
		
		
	}
	
}
