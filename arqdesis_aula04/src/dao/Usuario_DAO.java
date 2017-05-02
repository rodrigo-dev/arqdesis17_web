package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.*;

public class Usuario_DAO {
	String nome, nomeNovoUtilizeParaAlterar, senha, perfil;
	
	public Usuario_DAO(){
		setNome(null);
		setSenha(null);
		setPerfil(null);
		setnomeNovoUtilizeParaAlterar(null);
	}
	
	public boolean criar(){
		Connection conexao;

		try{
			conexao = new ConexaoBD().getConexao();
			
			PreparedStatement st = conexao.prepareStatement("INSERT restaurante.usuario VALUES (?,?,?)");
			st.setString(1, getNome());
			st.setString(2, getSenha());
			st.setString(3, getPerfil());
			st.execute();
			
			conexao.close();
			return true;
				
		}catch(SQLException ex){
	
			System.out.println(ex.getMessage());
			return false;	
		}
	}
	
	public boolean excluir(){
		Connection conexao;

		try{
			conexao = new ConexaoBD().getConexao();
			
			PreparedStatement st = conexao.prepareStatement("DELETE FROM restaurante.usuario WHERE nome = ?");
			st.setString(1, getNome());
			st.execute();
			
			conexao.close();
			return true;
				
		}catch(SQLException ex){
	
			System.out.println(ex.getMessage());
			return false;	
		}
	}
	
	public boolean alterarNome(){
			
		try{
			@SuppressWarnings("unused")
			ArrayList<String> dadosbd = consultar();
			if(getNome().equals(getNomeNovoUtilizeParaAlterar()) == false && consultar().size()>0){
				
				Connection conexao = new ConexaoBD().getConexao();
				
				PreparedStatement st = conexao.prepareStatement("UPDATE restaurante.usuario SET usuario.nome = ? WHERE usuario.nome = ?");
				st.setString(1, getNomeNovoUtilizeParaAlterar());
				st.setString(2, getNome());
				st.execute();
				
				conexao.close();
				return true;
				
			}else{
				
				return false;
			}
		}catch(Exception ex){
			
			System.out.println(ex.getMessage());
			return  false;		
		}
	}
	
public boolean alterarSenha(){
		
		try{
			
			Connection conexao = new ConexaoBD().getConexao();
				
			PreparedStatement st = conexao.prepareStatement("UPDATE restaurante.usuario SET usuario.senha = ? WHERE usuario.nome = ?");
			st.setString(1, getSenha());
			st.setString(2, getNome());
			st.execute();
				
			conexao.close();
			return true;
				
		}catch(Exception ex){
			
			System.out.println(ex.getMessage());
			return  false;		
		}
	}

	public boolean alterarPerfil(){
		
		try{
			
			Connection conexao = new ConexaoBD().getConexao();
				
			PreparedStatement st = conexao.prepareStatement("UPDATE restaurante.usuario SET usuario.perfil = ? WHERE usuario.nome = ? ORDER BY nome");
			st.setString(1, getPerfil());
			st.setString(2, getNome());
			st.execute();
				
			conexao.close();
			return true;
				
		}catch(Exception ex){
			
			System.out.println(ex.getMessage());
			return  false;		
		}
	}
	
	public ArrayList<String> consultarTudo(){

		try{
			Connection conexao = new ConexaoBD().getConexao();
			
			PreparedStatement st = conexao.prepareStatement("SELECT * FROM restaurante.usuario ORDER BY nome");
			st.setString(1, getNome());
			ResultSet rs = st.executeQuery();
			
			ArrayList<String> dados = new ArrayList<String>();
			
			while(rs.next()){
			
				dados.add(""+rs.getString("nome"));
				dados.add(""+rs.getString("senha"));
				dados.add(""+rs.getString("perfil"));
			
			}
			conexao.close();
			return dados;

		}catch(SQLException ex){

			System.out.println(ex.getMessage());
			return  new ArrayList<String>();			

		}

	}
	
	public ArrayList<String> consultar(){

		try{
			Connection conexao = new ConexaoBD().getConexao();
			
			PreparedStatement st = conexao.prepareStatement("SELECT * FROM restaurante.usuario WHERE nome = ?");
			st.setString(1, getNome());
			ResultSet rs = st.executeQuery();
			
			ArrayList<String> dados = new ArrayList<String>();
			
			while(rs.next()){
			
				dados.add(""+rs.getString("nome"));
				dados.add(""+rs.getString("senha"));
				dados.add(""+rs.getString("perfil"));
			
			}
			conexao.close();
			return dados;

		}catch(SQLException ex){

			System.out.println(ex.getMessage());
			return  new ArrayList<String>();			

		}

	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public String getNomeNovoUtilizeParaAlterar() {
		return nomeNovoUtilizeParaAlterar;
	}
	
	public void setnomeNovoUtilizeParaAlterar(String nomeNovoUtilizeParaAlterar) {
		this.nomeNovoUtilizeParaAlterar = nomeNovoUtilizeParaAlterar;
	}
	
}
