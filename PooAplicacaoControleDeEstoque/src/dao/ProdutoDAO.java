package dao;

import database.ConexaoSQLite;
import model.Produto;

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
            pstmt.setInt(3, produto.getUnidadeDeMedida().getId());
            pstmt.setBigDecimal(4, produto.getPrecoDeCusto());
            pstmt.setBigDecimal(5, produto.getPrecoDeVenda());
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
        String sql = "SELECT * FROM produtos WHERE id = ?";
        Produto produto = null;
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                // Aqui estamos apenas setando os IDs relacionados (não buscando objetos completos)
                produto.setPrecoDeCusto(rs.getBigDecimal("preco_custo"));
                produto.setPrecoDeVenda(rs.getBigDecimal("preco_venda"));
                produto.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                produto.setEstoqueAtual(rs.getInt("estoque_atual"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar produto: " + e.getMessage());
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
                produto.setPrecoDeCusto(rs.getBigDecimal("preco_custo"));
                produto.setPrecoDeCusto(rs.getBigDecimal("preco_venda"));
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
            pstmt.setInt(3, produto.getUnidadeDeMedida().getId());
            pstmt.setBigDecimal(4, produto.getPrecoDeCusto());
            pstmt.setBigDecimal(5, produto.getPrecoDeVenda());
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
