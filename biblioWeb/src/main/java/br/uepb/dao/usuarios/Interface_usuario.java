package br.uepb.dao.usuarios;

import java.util.ArrayList;

/**
 * Essa é a interface que contém as assinaturas dos métodos para criar, remover, atualizar e buscar objetos do tipo Usuario
 * @author EquipeACL
 *
 */
public interface Interface_usuario<T> {
	
	public boolean createUsuario(T usuario);
	public boolean removeUsuario(T usuario);
	public boolean updateUsuario(T usuario);
	public ArrayList<T> searchUsuario(T usuario);

}
