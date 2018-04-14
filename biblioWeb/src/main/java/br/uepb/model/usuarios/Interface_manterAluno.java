package br.uepb.model.usuarios;

import java.util.ArrayList;

public interface Interface_manterAluno {
	
	public boolean createAluno(Aluno aluno);
	public boolean updateAluno(Aluno aluno);
	public ArrayList<Aluno> searchAluno(Aluno aluno);

}
