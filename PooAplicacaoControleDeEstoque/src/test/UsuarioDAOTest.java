package test;

import dao.UsuarioDAO;
import model.Usuario;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Ordena os testes para CRUD funcionar
public class UsuarioDAOTest {

    private static UsuarioDAO usuarioDAO;
    private static int idCriado;

    @Test
    @Order(1)
    public void testInserirUsuario() {
        usuarioDAO = new UsuarioDAO();

        Usuario usuario = new Usuario();
        usuario.setNome("Teste JUnit");
        usuario.setEmail("teste@junit.com");
        usuario.setSenhaHash("senha123");
        usuario.setTipoUsuario("operador");

        usuarioDAO.inserir(usuario);

        List<Usuario> usuarios = usuarioDAO.listarTodos();
        assertFalse(usuarios.isEmpty(), "A lista de usuários não deveria estar vazia após inserção.");

        // Capturar o último ID criado para usar nos próximos testes
        idCriado = usuarios.get(usuarios.size() - 1).getId();
        assertTrue(idCriado > 0, "ID do usuário deveria ser maior que zero.");
    }

    @Test
    @Order(2)
    public void testBuscarUsuarioPorId() {
        usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.buscarPorId(2);
        assertNotNull(usuario, "Usuário deveria ser encontrado pelo ID.");
        assertEquals("Teste JUnit", usuario.getNome(), "Nome do usuário não bate com o esperado.");
    }

    @Test
    @Order(3)
    public void testAtualizarUsuario() {
        usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.buscarPorId(2);
        usuario.setNome("Teste JUnit Atualizado");
        usuarioDAO.atualizar(usuario);

        Usuario usuarioAtualizado = usuarioDAO.buscarPorId(idCriado);
        assertEquals("Teste JUnit Atualizado", usuarioAtualizado.getNome(), "Nome deveria estar atualizado.");
    }

    @Test
    @Order(4)
    public void testDeletarUsuario() {
        usuarioDAO = new UsuarioDAO();

        usuarioDAO.deletar(idCriado);

        Usuario usuarioDeletado = usuarioDAO.buscarPorId(idCriado);
        assertNull(usuarioDeletado, "Usuário deveria ter sido deletado.");
    }
}
