package test;

import dao.ProdutoDAO;
import model.Categoria;
import model.Fornecedor;
import model.Produto;
import model.UnidadeDeMedida;

import java.math.BigDecimal;
import java.util.List;

public class TesteProdutoDAO {

    public static void main(String[] args) {
        ProdutoDAO produtoDAO = new ProdutoDAO();

        // Vamos supor que já existam Categoria, UnidadeMedida e Fornecedor com ID 1
        Categoria categoria = new Categoria();
        categoria.setId(1);  // ID da Categoria existente no banco

        UnidadeDeMedida unidadeMedida = new UnidadeDeMedida();
        unidadeMedida.setId(1);  // ID da Unidade de Medida existente

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(1);  // ID do Fornecedor existente

        // Inserir produto
        Produto novoProduto = new Produto();
        novoProduto.setNome("Refrigerante 2L");
        novoProduto.setCategoria(categoria);
        novoProduto.setUnidadeMedida(unidadeMedida);
        novoProduto.setPrecoCusto(new BigDecimal("3.50"));
        novoProduto.setPrecoVenda(new BigDecimal("5.99"));
        novoProduto.setEstoqueMinimo(10);
        novoProduto.setEstoqueAtual(50);
        novoProduto.setFornecedor(fornecedor);

        produtoDAO.inserir(novoProduto);

        // Listar produtos
        List<Produto> produtos = produtoDAO.listarTodos();
        System.out.println("\n📋 Lista de Produtos:");
        for (Produto p : produtos) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Preço Venda: R$" + p.getPrecoVenda());
        }

        // Buscar por ID
        if (!produtos.isEmpty()) {
            Produto produtoEncontrado = produtoDAO.buscarPorId(produtos.get(0).getId());
            if (produtoEncontrado != null) {
                System.out.println("\n🔍 Produto encontrado: " + produtoEncontrado.getNome());
            }
        }

        // Atualizar produto
        if (!produtos.isEmpty()) {
            Produto produtoAtualizar = produtos.get(0);
            produtoAtualizar.setNome("Refrigerante 1.5L");
            produtoAtualizar.setPrecoVenda(new BigDecimal("4.99"));
            produtoAtualizar.setCategoria(categoria);
            produtoAtualizar.setUnidadeMedida(unidadeMedida);
            produtoAtualizar.setFornecedor(fornecedor);
            produtoDAO.atualizar(produtoAtualizar);
            System.out.println("\n✏️ Produto atualizado para: " + produtoAtualizar.getNome());
        }

        // Deletar produto
        if (!produtos.isEmpty()) {
            produtoDAO.deletar(produtos.get(0).getId());
            System.out.println("\n🗑️ Produto deletado com sucesso.");
        }
    }
}
