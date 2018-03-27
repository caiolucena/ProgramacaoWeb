package testeMidias;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.uepb.dao.acervo.MidiasEletronicasDAO;
import br.uepb.model.acervo.Midias_Eletronicas;
import br.uepb.model.enums.Tipo_Midia;

public class TesteCaseMidia {
	private Midias_Eletronicas midia;
	private MidiasEletronicasDAO midiaDao;
	
	@Before
	public void setup() throws Exception{
		midia = new Midias_Eletronicas();
		midiaDao = new MidiasEletronicasDAO();
	}
	
	@Test
	public void testeCreateMidia(){
		midia.setTitulo("Teste");
		midia.setTipo(Tipo_Midia.CD);
		Date data = new Date(System.currentTimeMillis());
		midia.setData_gravacao(data);		
		assertTrue(midiaDao.createItemAcervo(midia));
		
		midia = new Midias_Eletronicas();
		assertFalse(midiaDao.createItemAcervo(midia));
		
		ArrayList<Midias_Eletronicas> lista = new ArrayList<Midias_Eletronicas>();
		lista = midiaDao.searchItemAcervo(midia);
		for(Midias_Eletronicas m:lista){
			assertTrue(midiaDao.removeItemAcervo(m));
		}
	}
	
	@Test
	public void testeUpdateMidia(){
		midia.setTitulo("Teste");
		midia.setTipo(Tipo_Midia.CD);
		Date data = new Date(System.currentTimeMillis());
		midia.setData_gravacao(data);		
		assertTrue(midiaDao.createItemAcervo(midia));
		ArrayList<Midias_Eletronicas> lista = new ArrayList<Midias_Eletronicas>();
		midia.setTitulo("Teste");
		lista = midiaDao.searchItemAcervo(midia);
		midia = lista.get(0);
		midia.setTitulo("Novo Teste");
		midia.setTipo(Tipo_Midia.DVD);
		assertTrue(midiaDao.updateItemAcervo(midia));
		lista = midiaDao.searchItemAcervo(midia);
		for(Midias_Eletronicas m:lista){
			assertTrue(midiaDao.removeItemAcervo(m));
		}
	}
	
	@Test
	public void testeRemoveMidia(){
		midia.setTitulo("Teste1");
		midia.setTipo(Tipo_Midia.CD);
		Date data = new Date(System.currentTimeMillis());
		midia.setData_gravacao(data);		
		assertTrue(midiaDao.createItemAcervo(midia));
		midia.setTitulo("Teste2");
		midia.setTipo(Tipo_Midia.CD);
		data = new Date(System.currentTimeMillis());
		midia.setData_gravacao(data);		
		assertTrue(midiaDao.createItemAcervo(midia));
		midia.setTitulo("Teste3");
		midia.setTipo(Tipo_Midia.CD);
		data = new Date(System.currentTimeMillis());
		midia.setData_gravacao(data);		
		assertTrue(midiaDao.createItemAcervo(midia));
		ArrayList<Midias_Eletronicas> lista = new ArrayList<Midias_Eletronicas>();
		midia.setTitulo("Teste");
		lista = midiaDao.searchItemAcervo(midia);
		System.out.println(lista.size());
		for(Midias_Eletronicas m:lista){
			assertTrue(midiaDao.removeItemAcervo(m));
		}
	}

}
