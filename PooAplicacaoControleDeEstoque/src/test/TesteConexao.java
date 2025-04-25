package test;

import database.ConexaoSQLite;

public class TesteConexao {
    public static void main(String[] args) {
        ConexaoSQLite.conectar();
        ConexaoSQLite.desconectar();
    }
}
