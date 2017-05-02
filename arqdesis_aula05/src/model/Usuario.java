package model;

import java.util.ArrayList;

import dao.UsuarioDAO;

public class Usuario {
	
	private String nome, perfil, login, senha, loginNovoUtilizeParaAlterar;
	private int id;


	public Usuario(){
		
		setPerfil(null);
		setLogin(null);
		setSenha(null);
	
	}
	
	public Usuario(String perfil, String login, String senha) {
		this.perfil = perfil;
		this.login = login;
		this.senha = senha;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public boolean logar(){
		
		try{
			UsuarioDAO usuario = new UsuarioDAO();
			usuario.setNome(getLogin());
			ArrayList<String> dadosbd = usuario.carregar(2)
			
			if(dadosbd != null && dadosbd.get(1).equals(getSenha())){
				return true;
			}else{
			
				return false;
			
			}
		}catch(NullPointerException error){
			
			try{//Verifica se o campo que está vazio é o login
			
				login.length();
				
				try{//Verifica se o campo que está vazio é a senha
					
					senha.length();
					return false;
					
				}catch(Exception ex){
					
					System.out.println("O campo senha não pode ficar vazio");
					return false;
				}
			
			}catch(Exception ex){
				
				System.out.println("O campo login não pode ficar vazio");
				return false;
			}
			
		}catch(Exception error){
			
			System.out.println("Ocorreu um erro ao processar a operação: "+error.getMessage());
			return false;
		}
		
	}
	
	public ArrayList<String> consultar(){
		if(getLogin() != null){
			
			UsuarioDAO usuario = new UsuarioDAO();
			usuario.setNome(getLogin());
			return usuario.consultar();
		
		}else{
		
			return new ArrayList<String>();
		}
	}
	
	public boolean alterarLogin(){
		
		if(getLogin() != null){
		
			//Verifica se o login informado para alteração já não existe
			UsuarioDAO usuario = new UsuarioDAO();
			usuario.setNome(getLoginNovoUtilizeParaAlterar());
			
			if(usuario.consultar().size()==0){
				
				usuario.setNome(getLogin());
				usuario.setnomeNovoUtilizeParaAlterar(getLoginNovoUtilizeParaAlterar());
				return usuario.alterarNome();
				
			}else{
				
				System.out.println("O login novo desejado já existe no banco de dados, por gentileza escolha outro login.");
				return false;
			}
			
		}else{
			return false;
		}
	}

	public boolean alterarPerfil(){
		
		if(getPerfil() != null && getLogin() != null){
		
			UsuarioDAO usuario = new UsuarioDAO();
			usuario.setNome		(getLogin());
			usuario.setPerfil	(getPerfil());
			return usuario.alterarPerfil();
		
		}else{
			return false;
		}
	}
	
	public boolean alterarSenha(){
		
		if(getPerfil() != null && getLogin() != null){
		
			UsuarioDAO usuario = new UsuarioDAO();
			usuario.setNome		(getLogin());
			usuario.setSenha	(getSenha());
			return usuario.alterarSenha();
		
		}else{
			return false;
		}
	}
	
	public boolean excluir(){
		
		try{
			if(getLogin() != null){
				
				UsuarioDAO usuario = new UsuarioDAO();
				usuario.setNome(getLogin());
				return usuario.excluir();
				
			}else{
				return false;
			}
		}catch(NullPointerException ex){
			
			System.out.println("O campo login não pode ficar vazio.");
			return false;
			
		}catch(Exception ex){
			
			System.out.println("Ocorreu um erro ao executar a operação: \n"+ex.getMessage());
			return false;
		}
		
	}
	
	public boolean criar(){
		
		try{	
			if(getPerfil() != null && getLogin() != null && getSenha() != null){//Valida se todos os campos foram preenchidos
				
				UsuarioDAO usuario = new UsuarioDAO();
				usuario.setNome(getLogin());
				usuario.setSenha(getSenha());
				usuario.setPerfil(getPerfil());
				
				return usuario.criar();//retorna true se o login foi criado
				
			}else{	
				return false;
			}
				
		}catch(NullPointerException error){//1º Catch, é executado se o problema causado foi ao tentar caucular o tamanho de uma campo
			
			try{//Verifica se o campo que está vazio é o login
				login.length();
				
				try{//Verifica se o campo que está vazio é a senha
					getSenha().length();
				
					try{//Verifica se o campo que está vazio é o perfil
						perfil.length();
							
					}catch(Exception ex){
						
						System.out.println("O campo perfil não pode ficar vazio");	
					}
				}catch(Exception ex){
					
					System.out.println("O campo senha não pode ficar vazio");	
				}
			}catch(Exception ex){
				
				System.out.println("O campo login não pode ficar vazio");
			}
			
			return false;
				
		}catch(Exception error){//Segundo catch, qualquer problema que não seja o tamanho do campo
			
			System.out.println("Ocorreu um erro ao processar a operação: "+error.getMessage());
			return false;
		}
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getLoginNovoUtilizeParaAlterar() {
		return loginNovoUtilizeParaAlterar;
	}
	
	public void setLoginNovoUtilizeParaAlterar(String loginNovoUtilizeParaAlterar) {
		this.loginNovoUtilizeParaAlterar = loginNovoUtilizeParaAlterar;
	}

		
}
