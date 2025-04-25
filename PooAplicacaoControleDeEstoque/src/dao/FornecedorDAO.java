package dao;

import database.ConexaoSQLite;
import model.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    // Inserir novo fornecedor
    public void inserir(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedores(nome, cnpj, contato, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fornecedor.getNome());
            pstmt.setString(2, fornecedor.getCnpj());
            pstmt.setString(3, fornecedor.getContato());
            pstmt.setString(4, fornecedor.getEmail());
            pstmt.executeUpdate();
            System.out.println("✅ model.Fornecedor inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir fornecedor: " + e.getMessage());
        }
    }

    // Buscar fornecedor por ID
    public Fornecedor buscarPorId(int id) {
        String sql = "SELECT * FROM fornecedores WHERE id = ?";
        Fornecedor fornecedor = null;
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setContato(rs.getString("contato"));
                fornecedor.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar fornecedor: " + e.getMessage());
        }
        return fornecedor;
    }

    // Listar todos os fornecedores
    public List<Fornecedor> listarTodos() {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM fornecedores";
        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getInt("id"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setContato(rs.getString("contato"));
                fornecedor.setEmail(rs.getString("email"));
                lista.add(fornecedor);
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar fornecedores: " + e.getMessage());
        }
        return lista;
    }

    // Atualizar fornecedor
    public void atualizar(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedores SET nome = ?, cnpj = ?, contato = ?, email = ? WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fornecedor.getNome());
            pstmt.setString(2, fornecedor.getCnpj());
            pstmt.setString(3, fornecedor.getContato());
            pstmt.setString(4, fornecedor.getEmail());
            pstmt.setInt(5, fornecedor.getId());
            pstmt.executeUpdate();
            System.out.println("✅ model.Fornecedor atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar fornecedor: " + e.getMessage());
        }
    }

    // Deletar fornecedor
    public void deletar(int id) {
        String sql = "DELETE FROM fornecedores WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("🗑️ model.Fornecedor deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao deletar fornecedor: " + e.getMessage());
        }
    }
}

