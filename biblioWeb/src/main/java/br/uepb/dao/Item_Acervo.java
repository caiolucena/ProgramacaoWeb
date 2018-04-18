package br.uepb.dao;

import java.util.ArrayList;

/**
 * Essa é a interface que contém as assinaturas dos métodos para criar, remover, atualizar e buscar objetos de um tipo generico. Utilizado para implementar inserção, remoção, atualização e busca de objetos do acervo.
 * @author EquipeACL
 *
 * @param <T>, tipo generico de dado.
 */
public interface Item_Acervo<T> {
	
	public boolean createItemAcervo(T objeto);
	public boolean removeItemAcervo(T objeto);
	public boolean updateItemAcervo(T objeto);
	public ArrayList<T> searchItemAcervo(T objeto);
	
}
