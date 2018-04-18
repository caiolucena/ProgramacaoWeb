package br.uepb.model.usuarios;

import java.util.ArrayList;

/**
 * Essa é a interface que contém as assinaturas dos métodos para criar, atualizar, buscar e remover um objeto do tipo Funcionário
 * @author Cleonice
 *
 */
public interface Interface_manterFuncionario {
	
	public boolean createFuncionario(Funcionario f);
	public boolean updateFuncionario(Funcionario f);
	public boolean removeFuncionario(Funcionario f);
	public ArrayList<Funcionario> searchFuncionario(Funcionario f);

}
