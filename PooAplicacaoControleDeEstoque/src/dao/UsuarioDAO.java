package dao;

import database.ConexaoSQLite;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Inserir novo usuário
    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nome, email, senha_hash, tipo_usuario) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenhaHash());
            pstmt.setString(4, usuario.getTipoUsuario());
            pstmt.executeUpdate();
            System.out.println("✅ Usuário inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir usuário: " + e.getMessage());
        }
    }

    // Buscar usuário por ID
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenhaHash(rs.getString("senha_hash"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar usuário: " + e.getMessage());
        }
        return usuario;
    }

    // Listar todos os usuários
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenhaHash(rs.getString("senha_hash"));
                usuario.setTipoUsuario(rs.getString("tipo_usuario"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // Atualizar usuário
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, senha_hash = ?, tipo_usuario = ? WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getEmail());
            pstmt.setString(3, usuario.getSenhaHash());
            pstmt.setString(4, usuario.getTipoUsuario());
            pstmt.setInt(5, usuario.getId());
            pstmt.executeUpdate();
            System.out.println("✅ Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // Deletar usuário
    public void deletar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("🗑️ Usuário deletado com sucesso!");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
