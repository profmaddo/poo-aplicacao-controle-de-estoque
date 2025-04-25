
import java.util.ArrayList;
import java.util.List;

/**
 * Esta aplicação tem como objetivo
 * manter o cadastro de categoria do
 * sistema de controle de estoque
 *
 * Incluir ok
 * Listar ok
 * Deletar ok
 * Alterar
 * Pesquisa
 *
 */
public class ManterCategoria {

    public static void main(String[] args) {
        // MENU PRINCIPAL
         //incluirCategoria();
         //listarCategorias();
         //deletarCategoria();
         //alterarCategoria();
        pesquisarCategoria();

    }

    private static void pesquisarCategoria() {

        List<Categoria> categorias = new ArrayList<>();

        Categoria novoObjeto = new Categoria();
        novoObjeto.setId(1);
        novoObjeto.setNome("Eletrônicos");

        categorias.add(novoObjeto);

        Categoria outraObjeto = new Categoria();
        outraObjeto.setId(2);
        outraObjeto.setNome("Roupas");

        categorias.add(outraObjeto);

        Categoria maisUmObjeto = new Categoria();
        maisUmObjeto.setId(3);
        maisUmObjeto.setNome("Calçados");

        categorias.add(maisUmObjeto);

        Categoria maisUmNovoObjeto = new Categoria();
        maisUmNovoObjeto.setId(4);
        maisUmNovoObjeto.setNome("Brinquedos");

        categorias.add(maisUmNovoObjeto);

        int idDePesquisa = 0;


        Categoria pesquisada = categorias.get(idDePesquisa);


/*        System.out.println("*** PESQUISA DE CATEGORIA ***");
        System.out.println("ID: (CODIGO DA CATEGORIA)   :" + categorias.get(idDePesquisa).getId());
        System.out.println("Nome:                       :" + categorias.get(idDePesquisa).getNome());


        System.out.println("*** PESQUISA DE CATEGORIA ***");
        System.out.println("ID: (CODIGO DA CATEGORIA)   :" + pesquisada.getId());
        System.out.println("Nome:                       :" + pesquisada.getNome());*/

        imprimirDados(pesquisada);



    }

    private static void alterarCategoria() {

        Categoria novoObjeto =  new Categoria();
        novoObjeto.setId(1);
        novoObjeto.setNome("Eletrônicos");

        System.out.println("*** INCLUSÃO DE NOVA CATEGORIA ***");
        System.out.println("ID: (CODIGO DA CATEGORIA)   :" + novoObjeto.getId());
        System.out.println("Nome:                       :" + novoObjeto.getNome());

        novoObjeto.setNome("Eletrônicos Importados");

        System.out.println("*** ALTERAÇÃO DE CATEGORIA ***");
        System.out.println("ID: (CODIGO DA CATEGORIA)   :" + novoObjeto.getId());
        System.out.println("Nome:                       :" + novoObjeto.getNome());

    }

    private static void deletarCategoria() {

        List<Categoria> categorias = new ArrayList<>();

        Categoria categoriaEletronicos = new Categoria();
        categoriaEletronicos.setId(1);
        categoriaEletronicos.setNome("Eletrônicos");

        categorias.add(categoriaEletronicos);

        Categoria categoriaRoupas = new Categoria();
        categoriaRoupas.setId(2);
        categoriaRoupas.setNome("Roupas");

        categorias.add(categoriaRoupas);

        Categoria categoriaCalcados = new Categoria();
        categoriaCalcados.setId(3);
        categoriaCalcados.setNome("Calçados");

        categorias.add(categoriaCalcados);

        Categoria categoriaBrinquedos = new Categoria();
        categoriaBrinquedos.setId(4);
        categoriaBrinquedos.setNome("Brinquedos");

        categorias.add(categoriaBrinquedos);

        categorias.remove(categoriaEletronicos);
       // categorias.remove(categoriaCalcados);
       // categorias.remove(categoriaRoupas);
      //  categorias.remove(categoriaBrinquedos);

        for (int i = 0; i < categorias.size(); i++) {

         //   System.out.println("*** INCLUSÃO DE NOVA CATEGORIA ***");
         //   System.out.println("ID: (CODIGO DA CATEGORIA)   :"+categorias.get(i).getId());
          //  System.out.println("Nome:                       :"+categorias.get(i).getNome());

          imprimirDados(categorias.get(i));

        }

    }

    private static void listarCategorias() {

        List<Categoria> categorias = new ArrayList<>();

        Categoria novoObjeto = new Categoria();
        novoObjeto.setId(1);
        novoObjeto.setNome("Eletrônicos");

        categorias.add(novoObjeto);

        Categoria outroObjeto = new Categoria();
        outroObjeto.setId(2);
        outroObjeto.setNome("Roupas");

        categorias.add(outroObjeto);

        Categoria maisUmObjeto = new Categoria();
        maisUmObjeto.setId(3);
        maisUmObjeto.setNome("Calçados");

        categorias.add(maisUmObjeto);

        Categoria maisUmNovoObjeto = new Categoria();
        maisUmNovoObjeto.setId(4);
        maisUmNovoObjeto.setNome("Brinquedos");

        categorias.add(maisUmNovoObjeto);

        for (int i = 0; i < categorias.size(); i++) {

          //  System.out.println("*** INCLUSÃO DE NOVA CATEGORIA ***");
          //  System.out.println("ID: (CODIGO DA CATEGORIA)   :"+categorias.get(i).getId());
          //  System.out.println("Nome:                       :"+categorias.get(i).getNome());

            imprimirDados(categorias.get(i));

        }

    }

    private static void incluirCategoria() {

        Categoria novoObjeto = new Categoria();
        novoObjeto.setId(1);
        novoObjeto.setNome("Eletrônicos");

        imprimirDados(novoObjeto);

    }

    private static void imprimirDados(Categoria categoria){

        System.out.println("*** DADOS DA CATEGORIA ***");
        System.out.println("ID: (CODIGO DA CATEGORIA)   :"+categoria.getId());
        System.out.println("Nome:                       :"+categoria.getNome());


    }

}
