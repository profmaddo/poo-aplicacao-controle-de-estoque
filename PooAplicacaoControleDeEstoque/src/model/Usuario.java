package model;

/**
 * Classe de controle de acesso
 * dos usuários do sistema
 */
public class Usuario {

    //private Integer id;
    // private String nome;
    // private String perfil;

    private Integer id;
    private String nome;
    private String perfil;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
