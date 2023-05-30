package model.repositories;

import model.User;
import java.util.ArrayList;

public interface RepositoryDao {
    public ArrayList<User> buscarTodos(String meuEmail);
    public ArrayList<User> buscarPorCurso(String meuEmail, String curso);
    public ArrayList<User> buscarPorNome(String meuEmail, String nome);
    public boolean buscarPorEmailSenha(String email, String senha);
    public User buscarUsuarioPorEmail(String email);

    boolean ExcluindoUsuario(String email);

    public boolean buscarPorEmail(String email);
    public boolean inserirDetalhesDoUsuario(User user);
    public boolean inserirUsuario(User user);
}
