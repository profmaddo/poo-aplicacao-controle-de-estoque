package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ZerarTabelasSQLite {

    public static void zerarTabelas() {
        Connection conn = ConexaoSQLite.conectar();
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM movimentacoes;");
            stmt.executeUpdate("DELETE FROM produtos;");
            stmt.executeUpdate("DELETE FROM fornecedores;");
            stmt.executeUpdate("DELETE FROM categorias;");
            stmt.executeUpdate("DELETE FROM unidades_medida;");
            stmt.executeUpdate("DELETE FROM usuarios;");

            System.out.println("🧹 Todas as tabelas foram zeradas com sucesso!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao zerar tabelas: " + e.getMessage());
        } finally {
            ConexaoSQLite.desconectar();
        }
    }
}

