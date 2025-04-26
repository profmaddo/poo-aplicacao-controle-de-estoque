package test;

import dao.MovimentacaoDAO;
import model.Movimentacao;
import model.Produto;
import model.Tipo;
import model.Usuario;

import java.util.Date;
import java.util.List;

public class TesteMovimentacaoDAO {

    public static void main(String[] args) {
        MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();

        // Vamos supor que já existam Produto e Usuário com ID 1
        Produto produto = new Produto();
        produto.setId(1);  // ID do Produto já cadastrado

        Usuario usuario = new Usuario();
        usuario.setId(1);  // ID do Usuário já cadastrado

        // Inserir movimentação (Entrada de estoque)
        Movimentacao novaMovimentacao = new Movimentacao();
        novaMovimentacao.setProduto(produto);
        novaMovimentacao.setTipo(Tipo.ENTRADA);
        novaMovimentacao.setQuantidade(20);
        novaMovimentacao.setDataMovimentacao(new Date()); // data atual
        novaMovimentacao.setUsuario(usuario);

        movimentacaoDAO.inserir(novaMovimentacao);

        // Listar movimentações
        List<Movimentacao> movimentacoes = movimentacaoDAO.listarTodos();
        System.out.println("\n📋 Lista de Movimentações:");
        for (Movimentacao m : movimentacoes) {
            System.out.println("ID: " + m.getId() + " | Tipo: " + m.getTipo() + " | Quantidade: " + m.getQuantidade());
        }

        // Buscar movimentação por ID
        if (!movimentacoes.isEmpty()) {
            Movimentacao movimentacaoEncontrada = movimentacaoDAO.buscarPorId(movimentacoes.get(0).getId());
            if (movimentacaoEncontrada != null) {
                System.out.println("\n🔍 Movimentação encontrada: Tipo " + movimentacaoEncontrada.getTipo() +
                        ", Quantidade: " + movimentacaoEncontrada.getQuantidade());
            }
        }

        // Atualizar movimentação (opcional, mas incluímos para teste de update)
        if (!movimentacoes.isEmpty()) {
            Movimentacao movimentacaoAtualizar = movimentacoes.get(0);
            movimentacaoAtualizar.setQuantidade(30); // ajusta a quantidade
            movimentacaoDAO.atualizar(movimentacaoAtualizar);
            System.out.println("\n✏️ Movimentação atualizada para quantidade: " + movimentacaoAtualizar.getQuantidade());
        }

        // Deletar movimentação
        if (!movimentacoes.isEmpty()) {
            movimentacaoDAO.deletar(movimentacoes.get(0).getId());
            System.out.println("\n🗑️ Movimentação deletada com sucesso.");
        }
    }
}
