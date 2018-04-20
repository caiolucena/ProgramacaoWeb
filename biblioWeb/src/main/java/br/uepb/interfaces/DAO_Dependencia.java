package br.uepb.interfaces;

import java.util.ArrayList;

public interface DAO_Dependencia<T> {
	public boolean createItemDependencia(T objeto);
	public boolean removeItemDependencia(T objeto);
	public boolean updateItemDependencia(T objeto);
	public ArrayList<T> searchItemDependencia(String nome);
		
}
