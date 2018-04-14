package br.uepb.model.usuarios;

import java.util.ArrayList;

public interface Interface_manterFuncionario {
	
	public boolean createFuncionario(Funcionario f);
	public boolean updateFuncionario(Funcionario f);
	public boolean removeFuncionario(Funcionario f);
	public ArrayList<Funcionario> searchFuncionario(Funcionario f);

}
