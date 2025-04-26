package test;

import dao.UnidadeDeMedidaDAO;
import model.UnidadeDeMedida;

import java.util.List;

public class TesteUnidadeMedidaDAO {

    public static void main(String[] args) {
        UnidadeDeMedidaDAO unidadeDAO = new UnidadeDeMedidaDAO();

        // Inserir unidade de medida
        UnidadeDeMedida novaUnidade = new UnidadeDeMedida();
        novaUnidade.setDescricao("Litro");
        unidadeDAO.inserir(novaUnidade);

        // Listar unidades de medida
        List<UnidadeDeMedida> unidades = unidadeDAO.listarTodos();
        System.out.println("\n📋 Lista de Unidades de Medida:");
        for (UnidadeDeMedida u : unidades) {
            System.out.println("ID: " + u.getId() + " | Descrição: " + u.getDescricao());
        }

        // Buscar por ID
        if (!unidades.isEmpty()) {
            UnidadeDeMedida unidadeEncontrada = unidadeDAO.buscarPorId(unidades.get(0).getId());
            System.out.println("\n🔍 Unidade encontrada: " + unidadeEncontrada.getDescricao());
        }

        // Atualizar unidade de medida
        if (!unidades.isEmpty()) {
            UnidadeDeMedida unidadeAtualizar = unidades.get(0);
            unidadeAtualizar.setDescricao("Kilograma");
            unidadeDAO.atualizar(unidadeAtualizar);
            System.out.println("\n✏️ Unidade atualizada para: " + unidadeAtualizar.getDescricao());
        }

        // Deletar unidade de medida
        if (!unidades.isEmpty()) {
            unidadeDAO.deletar(unidades.get(0).getId());
            System.out.println("\n🗑️ Unidade de medida deletada com sucesso.");
        }
    }
}