package br.uepb.interfaces;

import java.util.ArrayList;

import br.uepb.model.usuarios.Aluno;

/**
 * Essa é a interface que contém as assinaturas dos métodos para criar, atualizar e buscar um objeto do tipo Aluno
 * @author EquipeACL
 *
 */
public interface Interface_manterAluno {
	
	public boolean createAluno(Aluno aluno);
	public boolean updateAluno(Aluno aluno);
	public ArrayList<Aluno> searchAluno(String nome);

}
