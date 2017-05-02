package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import service.UsuarioService;

/**
 * Servlet implementation class ManterUsuarioController
 */
@WebServlet("/ManterUsuario.do")
public class ManterUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNome = request.getParameter("nome");
		String pLogin = request.getParameter("login");
		String pSenha = request.getParameter("senha");
		
		//instanciar o javabean
		Usuario Usuario = new Usuario();
		Usuario.setNome(pNome);
		Usuario.setLogin(pLogin);
		Usuario.setSenha(pSenha);
		
		//instanciar o service
		UsuarioService cs = new UsuarioService();
		cs.criar(Usuario);
		Usuario = cs.carregar(Usuario.getId());
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Usuario Cadastrado</title></head><body>");
		out.println(	"id: "+Usuario.getId()+"<br>");
		out.println(	"nome: "+Usuario.getNome()+"<br>");
		out.println(	"perfil: "+Usuario.getPerfil()+"<br>");
		out.println(	"senha: "+Usuario.getSenha()+"<br>");
	    out.println("</body></html>");
		
	}

}
