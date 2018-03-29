package br.uepb.dao;

import java.util.ArrayList;

public interface Item_Acervo<T> {
	
	public boolean createItemAcervo(T objeto);
	public boolean removeItemAcervo(T objeto);
	public boolean updateItemAcervo(T objeto);
	public ArrayList<T> searchItemAcervo(T objeto);
	
}
