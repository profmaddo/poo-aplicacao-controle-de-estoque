import dao.*;
import model.*;

import java.util.Date;
import java.util.Scanner;

public class MenuSistemaDeEstoqueV1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        UnidadeDeMedidaDAO unidadeMedidaDAO = new UnidadeDeMedidaDAO();
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();

        do {
            System.out.println("\n======================================");
            System.out.println("         Sistema de Estoque - V1");
            System.out.println("======================================");
            System.out.println("1 - Gerenciar Categoria");
            System.out.println("2 - Gerenciar Unidade de Medida");
            System.out.println("3 - Gerenciar Produto");
            System.out.println("4 - Gerenciar Fornecedor");
            System.out.println("5 - Gerenciar Usuário");
            System.out.println("6 - Gerenciar Movimentação");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir linha pendente

            switch (opcao) {
                case 1 -> gerenciarCategoria(categoriaDAO, scanner);
                case 2 -> gerenciarUnidadeMedida(unidadeMedidaDAO, scanner);
                case 3 -> gerenciarProduto(produtoDAO, scanner);
                case 4 -> gerenciarFornecedor(fornecedorDAO, scanner);
                case 5 -> gerenciarUsuario(usuarioDAO, scanner);
                case 6 -> gerenciarMovimentacao(movimentacaoDAO, scanner);
                case 0 -> System.out.println("\n🚪 Saindo do sistema...");
                default -> System.out.println("\n⚠️ Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void gerenciarCategoria(CategoriaDAO categoriaDAO, Scanner scanner) {
        System.out.println("\n🎯 Cadastro de Categoria");
        System.out.print("Nome da categoria: ");
        String nome = scanner.nextLine();

        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoriaDAO.inserir(categoria);
    }

    private static void gerenciarUnidadeMedida(UnidadeDeMedidaDAO unidadeMedidaDAO, Scanner scanner) {
        System.out.println("\n🎯 Cadastro de Unidade de Medida");
        System.out.print("Descrição da unidade (ex: Litro, Unidade): ");
        String descricao = scanner.nextLine();

        UnidadeDeMedida unidade = new UnidadeDeMedida();
        unidade.setDescricao(descricao);
        unidadeMedidaDAO.inserir(unidade);
    }

    private static void gerenciarProduto(ProdutoDAO produtoDAO, Scanner scanner) {
        System.out.println("\n🎯 Cadastro de Produto");
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

    private static void gerenciarFornecedor(FornecedorDAO fornecedorDAO, Scanner scanner) {
        System.out.println("\n🎯 Cadastro de Fornecedor");
        Fornecedor fornecedor = new Fornecedor();

        System.out.print("Nome do Fornecedor: ");
        fornecedor.setNome(scanner.nextLine());

        System.out.print("CNPJ: ");
        fornecedor.setCnpj(scanner.nextLine());

        System.out.print("Contato: ");
        fornecedor.setContato(scanner.nextLine());

        System.out.print("Email: ");
        fornecedor.setEmail(scanner.nextLine());

        fornecedorDAO.inserir(fornecedor);
    }

    private static void gerenciarUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
        System.out.println("\n🎯 Cadastro de Usuário");
        Usuario usuario = new Usuario();

        System.out.print("Nome do Usuário: ");
        usuario.setNome(scanner.nextLine());

        System.out.print("Email: ");
        usuario.setEmail(scanner.nextLine());

        System.out.print("Senha (hash simulada): ");
        usuario.setSenhaHash(scanner.nextLine());

        System.out.print("Tipo de Usuário (admin, operador, visualizador): ");
        usuario.setTipoUsuario(scanner.nextLine());

        usuarioDAO.inserir(usuario);
    }

    private static void gerenciarMovimentacao(MovimentacaoDAO movimentacaoDAO, Scanner scanner) {
        System.out.println("\n🎯 Registro de Movimentação de Estoque");
        Movimentacao movimentacao = new Movimentacao();

        System.out.print("ID do Produto: ");
        Produto produto = new Produto();
        produto.setId(scanner.nextInt());
        movimentacao.setProduto(produto);

        System.out.print("Tipo de Movimentação (ENTRADA, SAIDA, AJUSTE): ");
        movimentacao.setTipo(Tipo.valueOf(scanner.next().toUpperCase()));

        System.out.print("Quantidade: ");
        movimentacao.setQuantidade(scanner.nextInt());

        movimentacao.setDataMovimentacao(new Date()); // Define data atual automaticamente

        System.out.print("ID do Usuário: ");
        Usuario usuario = new Usuario();
        usuario.setId(scanner.nextInt());
        movimentacao.setUsuario(usuario);

        movimentacaoDAO.inserir(movimentacao);
    }
}

