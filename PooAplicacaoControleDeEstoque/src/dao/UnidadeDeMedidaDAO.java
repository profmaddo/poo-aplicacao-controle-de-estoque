package dao;

import database.ConexaoSQLite;
import model.UnidadeDeMedida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnidadeDeMedidaDAO {

    // Método para inserir uma nova unidade de medida
    public void inserir(UnidadeDeMedida unidade) {
        String sql = "INSERT INTO unidades_medida(descricao) VALUES (?)";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, unidade.getDescricao());
            pstmt.executeUpdate();
            System.out.println("✅ Unidade de medida inserida com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir unidade de medida: " + e.getMessage());
        }
    }

    // Método para buscar uma unidade de medida por ID
    public UnidadeDeMedida buscarPorId(int id) {
        String sql = "SELECT * FROM unidades_medida WHERE id = ?";
        UnidadeDeMedida unidade = null;
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                unidade = new UnidadeDeMedida();
                unidade.setId(rs.getInt("id"));
                unidade.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar unidade de medida: " + e.getMessage());
        }
        return unidade;
    }

    // Método para listar todas as unidades de medida
    public List<UnidadeDeMedida> listarTodos() {
        List<UnidadeDeMedida> unidades = new ArrayList<>();
        String sql = "SELECT * FROM unidades_medida";
        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                UnidadeDeMedida unidade = new UnidadeDeMedida();
                unidade.setId(rs.getInt("id"));
                unidade.setDescricao(rs.getString("descricao"));
                unidades.add(unidade);
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar unidades de medida: " + e.getMessage());
        }
        return unidades;
    }

    // Método para atualizar uma unidade de medida
    public void atualizar(UnidadeDeMedida unidade) {
        String sql = "UPDATE unidades_medida SET descricao = ? WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, unidade.getDescricao());
            pstmt.setInt(2, unidade.getId());
            pstmt.executeUpdate();
            System.out.println("✅ Unidade de medida atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar unidade de medida: " + e.getMessage());
        }
    }

    // Método para deletar uma unidade de medida
    public void deletar(int id) {
        String sql = "DELETE FROM unidades_medida WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("🗑️ Unidade de medida deletada com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao deletar unidade de medida: " + e.getMessage());
        }
    }
}