package br.uepb.model.usuarios;

import java.util.ArrayList;

import br.uepb.dao.Item_Acervo;
import br.uepb.dao.usuarios.FuncionarioDAO;
import br.uepb.model.acervo.Acervo;

public class Administrador extends Usuario implements Interface_manterFuncionario,Interface_manterAcervo{
	private FuncionarioDAO funcionarioDAO;
	private String nomeUsuario;

	public Administrador(String nomeUsuario) {
		super();
		this.nomeUsuario = nomeUsuario;
		this.funcionarioDAO = new FuncionarioDAO();
		
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public boolean removeAluno(Aluno aluno) {
		if(validaAluno(aluno)) {
			return alunoDAO.removeUsuario(aluno);
		}
		return false;
		
	}

	public boolean createFuncionario(Funcionario f) {
		// TODO validar funcionario
		return funcionarioDAO.createUsuario(f);
	}

	public boolean updateFuncionario(Funcionario f) {
		// TODO validar funcionario
		return funcionarioDAO.updateUsuario(f);
	}

	public boolean removeFuncionario(Funcionario f) {
		// TODO validar funcionario
		return funcionarioDAO.removeUsuario(f);
	}

	public ArrayList<Funcionario> searchFuncionario(Funcionario f) {
		// TODO validar funcionario
		return funcionarioDAO.searchUsuario(f);
	}

	public boolean createItemAcervo(Item_Acervo itemDao,Acervo item) {
		//TODO Validar dados do item
		return itemDao.createItemAcervo(item);
	}

	public boolean removeItemAcervo(Item_Acervo itemDao,Acervo item) {
		//TODO Validar dados do item
		return itemDao.removeItemAcervo(item);
	}

	public boolean updateItemAcervo(Item_Acervo itemDao,Acervo item) {
		//TODO Validar dados do item
		return itemDao.updateItemAcervo(item);
	}

	public ArrayList<Acervo> searchItemAcervo(Item_Acervo itemDao,Acervo item) {
		//TODO Validar dados do item
		return itemDao.searchItemAcervo(item);
	}

}
