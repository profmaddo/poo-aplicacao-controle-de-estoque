package test;

import dao.UsuarioDAO;
import model.Usuario;

import java.util.List;

public class TesteUsuarioDAO {

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        // Inserir novo usuário
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome("João da Silva");
        novoUsuario.setEmail("joao.silva@email.com");
        novoUsuario.setSenhaHash("123456hash"); // Simulando um hash de senha
        novoUsuario.setTipoUsuario("operador");

        usuarioDAO.inserir(novoUsuario);

        // Listar usuários
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        System.out.println("\n📋 Lista de Usuários:");
        for (Usuario u : usuarios) {
            System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Tipo: " + u.getTipoUsuario());
        }

        // Buscar usuário por ID
        if (!usuarios.isEmpty()) {
            Usuario usuarioEncontrado = usuarioDAO.buscarPorId(usuarios.get(0).getId());
            if (usuarioEncontrado != null) {
                System.out.println("\n🔍 Usuário encontrado: " + usuarioEncontrado.getNome());
            }
        }

        // Atualizar usuário
        if (!usuarios.isEmpty()) {
            Usuario usuarioAtualizar = usuarios.get(0);
            usuarioAtualizar.setNome("João Atualizado");
            usuarioAtualizar.setTipoUsuario("admin");
            usuarioDAO.atualizar(usuarioAtualizar);
            System.out.println("\n✏️ Usuário atualizado para: " + usuarioAtualizar.getNome());
        }

        // Deletar usuário
        if (!usuarios.isEmpty()) {
            usuarioDAO.deletar(usuarios.get(0).getId());
            System.out.println("\n🗑️ Usuário deletado com sucesso.");
        }
    }
}
