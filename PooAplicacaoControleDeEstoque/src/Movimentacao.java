import model.Produto;
import model.Usuario;

/**
 * Classe destinada a gerenciar
 * a entrada e saída de produtos
 * do estoque
 */
public class Movimentacao {

    private Integer id;
    private Produto produto;
    // 0 = entrada
    // 1 = saída
    // enum
    private int tipoDeMovimentacao;
    private Integer quantidade;
    private String data;
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getTipoDeMovimentacao() {
        return tipoDeMovimentacao;
    }

    public void setTipoDeMovimentacao(int tipoDeMovimentacao) {
        this.tipoDeMovimentacao = tipoDeMovimentacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
