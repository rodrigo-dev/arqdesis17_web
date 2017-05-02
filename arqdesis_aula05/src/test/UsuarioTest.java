package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.Usuario;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioTest {

	static String nome, senha, perfil, novoNome;
	static Usuario usuario;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		
		usuario = new Usuario();
		setNome("Teste_do_Sistema");
		setSenha("ChZYsujz32a");
		setPerfil("Supervisor");
		setNovoNome("Teste_do_Sistema_Novo_Nome");
		usuario.setLogin(getNome());
		usuario.setSenha(getSenha());
		usuario.setPerfil(getPerfil());
		usuario.setLoginNovoUtilizeParaAlterar(getNovoNome());
		usuario.excluir();
		
	}
	
	@Before
	public void setUpBeforeMethod() throws Exception {

		setNome("Teste_do_Sistema");
		setSenha("ChZYsujz32a");
		setPerfil("Supervisor");
		setNovoNome("Teste_do_Sistema_Novo_Nome");
		usuario.setLogin(getNome());
		usuario.setSenha(getSenha());
		usuario.setPerfil(getPerfil());
		usuario.setLoginNovoUtilizeParaAlterar(getNovoNome());
		
	}
	
	
	@Test
	public void test001_cadastroDeUsuario(){
		System.out.println("Usu�rio - Teste 001 - Testando a Inclus�o do usu�rio: "+getNome());
		System.out.println("Usu�rio - Teste 001 - Testando a Inclus�o do usu�rio: "+getNome());
		assertTrue("Testando a Inclus�o do usu�rio: "+getNome(),usuario.criar());
	}
	
	@Test
	public void test002_cadastroDeUsuarioJaExistente(){
		System.out.println("Usu�rio - Teste 002 - Testando a Inclus�o de um usu�rio j� existente: "+getNome());
		assertFalse("Testando a Inclus�o de um usu�rio j� existente: "+getNome(), usuario.criar());
	}
	
	@Test
	public void test003_exclus�oDoUsuarioCriado(){
		System.out.println("Usu�rio - Teste 003 - Testando a exclus�o do usu�rio criado: "+getNome());
		assertTrue("Testando a exclus�o do usu�rio criado: "+getNome(), usuario.excluir());
	}
	
	@Test
	public void test004_cadastroDeUsuarioSemSenha(){
		System.out.println("Usu�rio - Teste 004 - Testando a inclus�o de um usu�rio sem senha.");
		usuario.setSenha(null);
		assertFalse("Testando a inclus�o de um usu�rio sem senha.", usuario.criar());
	}

	@Test
	public void test005_cadastroDeUsuarioSemPerfil(){
		usuario.setPerfil(null);

		System.out.println("Usu�rio - Teste 005 - Testando a inclus�o de um usu�rio sem perfil.");
		
		assertFalse("Testando a inclus�o de um usu�rio sem perfil.", usuario.criar());
	}
	
	@Test
	public void test006_cadastroDeUsuarioSemNome(){
		usuario.setLogin(null);
		
		System.out.println("Usu�rio - Teste 006 - Testando a inclus�o de um usu�rio sem nome.");
		
		assertFalse("Testando a inclus�o de um usu�rio sem nome.", usuario.criar());
	}

	@Test
	public void test007_cadastroDeUsuario(){
		
		System.out.println("Usu�rio - Teste 007 - Testando a Inclus�o do usu�rio (novamente): "+getNome());
		
		assertTrue("Testando a Inclus�o do usu�rio (novamente): "+getNome(), usuario.criar());
	}
	
	@Test
	public void test008_consultaSeDadosForamCadastradosCorretamente(){
		ArrayList<String> dadosParaConferir = new ArrayList<String>();
		dadosParaConferir.add(getNome());
		dadosParaConferir.add(getSenha());
		dadosParaConferir.add(getPerfil());
		
		System.out.println("Usu�rio - Teste 008 - Testando se as informa��es inseridas do usu�rio s�o: "+getNome()+", "+getSenha()+" e "+getPerfil());
		
		assertEquals("Testando se as informa��es inseridas do usu�rio s�o: "+getNome()+", "+getSenha()+" e "+getPerfil(),dadosParaConferir,usuario.consultar());
		
	}
	
	@Test
	public void test009_realizaLoginComDadosCorretos(){
		System.out.println("Usu�rio - Teste 009 - Tentando fazer login com os dados corretos, usu�rio: "+getNome()+" e senha: "+getSenha());
		assertTrue("Tentando fazer login com os dados corretos, usu�rio: "+getNome()+" e senha: "+getSenha(),usuario.logar());
	}
	
	@Test
	public void test010_realizaLoginComUsuarioIncorreto(){
		usuario.setLogin("Teste_Erro_Login_Usuario");
		
		System.out.println("Usu�rio - Teste 010 - Tentando fazer login com o usu�rio incorreto, usu�rio: "+getNome()+" e senha: "+getSenha());
		
		assertFalse("Tentando fazer login com o usu�rio incorreto, usu�rio: "+getNome()+" e senha: "+getSenha(),usuario.logar());
	}
	
	@Test
	public void test011_realizaLoginComSenhaIncorreta(){
		usuario.setSenha("Teste_Erro_Login_Senha");
		
		System.out.println("Usu�rio - Teste 011 - Tentando fazer login com a senha incorreta, usu�rio: "+getNome()+" e senha: "+getSenha());
		
		assertFalse("Tentando fazer login com a senha incorreta, usu�rio: "+getNome()+" e senha: "+getSenha(),usuario.logar());
	}
	
	@Test
	public void test012_alteraNome(){
		usuario.setLoginNovoUtilizeParaAlterar(getNovoNome());		

		System.out.println("Usu�rio - Teste 012 - Testando altera��o do nome do usu�rio "+getNome()+" para "+getNovoNome());
		
		assertTrue("Testando altera��o do nome do usu�rio "+getNome()+" para "+getNovoNome(),usuario.alterarLogin());
	}
	
	@Test
	public void test013_consultaSeDadosForamCadastradosCorretamente(){
		
		ArrayList<String> dadosParaConferir = new ArrayList<String>();
		usuario.setLogin(getNovoNome());
		dadosParaConferir.add(getNovoNome());
		dadosParaConferir.add(getSenha());
		dadosParaConferir.add(getPerfil());
		
		System.out.println("Usu�rio - Teste 013 - Testando se as informa��es inseridas do usu�rio s�o: "+getNovoNome()+", "+getSenha()+" e "+getPerfil());

		assertEquals("Testando se as informa��es inseridas do usu�rio s�o: "+getNovoNome()+", "+getSenha()+" e "+getPerfil(),dadosParaConferir,usuario.consultar());
		
		
	}
	
	@Test
	public void test014_voltaParaONomeAnterior(){
		usuario.setLoginNovoUtilizeParaAlterar(getNome());
		usuario.setLogin(getNovoNome());

		System.out.println("Usu�rio - Teste 014 - Testando altera��o do nome do usu�rio "+getNovoNome()+" para "+getNome());
		
		assertTrue("Testando altera��o do nome do usu�rio "+getNovoNome()+" para "+getNome(),usuario.alterarLogin());
	}
	
	@Test
	public void test015_consultaSeDadosForamCadastradosCorretamente1(){
		ArrayList<String> dadosParaConferir = new ArrayList<String>();
		dadosParaConferir.add(getNome());
		dadosParaConferir.add(getSenha());
		dadosParaConferir.add(getPerfil());

		System.out.println("Usu�rio - Teste 015 - Testando se as informa��es inseridas do usu�rio s�o: "+getNome()+", "+getSenha()+" e "+getPerfil());
		
		assertEquals("Testando se as informa��es inseridas do usu�rio s�o: "+getNome()+", "+getSenha()+" e "+getPerfil(),dadosParaConferir,usuario.consultar());
	}
	
	@Test
	public void test016_alteraNomeParaUmJ�Existente(){
		usuario.setLoginNovoUtilizeParaAlterar(getNome());

		System.out.println("Usu�rio - Teste 016 - Testando altera��o do nome do usu�rio "+getNome()+" para "+getNome());
		
		assertFalse("Testando altera��o do nome do usu�rio "+getNome()+" para "+getNome(),usuario.alterarLogin());
	}
	
	@Test
	public void test017_alteraSenha(){
		usuario.setSenha("Teste_do_Sys");

		System.out.println("Usu�rio - Teste 017 - Testando altera��o da senha do usu�rio "+getNome()+" para "+usuario.getSenha());
		
		assertTrue("Testando altera��o da senha do usu�rio "+getNome()+" para "+usuario.getSenha(),usuario.alterarSenha());
	}
	
	@Test
	public void test018_alteraPerfil(){
		
		usuario.setPerfil("Administrador do Sistema");

		System.out.println("Usu�rio - Teste 018 - Testando altera��o do perfil do usu�rio "+getNome()+" para "+usuario.getPerfil());
		
		assertTrue("Testando altera��o do perfil do usu�rio "+getNome()+" para "+usuario.getPerfil(),usuario.alterarPerfil());
	}
	
	@Test
	public void test019_consultaSeDadosForamCadastradosCorretamente(){
		
		ArrayList<String> dadosParaConferir = new ArrayList<String>();
		dadosParaConferir.add(getNome());
		dadosParaConferir.add("Teste_do_Sys");
		dadosParaConferir.add("Administrador do Sistema");

		System.out.println("Usu�rio - Teste 019 - Testando se as informa��es inseridas do usu�rio s�o: "+getNome()+", "+getSenha()+" e "+getPerfil());
		
		assertEquals("Testando se as informa��es inseridas do usu�rio s�o: "+getNome()+", "+getSenha()+" e "+getPerfil(),dadosParaConferir,usuario.consultar());
	}
	
	@Test
	public void test020_voltaSenha(){

		System.out.println("Usu�rio - Teste 020 - Testando altera��o da senha do usu�rio "+getNome()+" para "+getSenha());

		assertTrue("Testando altera��o da senha do usu�rio "+getNome()+" para "+getSenha(),usuario.alterarSenha());
	}
	
	@Test
	public void test021_voltaPerfil(){

		System.out.println("Usu�rio - Teste 021 - Testando altera��o do perfil do usu�rio "+getNome()+" para "+getPerfil());
		
		assertTrue("Testando altera��o do perfil do usu�rio "+getNome()+" para "+getPerfil(),usuario.alterarPerfil());
	}
	
	@Test
	public void test022_consultaSeDadosForamCadastradosCorretamente(){
		ArrayList<String> dadosParaConferir = new ArrayList<String>();
		dadosParaConferir.add(getNome());
		dadosParaConferir.add(getSenha());
		dadosParaConferir.add(getPerfil());

		System.out.println("Usu�rio - Teste 022 - Testando se as informa��es inseridas do usu�rio s�o: "+getNome()+", "+getSenha()+" e "+getPerfil());
		
		assertEquals("Testando se as informa��es inseridas do usu�rio s�o: "+getNome()+", "+getSenha()+" e "+getPerfil(),dadosParaConferir,usuario.consultar());
	}

	public static String getNome() {
		return nome;
	}

	public static void setNome(String nome) {
		UsuarioTest.nome = nome;
	}

	public static String getSenha() {
		return senha;
	}

	public static void setSenha(String senha) {
		UsuarioTest.senha = senha;
	}

	public static String getPerfil() {
		return perfil;
	}

	public static void setPerfil(String perfil) {
		UsuarioTest.perfil = perfil;
	}

	public static String getNovoNome() {
		return novoNome;
	}

	public static void setNovoNome(String novoNome) {
		UsuarioTest.novoNome = novoNome;
	}

}
