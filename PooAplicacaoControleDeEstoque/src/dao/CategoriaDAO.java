package dao;

import database.ConexaoSQLite;
import model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    // Método para inserir uma nova categoria
    public void inserir(Categoria categoria) {
        String sql = "INSERT INTO categorias(nome) VALUES (?)";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNome());
            pstmt.executeUpdate();
            System.out.println("✅ model.Categoria inserida com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir categoria: " + e.getMessage());
        }
    }

    // Método para buscar uma categoria por ID
    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categorias WHERE id = ?";
        Categoria categoria = null;
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar categoria: " + e.getMessage());
        }
        return categoria;
    }

    // Método para listar todas as categorias
    public List<Categoria> listarTodos() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar categorias: " + e.getMessage());
        }
        return categorias;
    }

    // Método para atualizar uma categoria
    public void atualizar(Categoria categoria) {
        String sql = "UPDATE categorias SET nome = ? WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNome());
            pstmt.setInt(2, categoria.getId());
            pstmt.executeUpdate();
            System.out.println("✅ model.Categoria atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar categoria: " + e.getMessage());
        }
    }

    // Método para deletar uma categoria
    public void deletar(int id) {
        String sql = "DELETE FROM categorias WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("🗑️ model.Categoria deletada com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao deletar categoria: " + e.getMessage());
        }
    }
}
