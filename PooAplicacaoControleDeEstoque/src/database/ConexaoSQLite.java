package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQLite {

    private static final String URL = "jdbc:sqlite:banco_estoque.db";
    private static Connection conexao = null;

    public static Connection conectar() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = DriverManager.getConnection(URL);
                System.out.println("✅ Conexão com SQLite realizada com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar com o banco de dados: " + e.getMessage());
        }
        return conexao;
    }

    public static void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("🔌 Conexão encerrada.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}

