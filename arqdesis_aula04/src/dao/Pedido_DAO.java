package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.ConexaoBD;

public class Pedido_DAO {

	static int mesa;

	public boolean cadastrar(int mesa){
		
		Connection conexao;
		try{
			setMesa(mesa);
			conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("INSERT restaurante.pedido (mesa, hora_entrada, status) values (?,sysdate(),?)");
			st.setInt(1, mesa);
			st.setBoolean(2, true);
			st.execute();
			conexao.close();
			return true;
		
		}catch(SQLException ex){

			ex.printStackTrace();			
			return false;
		
		}
	
	}


	public boolean excluir(int id_pedido){
	
		try{
			setId_pedido(id_pedido);
			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("DELETE FROM restaurante.pedido WHERE restaurante.pedido.id_pedido = ?");
			st.setInt(1, id_pedido);
			st.execute();
			conexao.close();
			return true;

		}catch(SQLException ex){

			ex.printStackTrace();
			return false;			

		}

	}


	//***********************	CONSULTA	***********************

	public ArrayList<String> consultarTudo(){

		try{

			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("SELECT "
															+"restaurante.pedido.id_pedido, "
															+"restaurante.pedido.mesa, "
															+"restaurante.pedido.hora_entrada, "
															+"restaurante.pedido.hora_saida, "
															+"CASE restaurante.pedido.status WHEN true THEN 'Aberto' ELSE 'Fechado' END as status, "
															+"COUNT(restaurante.item_pedido.id_pedido) qtde_itens_pedidos, "
															+"CONCAT('R$', CAST( REPLACE(restaurante.item_pedido.quantidade * restaurante.item_cardapio.preco,',','.') AS DECIMAL(18,2))) as valor_total "
															+"FROM restaurante.item_pedido RIGHT JOIN restaurante.pedido "
															+"ON restaurante.pedido.id_pedido = restaurante.item_pedido.id_pedido "
															+"LEFT JOIN restaurante.item_cardapio "
															+"ON restaurante.item_pedido.id_item_cardapio = restaurante.item_cardapio.id_item_cardapio "
															+"GROUP BY restaurante.pedido.id_pedido "
															+"ORDER BY restaurante.pedido.id_pedido ");
			ResultSet rs = st.executeQuery();
			ArrayList<String> dados = new ArrayList<String>();
			
			while(rs.next()){
			
				dados.add(""+rs.getInt("id_pedido"));
				dados.add(""+rs.getInt("mesa"));
				dados.add(""+rs.getDate("hora_entrada"));
				dados.add(""+rs.getDate("hora_saida"));
				dados.add(""+rs.getString("status"));
				dados.add(""+rs.getDouble("qtde_itens_pedidos"));
				dados.add(""+rs.getDouble("valor_total"));
				
			}
			conexao.close();
			return dados;

		}catch(SQLException ex){

			ex.printStackTrace();
			return  new ArrayList<String>();			

		}

	}

	public ArrayList<String> consultar(int id_pedido){

		try{
			setId_pedido(id_pedido);
			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("SELECT "
															+"restaurante.pedido.id_pedido, "
															+"restaurante.pedido.mesa, "
															+"restaurante.pedido.hora_entrada, "
															+"restaurante.pedido.hora_saida, "
															+"CASE restaurante.pedido.status WHEN true THEN 'Aberto' ELSE 'Fechado' END as status, "
															+"COUNT(restaurante.item_pedido.id_pedido) AS qtde_itens_pedidos, "
															+"CONCAT('R$', CAST( REPLACE(restaurante.item_pedido.quantidade * restaurante.item_cardapio.preco,',','.') AS DECIMAL(18,2))) as valor_total "
															+"FROM restaurante.item_pedido RIGHT JOIN restaurante.pedido "
															+"ON restaurante.pedido.id_pedido = restaurante.item_pedido.id_pedido "
															+ "LEFT JOIN restaurante.item_cardapio "
															+ "ON restaurante.item_pedido.id_item_cardapio = restaurante.item_cardapio.id_item_cardapio "
															+ "WHERE restaurante.pedido.id_pedido = ? "
															+"GROUP BY restaurante.pedido.id_pedido "
															+"ORDER BY restaurante.pedido.id_pedido ");
			st.setInt(1, id_pedido);
			ResultSet rs = st.executeQuery();
			ArrayList<String> dados = new ArrayList<String>();
			
			while(rs.next()){
			
				dados.add(""+rs.getInt("id_pedido"));
				dados.add(""+rs.getInt("mesa"));
				dados.add(""+rs.getDate("hora_entrada"));
				dados.add(""+rs.getDate("hora_saida"));
				dados.add(""+rs.getString("status"));
				dados.add(""+rs.getDouble("qtde_itens_pedidos"));
				dados.add(""+rs.getDouble("valor_total"));
				
			}
			conexao.close();
			return dados;

		}catch(SQLException ex){

			System.out.println(ex.getMessage());
			return  new ArrayList<String>();			

		}

	}

	public ArrayList<String> consultarIdPedidoPelaMesa(int mesa){

		try{
			setMesa(mesa);
			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("SELECT "
															+"restaurante.pedido.id_pedido, "
															+"restaurante.pedido.mesa, "
															+"restaurante.pedido.hora_entrada, "
															+"restaurante.pedido.hora_saida, "
															+"CASE restaurante.pedido.status WHEN true THEN 'Aberto' ELSE 'Fechado' END as status, "
															+"COUNT(restaurante.item_pedido.id_pedido) AS qtde_itens_pedidos, "
															+"CONCAT('R$', CAST( REPLACE(restaurante.item_pedido.quantidade * restaurante.item_cardapio.preco,',','.') AS DECIMAL(18,2))) as valor_total "
															+"FROM restaurante.item_pedido RIGHT JOIN restaurante.pedido "
															+"ON restaurante.pedido.id_pedido = restaurante.item_pedido.id_pedido "
															+ "LEFT JOIN restaurante.item_cardapio "
															+ "ON restaurante.item_pedido.id_item_cardapio = restaurante.item_cardapio.id_item_cardapio "
															+ "WHERE restaurante.pedido.mesa = ? "
															+"GROUP BY restaurante.pedido.id_pedido "
															+"ORDER BY restaurante.pedido.id_pedido ");
			st.setInt(1, mesa);
			ResultSet rs = st.executeQuery();
			ArrayList<String> dados = new ArrayList<String>();
			
			while(rs.next()){
			
				dados.add(""+rs.getInt("id_pedido"));
				dados.add(""+rs.getInt("mesa"));
				dados.add(""+rs.getDate("hora_entrada"));
				dados.add(""+rs.getDate("hora_saida"));
				dados.add(""+rs.getString("status"));
				dados.add(""+rs.getDouble("qtde_itens_pedidos"));
				dados.add(""+rs.getString("valor_total"));
				
			}
			conexao.close();
			return dados;

		}catch(SQLException ex){

			System.out.println(ex.getMessage());
			return  new ArrayList<String>();			

		}

	}


	//***********************	ALTERAÇÃO	***********************

	public boolean alterarHoraSaida(int id_pedido){	//Atualizará a data e hora atual do pedido
	
		try{
			setId_pedido(id_pedido);
			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("UPDATE restaurante.pedido "
															+ "SET restaurante.pedido.hora_saida = sysdate() "
															+ "WHERE restaurante.pedido.id_pedido = ?");
			st.setInt(1, id_pedido);
			st.execute();
			conexao.close();
			return true;

		}catch(SQLException ex){

			System.out.println(ex.getMessage());
			return  false;			

		}

	}

	/*//O MÉTODO ABAIXO ESTÁ COMENDADO POR QUE NÃO DEVE SER POSSÍVEL ALTERAR A HORA DE ENTRADA DO CLIENTE, A HORA DE ENTRADA É SEMPRE A DE ABERTURA DO PEDIDO.
	public boolean alterarHoraEntrada(int id_pedido){	//Atualizará a data e hora atual do pedido
	
		try{

			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("UPDATE restaurante.pedido "
															+ "SET restaurante.pedido.hora_entrada = sysdate() "
															+ "WHERE restaurante.pedido.id_pedido = ?");
			st.setInt(1, id_pedido);
			st.execute();
			conexao.close();
			return true;

		}catch(Exception ex){

			ex.printStackTrace();
			return  false;			

		}

	}*/

	public boolean alterarMesa(int id_pedido, int mesa){
	
		try{
			setMesa(mesa);
			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("UPDATE restaurante.pedido "
															+ "SET restaurante.pedido.mesa = ? "
															+ "WHERE restaurante.pedido.id_pedido = ?");
			st.setInt(1, mesa);
			st.setInt(2, id_pedido);
			st.execute();
			conexao.close();
			return true;

		}catch(Exception ex){

			System.out.println(ex.getMessage());
			return  false;			

		}

	}


	public boolean alterarStatus(int id_pedido){	//Alterará o status automaticamente, se o status estiver como "True" o banco colocará como "False", "True" é como se fosse uma pergunta: O pedido está fechado? (True = Sim), (False = Não).
	
		try{
			setId_pedido(id_pedido);
			//1º PASSO - VERIFICA QUAL O STATUS ATUAL E GUARDA O INVERSO PARA ATUALIZAR
			ArrayList<String> dados = consultar(id_pedido); //Pega informações do pedido informado
			boolean status_atual;
			
			if(dados.get(4).equals("Aberto")){ //Verifica se o status atual é disponível ou indisponível e de acordo com a resposta atualizará o status
				
				status_atual = false; 
				
			}else{
				
				status_atual = true;
				
			}
			
			//2º PASSO - ATUALIZA O STATUS NO BANCO DE DADOS
			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("UPDATE restaurante.pedido "
															+ "SET restaurante.pedido.status = ? "
															+ "WHERE restaurante.pedido.id_pedido = ?");
			st.setBoolean(1, status_atual);
			st.setInt(2, id_pedido);
			st.execute();
			conexao.close();
			return true;

		}catch(SQLException ex){

			System.out.println(ex.getMessage());
			return  false;			

		}

	}


	public static int getMesa() {
		return mesa;
	}
	
	public static void setMesa(int mesa) {
		Pedido_DAO.mesa = mesa;
	}
	
	public static int getId_pedido() {
		return id_pedido;
	}
	
	public static void setId_pedido(int id_pedido) {
		Pedido_DAO.id_pedido = id_pedido;
	}
	
	
	static int id_pedido;
	
	public Pedido_DAO(){
		
	}
}
