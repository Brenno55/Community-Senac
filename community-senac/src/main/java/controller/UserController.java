package controller;

import model.DAO.UserDAO;
import model.User;
import services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//TODO CLASSE RESPONSÁVEL POR TODAS AS FUNÇÕES DE REQUISIÇÕES WEB, QUE CHAMARÁ >>> A CLASSE SERVICE
@WebServlet( urlPatterns = {"/user"})
public class UserController extends HttpServlet {
    public UserDAO repository = new UserDAO();
    public UserService userService = new UserService(repository);
    private final User user = new User();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user.setNome(req.getParameter("nome"));
        user.setEmail(req.getParameter("email"));
        user.setSenha(req.getParameter("senha"));

        boolean userCriado = userService.criarUsuario(user);

        if (!userCriado) {
            req.setAttribute("countV", 1);
            String count = req.getAttribute("countV").toString();
            System.out.println(count + " ->>>>");
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<script>alert('E-mail ja cadastrado já cadastrado!');</script>");
            RequestDispatcher rd = req.getRequestDispatcher("/cadastro.jsp");
            rd.include(req, resp);
        } else {
            HttpSession sessao = req.getSession();
            sessao.setAttribute("sessionUser", user);
            req.getRequestDispatcher("perfil.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        boolean usuarioAutenticado = userService.autenticarUsuario(email, senha);

        User user = userService.buscarUsuarioPorEmail(email);

        if(usuarioAutenticado){
            HttpSession sessao = req.getSession();
            sessao.setAttribute("sessionUser", user);
            resp.sendRedirect("/home");
        } else {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<script>alert('Erro de login!');</script>");
            RequestDispatcher rd = req.getRequestDispatcher("/login.html");
            rd.include(req, resp);
        }
    }
}

