package model;

/**
 * Classe destinada a gerenciar
 * a entrada e saída de produtos
 * do estoque
 */
import java.util.Date;

public class Movimentacao {

    private int id;
    private Produto produto;
    private Tipo tipo;                // Tipo de movimentação (ENTRADA ou SAIDA)
    private int quantidade;
    private Date dataMovimentacao;
    private Usuario usuario;

    // Construtor vazio
    public Movimentacao() {
    }

    // Construtor completo (opcional para criar movimentações rapidamente)
    public Movimentacao(Produto produto, Tipo tipo, int quantidade, Date dataMovimentacao, Usuario usuario) {
        this.produto = produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.dataMovimentacao = dataMovimentacao;
        this.usuario = usuario;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Métodos auxiliares opcionais para melhorar o uso

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id=" + id +
                ", produto=" + (produto != null ? produto.getNome() : "N/A") +
                ", tipo=" + tipo +
                ", quantidade=" + quantidade +
                ", dataMovimentacao=" + dataMovimentacao +
                ", usuario=" + (usuario != null ? usuario.getNome() : "N/A") +
                '}';
    }
}

