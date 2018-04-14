package br.uepb.dao.usuarios;

import java.util.ArrayList;

public interface Interface_usuario<T> {
	
	public boolean createUsuario(T usuario);
	public boolean removeUsuario(T usuario);
	public boolean updateUsuario(T usuario);
	public ArrayList<T> searchUsuario(T usuario);

}
