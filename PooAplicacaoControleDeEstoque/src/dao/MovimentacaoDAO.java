package dao;

import database.ConexaoSQLite;
import model.Movimentacao;
import model.Produto;
import model.Tipo;
import model.Usuario;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimentacaoDAO {

    private static final SimpleDateFormat FORMATADOR_DATA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // Inserir nova movimentação
    public void inserir(Movimentacao movimentacao) {
        String sql = """
            INSERT INTO movimentacao (produto_id, tipo_movimentacao, quantidade, data_movimentacao, usuario_id)
            VALUES (?, ?, ?, ?, ?);
        """;

        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, movimentacao.getProduto().getId());
            pstmt.setString(2, movimentacao.getTipo().name()); // Enum convertido para String
            pstmt.setInt(3, movimentacao.getQuantidade());
            pstmt.setString(4, FORMATADOR_DATA.format(movimentacao.getDataMovimentacao()));
            pstmt.setInt(5, movimentacao.getUsuario().getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir movimentação: " + e.getMessage());
        }
    }

    // Listar todas as movimentações
    public List<Movimentacao> listarTodos() {
        List<Movimentacao> movimentacoes = new ArrayList<>();

        String sql = "SELECT * FROM movimentacao;";

        try (Connection conn = ConexaoSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setId(rs.getInt("id"));

                Produto produto = new Produto();
                produto.setId(rs.getInt("produto_id"));
                movimentacao.setProduto(produto);

                movimentacao.setTipo(Tipo.valueOf(rs.getString("tipo_movimentacao")));
                movimentacao.setQuantidade(rs.getInt("quantidade"));

                Date data = FORMATADOR_DATA.parse(rs.getString("data_movimentacao"));
                movimentacao.setDataMovimentacao(data);

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("usuario_id"));
                movimentacao.setUsuario(usuario);

                movimentacoes.add(movimentacao);
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao listar movimentações: " + e.getMessage());
        }

        return movimentacoes;
    }

    // Buscar uma movimentação por ID
    public Movimentacao buscarPorId(int id) {
        Movimentacao movimentacao = null;
        String sql = "SELECT * FROM movimentacao WHERE id = ?;";

        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                movimentacao = new Movimentacao();
                movimentacao.setId(rs.getInt("id"));

                Produto produto = new Produto();
                produto.setId(rs.getInt("produto_id"));
                movimentacao.setProduto(produto);

                movimentacao.setTipo(Tipo.valueOf(rs.getString("tipo_movimentacao")));
                movimentacao.setQuantidade(rs.getInt("quantidade"));

                Date data = FORMATADOR_DATA.parse(rs.getString("data_movimentacao"));
                movimentacao.setDataMovimentacao(data);

                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("usuario_id"));
                movimentacao.setUsuario(usuario);
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao buscar movimentação: " + e.getMessage());
        }

        return movimentacao;
    }

    // Atualizar uma movimentação (⚠️ normalmente movimentações não são atualizadas, mas vamos permitir aqui)
    public void atualizar(Movimentacao movimentacao) {
        String sql = """
            UPDATE movimentacao
            SET produto_id = ?, tipo_movimentacao = ?, quantidade = ?, data_movimentacao = ?, usuario_id = ?
            WHERE id = ?;
        """;

        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, movimentacao.getProduto().getId());
            pstmt.setString(2, movimentacao.getTipo().name());
            pstmt.setInt(3, movimentacao.getQuantidade());
            pstmt.setString(4, FORMATADOR_DATA.format(movimentacao.getDataMovimentacao()));
            pstmt.setInt(5, movimentacao.getUsuario().getId());
            pstmt.setInt(6, movimentacao.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar movimentação: " + e.getMessage());
        }
    }

    // Deletar uma movimentação
    public void deletar(int id) {
        String sql = "DELETE FROM movimentacao WHERE id = ?;";

        try (Connection conn = ConexaoSQLite.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("❌ Erro ao deletar movimentação: " + e.getMessage());
        }
    }
}