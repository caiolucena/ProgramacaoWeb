package br.uepb.dao;

public interface Item_Acervo<T> {
	
	public boolean createItemAcervo(T objeto);
	public boolean removeItemAcervo(T objeto);
	public boolean updateItemAcervo(T objeto);
	public T searchItemAcervo(T objeto);

}
