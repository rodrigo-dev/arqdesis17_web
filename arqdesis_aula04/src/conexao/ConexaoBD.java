package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
Connection conexao;
	
	//CONEXAOBD (TENTA CONECTAR COM O BANCO DE DADOS)
	public ConexaoBD() throws SQLException{
		try{
				try {
				
					Class.forName("com.mysql.jdbc.Driver");
				
				} catch (ClassNotFoundException e) {
					e.printStackTrace();				
				}catch(Exception i){
					i.printStackTrace();
				}
				
				conexao = DriverManager.getConnection("jdbc:mysql://localhost/restaurante","root","123456"); //Usu�rio e senha do BD
		
		}catch(SQLException ex){
			
			ex.printStackTrace();
			
		}
	}
	
	//GETCONEXAO (RETORNA UMA CONEX�O ABERTA COM O BD)
	public Connection getConexao(){
		
		return conexao;
		
	}
	
	//CLOSECONEXAO (FECHA A CONEX�O)
	public boolean closeConexao(){
		
		try{
			
			conexao.close(); 
			return true;
			
		}catch(SQLException ex){
			
			return false;
			
		}
	
	}
	
}
