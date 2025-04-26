package test;

import dao.FornecedorDAO;
import model.Fornecedor;

import java.util.List;

public class TesteFornecedorDAO {

    public static void main(String[] args) {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();

        // Inserir fornecedor
        Fornecedor novoFornecedor = new Fornecedor();
        novoFornecedor.setNome("Distribuidora Central");
        novoFornecedor.setCnpj("12345678000199");
        novoFornecedor.setContato("Carlos Silva");
        novoFornecedor.setEmail("contato@distribuidoracentral.com");
        fornecedorDAO.inserir(novoFornecedor);

        // Listar fornecedores
        List<Fornecedor> fornecedores = fornecedorDAO.listarTodos();
        System.out.println("\n📋 Lista de Fornecedores:");
        for (Fornecedor f : fornecedores) {
            System.out.println("ID: " + f.getId() + " | Nome: " + f.getNome() + " | CNPJ: " + f.getCnpj());
        }

        // Buscar por ID
        if (!fornecedores.isEmpty()) {
            Fornecedor fornecedorEncontrado = fornecedorDAO.buscarPorId(fornecedores.get(0).getId());
            System.out.println("\n🔍 Fornecedor encontrado: " + fornecedorEncontrado.getNome());
        }

        // Atualizar fornecedor
        if (!fornecedores.isEmpty()) {
            Fornecedor fornecedorAtualizar = fornecedores.get(0);
            fornecedorAtualizar.setNome("Distribuidora Atualizada");
            fornecedorAtualizar.setContato("Ana Paula");
            fornecedorDAO.atualizar(fornecedorAtualizar);
            System.out.println("\n✏️ Fornecedor atualizado para: " + fornecedorAtualizar.getNome());
        }

        // Deletar fornecedor
        if (!fornecedores.isEmpty()) {
            fornecedorDAO.deletar(fornecedores.get(0).getId());
            System.out.println("\n🗑️ Fornecedor deletado com sucesso.");
        }
    }
}
