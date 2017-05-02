package service;

import model.Usuario;
import dao.UsuarioDAO;


public class UsuarioService {
	UsuarioDAO dao = new UsuarioDAO();
	
	public int criar(Usuario usuario) {
		return dao.criar(usuario);
	}
	
	public void atualizar(Cliente cliente){
		dao.atualizar(cliente);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Cliente carregar(int id){
		return dao.carregar(id);
	}

}
