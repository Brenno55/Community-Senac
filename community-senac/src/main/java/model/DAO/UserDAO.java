package model.DAO;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {

    private final String DRIVER = "org.h2.Driver";
    private final String URL = "jdbc:h2:~/test";
    private final String USER = "sa";
    private final String PASSWORD = "sa";

    //TODO CLASSE RESPONSÁVEL POR TODAS AS FUNÇÕES PARA MANIPULAR O BANCO DE DADOS (TABELA USUÁRIO)!
    public boolean inserirUsuario(User user){
        String SQL = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";
        System.out.println("entra no insert do usuario");
        try {
            Connection conectar = conectar();
            PreparedStatement pst = conectar.prepareStatement(SQL);
            System.out.println("ele conecta o prepare");

            pst.setString(1, user.getNome());
            System.out.println("ele seta  o nome");
            pst.setString(2, user.getEmail());
            System.out.println(" ele seta o email");
            pst.setString(3, user.getSenha());
            System.out.println("ele seta a senha");
            pst.executeUpdate();

            conectar.close();
            System.out.println("ele insere o usuario");
            return true;
        } catch (Exception e) {
            System.out.println("entra na exceção do insert");
            return false;
        }
    }
    public boolean inserirDetalhesDoUsuario(User user){
        System.out.println("etnrou na dao ");

        String SQL = "UPDATE usuario SET data_nascimento = ?, celular = ?, curso = ?, sexo = ?, bio = ? WHERE email = ?";
        System.out.println("Entrar no UPDATE do usuario");

        try {
            Connection conectar = conectar();
            PreparedStatement pst = conectar.prepareStatement(SQL);
            System.out.println("conecta o prepare");

            pst.setString(1, user.getData_nascimento());
            pst.setString(2, user.getCelular());
            pst.setString(3, user.getCurso());
            pst.setString(4, user.getSexo());
            pst.setString(5, user.getBio());
            pst.setString(6, user.getEmail());
            pst.executeUpdate();

            System.out.println("Atualizou!!");

            conectar.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean buscarPorEmail(String email) {
        String SQL = "SELECT * FROM usuario WHERE email = ?";
        try {
            Connection conectar = conectar();
            PreparedStatement pst = conectar.prepareStatement(SQL);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            conectar.close();

            return rs != null;

        } catch (Exception e) {
            return true;
        }
    }
    public User buscarUsuarioLogado(String email){
        String SQL = "SELECT * FROM usuario WHERE email = (?)";
        User user = new User();
        try {
            Connection conectar = conectar();

            PreparedStatement pst = conectar.prepareStatement(SQL);
            pst.setString(1, email);

            ResultSet rs = pst.executeQuery();
            conectar.close();

            user.setNome(rs.getString("nome"));
            user.setData_nascimento(rs.getString("data_nascimento"));
            user.setSexo(rs.getString("sexo"));
            user.setBio(rs.getString("bio"));
            user.setCelular(rs.getString("celular"));
            user.setCurso(rs.getString("curso"));
            user.setEmail(rs.getString("email"));
            user.setSenha(rs.getString("senha"));
            return user;

        } catch (Exception e) {
            return null;
        }
    }
    public boolean buscarPorEmailSenha(String email, String senha){
        String SQL = "SELECT * FROM usuario WHERE email = (?) AND senha = (?)";

        try {
            Connection conectar = conectar();

            PreparedStatement pst = conectar.prepareStatement(SQL);
            pst.setString(1, email);
            pst.setString(2, senha);

            ResultSet rs = pst.executeQuery();
            conectar.close();

            return rs != null;

        } catch (Exception e) {
            return false;
        }
    }
    public ArrayList<User> buscarPorNome(String nome){
        String SQL = "SELECT * FROM usuario WHERE nome = (?) ORDER BY nome";
        ArrayList<User> users = new ArrayList<>();

        try {
            Connection conectar = conectar();
            PreparedStatement pst = conectar.prepareStatement(SQL);
            pst.setString(1, nome);

            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setNome(rs.getString("nome"));
                user.setData_nascimento(rs.getString("data_nascimento"));
                user.setSexo(rs.getString("sexo"));
                user.setBio(rs.getString("bio"));
                user.setCelular(rs.getString("celular"));
                user.setCurso(rs.getString("curso"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));

                users.add(user);
            }
            conectar.close();
            return users;
        } catch (Exception e) {
            return null;
        }
    }
    public ArrayList<User> buscarPorCurso(String curso){
        String SQL = "SELECT * FROM usuario WHERE curso = (?) ORDER BY nome";
        ArrayList<User> users = new ArrayList<>();

        try {
            Connection conectar = conectar();
            PreparedStatement pst = conectar.prepareStatement(SQL);
            pst.setString(1, curso);

            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setNome(rs.getString("nome"));
                user.setData_nascimento(rs.getString("data_nascimento"));
                user.setSexo(rs.getString("sexo"));
                user.setBio(rs.getString("bio"));
                user.setCelular(rs.getString("celular"));
                user.setCurso(rs.getString("curso"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));

                users.add(user);
            }
            conectar.close();
            return users;
        } catch (Exception e) {
            return null;
        }
    }
    public ArrayList<User> buscarTodos(){
        String SQL = "SELECT * FROM usuario ORDER BY nome";
        ArrayList<User> users = new ArrayList<>();

        try {
            Connection conectar = conectar();
            PreparedStatement pst = conectar.prepareStatement(SQL);

            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setNome(rs.getString("nome"));
                user.setData_nascimento(rs.getString("data_nascimento"));
                user.setSexo(rs.getString("sexo"));
                user.setBio(rs.getString("bio"));
                user.setCelular(rs.getString("celular"));
                user.setCurso(rs.getString("curso"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));

                users.add(user);
            }
            conectar.close();
            return users;
        } catch (Exception e) {
            return null;
        }
    }



    public Connection conectar(){
        Connection con;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Teste de conexão  dentro da dao ok");
            return con;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }


    public String getDRIVER() {
        return DRIVER;
    }
    public String getURL() {
        return URL;
    }
    public String getUSER() {
        return USER;
    }
    public String getPASSWORD() {
        return PASSWORD;
    }
}
