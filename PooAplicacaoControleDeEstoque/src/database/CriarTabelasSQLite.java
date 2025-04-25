package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabelasSQLite {

    public static void criarTabelas() {
        Connection conn = ConexaoSQLite.conectar();
        try (Statement stmt = conn.createStatement()) {

            String sqlCategoria = "CREATE TABLE IF NOT EXISTS categorias ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT NOT NULL UNIQUE"
                    + ");";

            String sqlUnidade = "CREATE TABLE IF NOT EXISTS unidades_medida ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "descricao TEXT NOT NULL UNIQUE"
                    + ");";

            String sqlFornecedor = "CREATE TABLE IF NOT EXISTS fornecedores ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT NOT NULL,"
                    + "cnpj TEXT NOT NULL UNIQUE,"
                    + "contato TEXT,"
                    + "email TEXT"
                    + ");";

            String sqlProduto = "CREATE TABLE IF NOT EXISTS produtos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT NOT NULL,"
                    + "categoria_id INTEGER NOT NULL,"
                    + "unidade_medida_id INTEGER NOT NULL,"
                    + "preco_custo REAL NOT NULL,"
                    + "preco_venda REAL NOT NULL,"
                    + "estoque_minimo INTEGER NOT NULL,"
                    + "estoque_atual INTEGER NOT NULL,"
                    + "fornecedor_id INTEGER NOT NULL,"
                    + "FOREIGN KEY (categoria_id) REFERENCES categorias(id),"
                    + "FOREIGN KEY (unidade_medida_id) REFERENCES unidades_medida(id),"
                    + "FOREIGN KEY (fornecedor_id) REFERENCES fornecedores(id)"
                    + ");";

            String sqlUsuario = "CREATE TABLE IF NOT EXISTS usuarios ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT NOT NULL,"
                    + "email TEXT NOT NULL UNIQUE,"
                    + "senha_hash TEXT NOT NULL,"
                    + "tipo_usuario TEXT NOT NULL"
                    + ");";

            String sqlMovimentacao = "CREATE TABLE IF NOT EXISTS movimentacoes ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "produto_id INTEGER NOT NULL,"
                    + "tipo TEXT NOT NULL,"
                    + "quantidade INTEGER NOT NULL,"
                    + "data_movimentacao DATETIME NOT NULL,"
                    + "usuario_id INTEGER NOT NULL,"
                    + "FOREIGN KEY (produto_id) REFERENCES produtos(id),"
                    + "FOREIGN KEY (usuario_id) REFERENCES usuarios(id)"
                    + ");";

            stmt.execute(sqlCategoria);
            stmt.execute(sqlUnidade);
            stmt.execute(sqlFornecedor);
            stmt.execute(sqlProduto);
            stmt.execute(sqlUsuario);
            stmt.execute(sqlMovimentacao);

            System.out.println("🏗️ Todas as tabelas foram criadas com sucesso!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao criar tabelas: " + e.getMessage());
        } finally {
            ConexaoSQLite.desconectar();
        }
    }
}
