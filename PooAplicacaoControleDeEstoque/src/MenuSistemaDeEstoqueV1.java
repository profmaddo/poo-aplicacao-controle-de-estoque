import dao.*;
import helper.Util;
import model.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MenuSistemaDeEstoqueV1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao;

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        UnidadeDeMedidaDAO UnidadeDeMedidaDAO = new UnidadeDeMedidaDAO();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();

        // 🔒 Tela de login antes de liberar o sistema
        Usuario usuarioLogado = telaLogin(scanner, usuarioDAO);

        do {
            System.out.println("\n======================================");
            System.out.println("         Sistema de Estoque");
            System.out.println("======================================");
            System.out.println("1 - Gerenciar Categoria");
            System.out.println("2 - Gerenciar Unidade de Medida");
            System.out.println("3 - Gerenciar Produto");
            System.out.println("4 - Gerenciar Fornecedor");
            System.out.println("5 - Gerenciar Usuário");
            System.out.println("6 - Gerenciar Movimentação");
            System.out.println("0 - Sair");

            opcao = Util.lerInt(scanner, "Escolha uma Opção: ");
            //opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1 -> submenuCategoria(categoriaDAO, scanner);
                case 2 -> submenuUnidadeMedida(UnidadeDeMedidaDAO, scanner);
                case 3 -> submenuProduto(produtoDAO,categoriaDAO, scanner);
                case 4 -> submenuFornecedor(fornecedorDAO, scanner);
                case 5 -> submenuUsuario(usuarioDAO, scanner);
                case 6 -> submenuMovimentacao(movimentacaoDAO, scanner);
                case 0 -> System.out.println("\n🚪 Saindo do sistema...");
                default -> System.out.println("\n⚠️ Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // SUBMENUS

    private static void submenuCategoria(CategoriaDAO categoriaDAO, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Menu Categoria ===");
            System.out.println("1 - Cadastrar Categoria");
            System.out.println("2 - Listar Categorias");
            System.out.println("3 - Atualizar Categoria");
            System.out.println("4 - Deletar Categoria");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome da categoria: ");
                    String nome = scanner.nextLine();
                    Categoria categoria = new Categoria();
                    categoria.setNome(nome);
                    categoriaDAO.inserir(categoria);
                }
                case 2 -> {
                    List<Categoria> categorias = categoriaDAO.listarTodos();
                    System.out.println("\n📋 Lista de Categorias:");
                    for (Categoria c : categorias) {
                        System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome());
                    }
                }
                case 3 -> {
                    System.out.print("ID da categoria para atualizar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Categoria categoria = categoriaDAO.buscarPorId(id);
                    if (categoria != null) {
                        System.out.print("Novo nome da categoria: ");
                        String novoNome = scanner.nextLine();
                        categoria.setNome(novoNome);
                        categoriaDAO.atualizar(categoria);
                    } else {
                        System.out.println("⚠️ Categoria não encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("ID da categoria para deletar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    categoriaDAO.deletar(id);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("⚠️ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void submenuUnidadeMedida(UnidadeDeMedidaDAO unidadeMedidaDAO, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Menu Unidade de Medida ===");
            System.out.println("1 - Cadastrar Unidade de Medida");
            System.out.println("2 - Listar Unidades de Medida");
            System.out.println("3 - Atualizar Unidade de Medida");
            System.out.println("4 - Deletar Unidade de Medida");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Descrição da unidade: ");
                    UnidadeDeMedida unidade = new UnidadeDeMedida();
                    unidade.setDescricao(scanner.nextLine());
                    unidadeMedidaDAO.inserir(unidade);
                }
                case 2 -> {
                    List<UnidadeDeMedida> unidades = unidadeMedidaDAO.listarTodos();
                    System.out.println("\n📋 Lista de Unidades:");
                    for (UnidadeDeMedida u : unidades) {
                        System.out.println("ID: " + u.getId() + " | Descrição: " + u.getDescricao());
                    }
                }
                case 3 -> {
                    System.out.print("ID da unidade para atualizar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    UnidadeDeMedida unidade = unidadeMedidaDAO.buscarPorId(id);
                    if (unidade != null) {
                        System.out.print("Nova descrição da unidade: ");
                        unidade.setDescricao(scanner.nextLine());
                        unidadeMedidaDAO.atualizar(unidade);
                    } else {
                        System.out.println("⚠️ Unidade de medida não encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("ID da unidade para deletar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    unidadeMedidaDAO.deletar(id);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("⚠️ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void submenuProduto(ProdutoDAO produtoDAO, CategoriaDAO categoriaDAO, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Menu Produto ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Atualizar Produto");
            System.out.println("4 - Deletar Produto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Produto produto = new Produto();
                    System.out.print("Nome do Produto: ");
                    produto.setNome(scanner.nextLine());

                    System.out.print("ID da Categoria: ");
                    Categoria categoria = new Categoria();
                    categoria.setId(scanner.nextInt());
                    produto.setCategoria(categoria);

                    System.out.print("ID da Unidade de Medida: ");
                    UnidadeDeMedida unidade = new UnidadeDeMedida();
                    unidade.setId(scanner.nextInt());
                    produto.setUnidadeMedida(unidade);

                     System.out.print("Preço de Custo: ");
                     produto.setPrecoCusto(scanner.nextBigDecimal());
                  //  produto.setPrecoCusto(lerBigDecimal(scanner,"Preço de Custo: "));

                    System.out.print("Preço de Venda: ");
                    produto.setPrecoVenda(scanner.nextBigDecimal());

                    System.out.print("Estoque Mínimo: ");
                    produto.setEstoqueMinimo(scanner.nextInt());

                    System.out.print("Estoque Atual: ");
                    produto.setEstoqueAtual(scanner.nextInt());

                    System.out.print("ID do Fornecedor: ");
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setId(scanner.nextInt());
                    produto.setFornecedor(fornecedor);

                    produtoDAO.inserir(produto);
                }
                case 2 -> {
                    List<Produto> produtos = produtoDAO.listarTodos();
                    System.out.println("\n📋 Lista de Produtos:");
                    for (Produto p : produtos) {
                        System.out.println("ID: " + p.getId() + " | Nome: " + p.getNome() + " | Preço Venda: R$" + p.getPrecoVenda());
                    }
                }
                case 3 -> {
                    System.out.print("ID do produto para atualizar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Produto produto = produtoDAO.buscarPorId(id);
                    if (produto != null) {

                        System.out.print("Novo nome do produto: ");
                        produto.setNome(scanner.nextLine());

                        System.out.print("Novo preço de venda: ");
                        produto.setPrecoVenda(scanner.nextBigDecimal());
                        produtoDAO.atualizar(produto);
                    } else {
                        System.out.println("⚠️ Produto não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do produto para deletar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    produtoDAO.deletar(id);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("⚠️ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void submenuFornecedor(FornecedorDAO fornecedorDAO, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Menu Fornecedor ===");
            System.out.println("1 - Cadastrar Fornecedor");
            System.out.println("2 - Listar Fornecedores");
            System.out.println("3 - Atualizar Fornecedor");
            System.out.println("4 - Deletar Fornecedor");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Fornecedor fornecedor = new Fornecedor();
                    System.out.print("Nome: ");
                    fornecedor.setNome(scanner.nextLine());
                    System.out.print("CNPJ: ");
                    fornecedor.setCnpj(scanner.nextLine());
                    System.out.print("Contato: ");
                    fornecedor.setContato(scanner.nextLine());
                    System.out.print("Email: ");
                    fornecedor.setEmail(scanner.nextLine());
                    fornecedorDAO.inserir(fornecedor);
                }
                case 2 -> {
                    List<Fornecedor> fornecedores = fornecedorDAO.listarTodos();
                    System.out.println("\n📋 Lista de Fornecedores:");
                    for (Fornecedor f : fornecedores) {
                        System.out.println("ID: " + f.getId() + " | Nome: " + f.getNome() + " | CNPJ: " + f.getCnpj());
                    }
                }
                case 3 -> {
                    System.out.print("ID do fornecedor para atualizar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Fornecedor fornecedor = fornecedorDAO.buscarPorId(id);
                    if (fornecedor != null) {
                        System.out.print("Novo nome do fornecedor: ");
                        fornecedor.setNome(scanner.nextLine());
                        fornecedorDAO.atualizar(fornecedor);
                    } else {
                        System.out.println("⚠️ Fornecedor não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do fornecedor para deletar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    fornecedorDAO.deletar(id);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("⚠️ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void submenuUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Menu Usuário ===");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Listar Usuários");
            System.out.println("3 - Atualizar Usuário");
            System.out.println("4 - Deletar Usuário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Usuario usuario = new Usuario();
                    System.out.print("Nome: ");
                    usuario.setNome(scanner.nextLine());
                    System.out.print("Email: ");
                    usuario.setEmail(scanner.nextLine());
                    System.out.print("Senha (hash simulada): ");
                    usuario.setSenhaHash(scanner.nextLine());
                    System.out.print("Tipo de Usuário (admin, operador, visualizador): ");
                    usuario.setTipoUsuario(scanner.nextLine());
                    usuarioDAO.inserir(usuario);
                }
                case 2 -> {
                    List<Usuario> usuarios = usuarioDAO.listarTodos();
                    System.out.println("\n📋 Lista de Usuários:");
                    for (Usuario u : usuarios) {
                        System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Tipo: " + u.getTipoUsuario());
                    }
                }
                case 3 -> {
                    System.out.print("ID do usuário para atualizar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Usuario usuario = usuarioDAO.buscarPorId(id);
                    if (usuario != null) {
                        System.out.print("Novo nome do usuário: ");
                        usuario.setNome(scanner.nextLine());
                        usuarioDAO.atualizar(usuario);
                    } else {
                        System.out.println("⚠️ Usuário não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do usuário para deletar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    usuarioDAO.deletar(id);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("⚠️ Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void submenuMovimentacao(MovimentacaoDAO movimentacaoDAO, Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Menu Movimentação ===");
            System.out.println("1 - Registrar Movimentação");
            System.out.println("2 - Listar Movimentações");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    Movimentacao movimentacao = new Movimentacao();
                    System.out.print("ID do Produto: ");
                    Produto produto = new Produto();
                    produto.setId(scanner.nextInt());
                    movimentacao.setProduto(produto);

                    System.out.print("Tipo de Movimentação (ENTRADA, SAIDA, AJUSTE): ");
                    movimentacao.setTipo(Tipo.valueOf(scanner.next().toUpperCase()));

                    System.out.print("Quantidade: ");
                    movimentacao.setQuantidade(scanner.nextInt());

                    movimentacao.setDataMovimentacao(new Date());

                    System.out.print("ID do Usuário: ");
                    Usuario usuario = new Usuario();
                    usuario.setId(scanner.nextInt());
                    movimentacao.setUsuario(usuario);

                    movimentacaoDAO.inserir(movimentacao);
                }
                case 2 -> {
                    List<Movimentacao> movimentacoes = movimentacaoDAO.listarTodos();
                    System.out.println("\n📋 Lista de Movimentações:");
                    for (Movimentacao m : movimentacoes) {
                        System.out.println("ID: " + m.getId() + " | Tipo: " + m.getTipo() + " | Quantidade: " + m.getQuantidade());
                    }
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("⚠️ Opção inválida.");
            }
        } while (opcao != 0);
    }


    private static Usuario telaLogin(Scanner scanner, UsuarioDAO usuarioDAO) {
        System.out.println("\n🔒 Tela de Login - Sistema de Estoque 🔒");

        Usuario usuarioAutenticado = null;

        while (usuarioAutenticado == null) {
            String email = Util.lerString(scanner, "Email: ");
            String senha = Util.lerString(scanner, "Senha: ");

            // Buscar o usuário no banco de dados pelo email
            List<Usuario> usuarios = usuarioDAO.listarTodos();
            for (Usuario u : usuarios) {
                if (u.getEmail().equalsIgnoreCase(email) && u.getSenhaHash().equals(senha)) {
                    usuarioAutenticado = u;
                    break;
                }
            }

            if (usuarioAutenticado == null) {
                System.out.println("⚠️ Login ou senha inválidos. Tente novamente.\n");
            }
        }

        System.out.println("\n✅ Login efetuado com sucesso! Bem-vindo, " + usuarioAutenticado.getNome() + ".");
        return usuarioAutenticado;
    }


}
