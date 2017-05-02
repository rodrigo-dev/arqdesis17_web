package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
        Usuario usuario = new Usuario();
        usuario.setNome(pNome);
        usuario.setLogin(pLogin);
        usuario.setSenha(pSenha);
        
        //instanciar o service
        UsuarioService cs = new UsuarioService();
        cs.criar(usuario);
        usuario = cs.carregar(usuario.getId());
        
        //enviar para o jsp
        request.setAttribute("usuario", usuario);
        
        RequestDispatcher view = 
        request.getRequestDispatcher("Usuario.jsp");
        view.forward(request, response);
        
    }
    
}
