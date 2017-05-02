package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioDAO {
	String nome, nomeNovoUtilizeParaAlterar, senha, perfil;

	public UsuarioDAO() {
		setNome(null);
		setSenha(null);
		setPerfil(null);
		setnomeNovoUtilizeParaAlterar(null);
	}

	public int criar(Usuario usuario) {

		String sqlInsert = "INSERT INTO restaurante.usuario(nome, senha, perfil) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, getNome());
			stm.setString(2, getSenha());
			stm.setString(3, getPerfil());
			stm.execute();

			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					usuario.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return usuario.getId();
	}


	public void atualizar(Usuario usuario) {
		String sqlUpdate = "UPDATE restaurante.usuario SET usuario.nome = ?, senha=?, perfil=?  WHERE id=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getSenha());
			stm.setString(3, usuario.getPerfil());
			stm.setInt(4, usuario.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM restaurante.usuario WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Usuario carregar(int id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		String sqlSelect = "SELECT nome, perfil, senha FROM restaurante.usuario WHERE usuario.id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, usuario.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario.setNome(rs.getString("nome"));
					usuario.setPerfil(rs.getString("perfil"));
					usuario.setSenha(rs.getString("senha"));
				} else {
					usuario.setId(-1);
					usuario.setNome(null);
					usuario.setPerfil(null);
					usuario.setSenha(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return usuario;
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
