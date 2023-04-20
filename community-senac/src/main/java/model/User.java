package model;

public class User {
    private String nome;
    private String email;
    private String senha;

    private String data_nascimento;
    private String celular;
    private String cFacul;
    private String sexo;
    private String bio;



    public User() {

    }

    public User(String nome, String email, String senha, String data_nascimento, String celular, String cFacul, String sexo, String bio) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.data_nascimento = data_nascimento;
        this.celular = celular;
        this.cFacul = cFacul;
        this.sexo = sexo;
        this.bio = bio;
    }

    public User(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public User(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getData_nascimento() {return data_nascimento;}

    public void setData_nascimento(String data_nascimento) {this.data_nascimento = data_nascimento;}

    public String getCelular() {return celular;}

    public void setCelular(String celular) {this.celular = celular;}

    public String getcFacul() {return cFacul;}

    public void setcFacul(String cFacul) {this.cFacul = cFacul;}

    public String getSexo() {return sexo;}

    public void setSexo(String sexo) {this.sexo = sexo;}

    public String getBio() {return bio;}

    public void setBio(String bio) {this.bio = bio;}
}
