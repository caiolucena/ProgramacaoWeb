package br.uepb.interfaces;

import java.util.ArrayList;
import br.uepb.interfaces.IFDependencia;
public interface Interface_manterDependencias {
	public boolean create(DAO_Dependencia itemDAO ,IFDependencia item);
	public boolean update(DAO_Dependencia itemDAO ,IFDependencia item);
	public boolean remove(DAO_Dependencia itemDAO ,IFDependencia item);
	public ArrayList<IFDependencia> search(DAO_Dependencia itemDAO ,String nome);

}
