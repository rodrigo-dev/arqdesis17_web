package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.ConexaoBD;

public class Item_Pedido_DAO {
	
	public Item_Pedido_DAO(){

	}
	
	public boolean cadastrar(int id_pedido, int id_item_cardapio, int quantidade){

		try{

			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("INSERT restaurante.item_pedido (id_pedido, id_item_cardapio, quantidade) "
															+ "VALUES ( ? , ? , ? )");
			st.setInt(1, id_pedido);
			st.setInt(2, id_item_cardapio);
			st.setInt(3, quantidade);
			st.execute();
			conexao.close();
			return true;

		}catch(SQLException ex){

			System.out.println(ex.getMessage());
			return false;

		}
	
	}

	public boolean excluir(int id_item_pedido){

		try{

			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("DELETE FROM restaurante.item_pedido WHERE restaurante.item_pedido.id_item_pedido = ?");
			st.setInt(1, id_item_pedido);
			st.execute();
			conexao.close();
			return true;

		}catch(SQLException ex){
			
			System.out.println(ex.getMessage());
			return false;

		}
	
	}
	
	public boolean excluirTodosOsItensPedidos(int id_pedido){

		try{

			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("DELETE FROM restaurante.item_pedido WHERE restaurante.item_pedido.id_pedido = ?");
			st.setInt(1, id_pedido);
			st.execute();
			conexao.close();
			return true;			

		}catch(SQLException ex){

			System.out.println(ex.getMessage());
			return false;

		}
	
	}

	public ArrayList<String> consultar(int id_item_pedido){

		try{

			Connection conn = new ConexaoBD().getConexao();
			PreparedStatement st = conn.prepareStatement("SELECT "
														+ "restaurante.pedido.mesa, "
														+ "restaurante.item_cardapio.nome, "
														+ "restaurante.item_pedido.quantidade, "
														+ "restaurante.item_cardapio.preco as valor_unitario, "
														+ "restaurante.item_cardapio.preco * restaurante.item_pedido.quantidade as valor_total, "
														+ "CASE restaurante.pedido.status WHEN TRUE THEN 'Aberto' ELSE 'Fechado' END as status_pedido "
														+ "FROM restaurante.item_pedido INNER JOIN restaurante.pedido ON restaurante.item_pedido.id_pedido = restaurante.pedido.id_pedido "
														+ "INNER JOIN restaurante.item_cardapio ON restaurante.item_pedido.id_item_cardapio = restaurante.item_cardapio.id_item_cardapio "
														+ "WHERE restaurante.item_pedido.id_item_pedido = ? ");
			st.setInt(1, id_item_pedido);
			ResultSet rs = st.executeQuery();
			ArrayList<String> dados = new ArrayList<String>();
			while(rs.next()){
				dados.add(""+rs.getInt("mesa"));
				dados.add(rs.getString("nome"));
				dados.add(""+rs.getInt("quantidade"));
				dados.add(""+rs.getDouble("valor_unitario"));
				dados.add(""+rs.getDouble("valor_total"));
				dados.add(""+rs.getString("status_pedido"));
				
			}
			return dados;
		}catch(SQLException ex){

			System.out.println(ex.getMessage());
			return new ArrayList<String>();

		}

	}

	
	public ArrayList<String> consultarTodosOsItensPedidosDeUmPedido(int id_pedido){

		try{

			Connection conn = new ConexaoBD().getConexao();
			PreparedStatement st = conn.prepareStatement("SELECT "
														+ "restaurante.pedido.mesa, "
														+ "restaurante.item_cardapio.nome, "
														+ "restaurante.item_pedido.quantidade, "
														+ "restaurante.item_cardapio.preco as valor_unitario, "
														+ "restaurante.item_cardapio.preco * restaurante.item_pedido.quantidade as valor_total, "
														+ "CASE restaurante.pedido.status WHEN TRUE THEN 'Aberto' ELSE 'Fechado' END as status_pedido "
														+ "FROM restaurante.item_pedido INNER JOIN restaurante.pedido ON restaurante.item_pedido.id_pedido = restaurante.pedido.id_pedido "
														+ "INNER JOIN restaurante.item_cardapio ON restaurante.item_pedido.id_item_cardapio = restaurante.item_cardapio.id_item_cardapio "
														+ "WHERE restaurante.pedido.id_pedido = ?");
			st.setInt(1, id_pedido);
			ResultSet rs = st.executeQuery();
			ArrayList<String> dados = new ArrayList<String>();
			while(rs.next()){
				dados.add(""+rs.getInt("mesa"));
				dados.add(rs.getString("nome"));
				dados.add(""+rs.getInt("quantidade"));
				dados.add(""+rs.getDouble("valor_unitario"));
				dados.add(""+rs.getDouble("valor_total"));
				dados.add(""+rs.getString("status_pedido"));
				
			}
			return dados;
		}catch(SQLException ex){

			System.out.println(ex.getMessage());
			return new ArrayList<String>();

		}

	}

	public boolean alterarQuantidade(int id_item_pedido, int quantidade){

		try{
			
			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("UPDATE restaurante.item_pedido "
															+ "SET restaurante.item_pedido.quantidade = ? "
															+ "WHERE restaurante.item_pedido.id_item_pedido = ?");
			st.setInt(1, quantidade);
			st.setInt(2, id_item_pedido);
			st.execute();
			conexao.close();
			return true;
			
		}catch(SQLException ex){

			System.out.println(ex.getMessage());
			return false;
			
		}

	}

	public boolean alterarIDItemCardapio(int id_item_pedido, int id_item_cardapio){

		try{
			
			Connection conexao = new ConexaoBD().getConexao();
			PreparedStatement st = conexao.prepareStatement("UPDATE restaurante.item_pedido "
															+ "SET restaurante.item_pedido.id_item_cardapio = ? "
															+ "WHERE restaurante.item_pedido.id_item_pedido = ?");
			st.setInt(1, id_item_cardapio);
			st.setInt(2, id_item_pedido);
			st.execute();
			conexao.close();
			return true;
			
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
			return false;
		}
		
	}
}
