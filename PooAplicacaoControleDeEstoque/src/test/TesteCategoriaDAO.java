package test;


import dao.CategoriaDAO;
import database.CriarTabelasSQLite;
import model.Categoria;

import java.util.List;

public class TesteCategoriaDAO {

    public static void main(String[] args) {

        CriarTabelasSQLite.criarTabelas();

        CategoriaDAO categoriaDAO = new CategoriaDAO();

        // Inserir categoria
        Categoria novaCategoria = new Categoria();
        novaCategoria.setNome("Bebidas");
        categoriaDAO.inserir(novaCategoria);

        // Listar categorias
        List<Categoria> categorias = categoriaDAO.listarTodos();
        System.out.println("\n📋 Lista de Categorias:");
        for (Categoria c : categorias) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
        }

        // Buscar por ID
        if (!categorias.isEmpty()) {
            Categoria categoriaEncontrada = categoriaDAO.buscarPorId(categorias.get(0).getId());
            System.out.println("\n🔍 Categoria encontrada: " + categoriaEncontrada.getNome());
        }

        // Atualizar categoria
        if (!categorias.isEmpty()) {
            Categoria categoriaAtualizar = categorias.get(0);
            categoriaAtualizar.setNome("Alimentos");
            categoriaDAO.atualizar(categoriaAtualizar);
            System.out.println("\n✏️ Categoria atualizada para: " + categoriaAtualizar.getNome());
        }

        // Deletar categoria
        if (!categorias.isEmpty()) {
            categoriaDAO.deletar(categorias.get(0).getId());
            System.out.println("\n🗑️ Categoria deletada com sucesso.");
        }
    }
}
