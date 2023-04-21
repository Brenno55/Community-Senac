package Controllers;

import model.DAO;
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
import java.util.ArrayList;
    @WebServlet( urlPatterns = {"/Controller", "/login", "/sobre", "/index", "/autenticador", "/erroLogin"})
    public class RoutesControllers extends HttpServlet {

        DAO dao = new DAO();
        User user = new User();
        UserService create = new UserService();
        public RoutesControllers(){
            super();
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String action = req.getServletPath();
            System.out.println("1 - chamada na rota: " + action);

            if (action.equals("/autenticador")) {
                HttpSession sessao = req.getSession(false);
                if(sessao!=null){
                    sessao.invalidate();
                }
                System.out.println("sessão falsa");
                resp.sendRedirect("login.html");
            } else if (action.equals("/login")) {
                System.out.println("2 - Redirecionei para a o metodo que faz o login");
                handleLogin(req, resp);
            } else if (action.equals("/sobre")) {
                RequestDispatcher rd = req.getRequestDispatcher("/view/pages/sobre/sobre.html");
                rd.forward(req, resp);
            } else if (action.equals("/index")) {
                System.out.println("2 - Redirecionei para a o metodo que faz a listagem de usuarios");
                listUsers(req,resp);

            } else if (action.equals("/erroLogin")) {
                System.out.println("2 - Redirecionei para pagina de erro");
                resp.sendRedirect("erroLogin.html");
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("index.html");
                rd.forward(req, resp);
            }

        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String semail = request.getParameter("email");
            String ssenha = request.getParameter("senha");

            User usu = new User();
            usu.setEmail(semail);
            usu.setSenha(ssenha);

            UserService usuDAO = new UserService();
            User usuAutenticado = usuDAO.autenticacao(usu);

            if(usuAutenticado != null){
                HttpSession sessao = request.getSession();
                sessao.setAttribute("usuAutenticado", usuAutenticado);
                //sessao.setMaxInactiveInterval(3000);
                //request.getRequestDispatcher("index").forward(request, response);
                System.out.println("usuario authenticado");
                response.sendRedirect("/index");
            }else {
                response.sendRedirect("erroLogin.html");
            }
        }
        protected void handlePerfil(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("3- Entrei na funcão handlePerfil");

            // setar as variaveis do Perfil
            user.setData_nascimento(req.getParameter("data_nascimento"));
            user.setCelular(req.getParameter("celular"));
            user.setcFacul(req.getParameter("cFacul"));
            user.setSexo(req.getParameter("sexo"));
            user.setBio(req.getParameter("bio"));
            System.out.println("4- Setei as variaveis");

            // Invocar o método createUser passando o objt user:
            UserService create = new UserService();

            // VALIDAÇÕES:

            if (!create.procureCelular(user)){
                System.out.println("Celular ja existente");
                resp.sendRedirect("/perfil.html");
            } else {
                create.createUserAux(user); // insert no banco.
                resp.sendRedirect("/home.jsp");
            }




        }

        protected void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("3- Entrei na funcão handleLogin");
            dao.testeConexao();

            // setar as variaveis do usuario
            user.setEmail(req.getParameter("email"));
            user.setSenha(req.getParameter("senha"));
            System.out.println("4- Setei as variaveis");



            // VALIDAÇÕES:
            if (!create.procureEmail(user)){
                System.out .println("Usuario não encontrado!");
                resp.sendRedirect("/login.html");

            } else if (!create.procureSenha(user)) {
                System.out.println("As senhas não conferem");
                resp.sendRedirect("/login.html");
            } else {
                resp.sendRedirect("index");
            }




        }

        protected void listUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            // Criando um obj que ira receber os dados da classe de auxUser:
            // por enquanto como ela não se encontra pronta eu vou usar apenas a classe de user;

            ArrayList<User> lista = create.listarContatos();

            //Encaminhar a lista ao documento home.jsp;

            req.setAttribute("users", lista);
            RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
            rd.forward(req, resp);
        }
    }
