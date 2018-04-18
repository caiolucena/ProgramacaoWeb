package br.uepb.model.usuarios;

import java.util.ArrayList;

import br.uepb.dao.Item_Acervo;
import br.uepb.model.acervo.Acervo;

/**
 * Essa é a interface que contém as assinaturas dos métodos para criar, remover, atualizar e buscar objetos que implementem a interface Item_Acervo
 * @author EquipeACL
 *
 */
public interface Interface_manterAcervo {
	
	public boolean createItemAcervo(Item_Acervo itemDao,Acervo item);
	public boolean removeItemAcervo(Item_Acervo itemDao,Acervo item);
	public boolean updateItemAcervo(Item_Acervo itemDao,Acervo item);
	public ArrayList<Acervo> searchItemAcervo(Item_Acervo itemDao,Acervo item);
	
}
