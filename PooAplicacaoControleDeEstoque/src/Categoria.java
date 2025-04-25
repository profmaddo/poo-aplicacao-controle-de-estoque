import java.util.List;

/**
 * Classe Categoria
 *
 *  id - Integer - PK do banco de dados
 *  nome - String - Nome da categoria
 *  Relacionamento 1:n
 *  Produtos - Lista de produtos (Produto)
 *
 */

public class Categoria {

    private Integer id;
    private String nome;
    List<Produto> produtos;

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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
