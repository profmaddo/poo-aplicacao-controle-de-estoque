package dao;

import database.ConexaoSQLite;
import model.Categoria;
import model.Fornecedor;
import model.Produto;
import model.UnidadeDeMedida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // Inserir novo produto
    public void inserir(Produto produto) {
        String sql = "INSERT INTO produtos(nome, categoria_id, unidade_medida_id, preco_custo, preco_venda, estoque_minimo, estoque_atual, fornecedor_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getCategoria().getId());
            pstmt.setInt(3, produto.getUnidadeMedida().getId());
            pstmt.setBigDecimal(4, produto.getPrecoCusto());
            pstmt.setBigDecimal(5, produto.getPrecoVenda());
            pstmt.setInt(6, produto.getEstoqueMinimo());
            pstmt.setInt(7, produto.getEstoqueAtual());
            pstmt.setInt(8, produto.getFornecedor().getId());
            pstmt.executeUpdate();
            System.out.println("✅ model.Produto inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir produto: " + e.getMessage());
        }
    }

    // Buscar produto por ID
    public Produto buscarPorId(int id) {

        Produto produto = new Produto();
        Categoria categoria = new Categoria();
        UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();
        Fornecedor fornecedor = new Fornecedor();

        String sql = "SELECT * FROM produtos WHERE id = ?";


        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                System.out.println(rs.getInt("categoria_id"));
                // Aqui estamos apenas setando os IDs relacionados (não buscando objetos completos)
                produto.setPrecoCusto(rs.getBigDecimal("preco_custo"));
                produto.setPrecoVenda(rs.getBigDecimal("preco_venda"));
                produto.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                produto.setEstoqueAtual(rs.getInt("estoque_atual"));
                // Categoria categoria = categoriaDAO.buscarPorId(rs.getInt("categoria_id"));

                sql = "SELECT * FROM categorias WHERE id = ?";

                try {
                    PreparedStatement pstmtCategoria = conn.prepareStatement(sql);
                    pstmtCategoria.setInt(1, rs.getInt("categoria_id"));
                    ResultSet rsCategoria = pstmtCategoria.executeQuery();
                    if (rsCategoria.next()) {
                        categoria = new Categoria();
                        categoria.setId(rsCategoria.getInt("id"));
                        categoria.setNome(rsCategoria.getString("nome"));
                    }
                } catch (SQLException e) {
                    System.out.println("❌ Erro ao buscar categoria: " + e.getMessage());
                }

                // Unidade de Medida
                sql = "SELECT * FROM unidades_medida WHERE id = ?";
                try {
                    PreparedStatement pstmtUnidade = conn.prepareStatement(sql);
                    pstmtUnidade.setInt(1, rs.getInt("unidade_medida_id"));
                    ResultSet rsUnidade = pstmtUnidade.executeQuery();
                    if (rsUnidade.next()) {
                        unidadeDeMedida = new UnidadeDeMedida();
                        unidadeDeMedida.setId(rsUnidade.getInt("id"));
                        unidadeDeMedida.setDescricao(rsUnidade.getString("descricao"));
                    }
                } catch (SQLException e) {
                    System.out.println("❌ Erro ao buscar unidade de medida: " + e.getMessage());
                }

                // Fornecedor do Produto;

                sql = "SELECT * FROM fornecedores WHERE id = ?";
                try {
                    PreparedStatement pstmtFornecedor = conn.prepareStatement(sql);
                    pstmtFornecedor.setInt(1, rs.getInt("fornecedor_id"));
                    ResultSet rsFornecedor = pstmtFornecedor.executeQuery();
                    if (rsFornecedor.next()) {
                        fornecedor.setId(rsFornecedor.getInt("id"));
                        fornecedor.setNome(rsFornecedor.getString("nome"));
                        fornecedor.setCnpj(rsFornecedor.getString("cnpj"));
                        fornecedor.setContato(rsFornecedor.getString("contato"));
                        fornecedor.setEmail(rsFornecedor.getString("email"));
                    }
                } catch (SQLException e) {
                    System.out.println("❌ Erro ao buscar fornecedor: " + e.getMessage());
                }


                produto.setCategoria(categoria);
                produto.setUnidadeMedida(unidadeDeMedida);
                produto.setFornecedor(fornecedor);
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar categoria: " + e.getMessage());
        }
        return produto;
    }


    // Listar todos os produtos
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPrecoCusto(rs.getBigDecimal("preco_custo"));
                produto.setPrecoVenda(rs.getBigDecimal("preco_venda"));
                produto.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                produto.setEstoqueAtual(rs.getInt("estoque_atual"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    // Atualizar produto
    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, categoria_id = ?, unidade_medida_id = ?, preco_custo = ?, preco_venda = ?, "
                + "estoque_minimo = ?, estoque_atual = ?, fornecedor_id = ? WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setInt(2, produto.getCategoria().getId());
            pstmt.setInt(3, produto.getUnidadeMedida().getId());
            pstmt.setBigDecimal(4, produto.getPrecoCusto());
            pstmt.setBigDecimal(5, produto.getPrecoVenda());
            pstmt.setInt(6, produto.getEstoqueMinimo());
            pstmt.setInt(7, produto.getEstoqueAtual());
            pstmt.setInt(8, produto.getFornecedor().getId());
            pstmt.setInt(9, produto.getId());
            pstmt.executeUpdate();
            System.out.println("✅ model.Produto atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // Deletar produto
    public void deletar(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("🗑️ model.Produto deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao deletar produto: " + e.getMessage());
        }
    }
}
