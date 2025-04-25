import java.util.ArrayList;
import java.util.List;

public class ManterUnidadeDeMedida {

    public static void main(String[] args) {

        // CRUD CREATE READ UPDATE DELETE
        //incluir();
       // listar();
        //deletar();
      //  alterar();
        pesquisar();


    }

    private static void pesquisar() {

        List<UnidadeDeMedida> medidaList = new ArrayList<>();

        UnidadeDeMedida unidadeDeMedidaKilo = new UnidadeDeMedida();
        unidadeDeMedidaKilo.setId(1);
        unidadeDeMedidaKilo.setNome("Kilo");

        medidaList.add(unidadeDeMedidaKilo);

        UnidadeDeMedida unidadeDeMedidaMetro = new UnidadeDeMedida();
        unidadeDeMedidaMetro.setId(2);
        unidadeDeMedidaMetro.setNome("Metro");

        medidaList.add(unidadeDeMedidaMetro);

        UnidadeDeMedida unidadeDeMedidaTonelada = new UnidadeDeMedida();
        unidadeDeMedidaTonelada.setId(3);
        unidadeDeMedidaTonelada.setNome("Tonelada");

        medidaList.add(unidadeDeMedidaTonelada);

        int idDePesquisa = 2;

        try {

            UnidadeDeMedida unidadeDeMedida = medidaList.get(idDePesquisa);

            imprimirDados(unidadeDeMedida);

        }catch (Exception e){
            System.out.println("Não encontrado ID "+idDePesquisa);
        }



    }

    private static void alterar() {

        UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();
        unidadeDeMedida.setId(1);
        unidadeDeMedida.setNome("Kilo");

        imprimirDados(unidadeDeMedida);

        unidadeDeMedida.setNome("Kg");

        imprimirDados(unidadeDeMedida);


    }

    private static void deletar() {

        List<UnidadeDeMedida> medidaList = new ArrayList<>();

        UnidadeDeMedida unidadeDeMedidaKilo = new UnidadeDeMedida();
        unidadeDeMedidaKilo.setId(1);
        unidadeDeMedidaKilo.setNome("Kilo");

        medidaList.add(unidadeDeMedidaKilo);

        UnidadeDeMedida unidadeDeMedidaMetro = new UnidadeDeMedida();
        unidadeDeMedidaMetro.setId(2);
        unidadeDeMedidaMetro.setNome("Metro");

        medidaList.add(unidadeDeMedidaMetro);

        UnidadeDeMedida unidadeDeMedidaTonelada = new UnidadeDeMedida();
        unidadeDeMedidaTonelada.setId(3);
        unidadeDeMedidaTonelada.setNome("Tonelada");

        medidaList.add(unidadeDeMedidaTonelada);

        medidaList.remove(1);

        for (int i = 0; i < medidaList.size(); i++) {

            imprimirDados(medidaList.get(i));

        }


    }

    private static void listar() {

        List<UnidadeDeMedida> medidaList = new ArrayList<>();

        UnidadeDeMedida unidadeDeMedidaKilo = new UnidadeDeMedida();
        unidadeDeMedidaKilo.setId(1);
        unidadeDeMedidaKilo.setNome("Kilo");

        medidaList.add(unidadeDeMedidaKilo);

        UnidadeDeMedida unidadeDeMedidaMetro = new UnidadeDeMedida();
        unidadeDeMedidaMetro.setId(2);
        unidadeDeMedidaMetro.setNome("Metro");

        medidaList.add(unidadeDeMedidaMetro);

        UnidadeDeMedida unidadeDeMedidaTonelada = new UnidadeDeMedida();
        unidadeDeMedidaTonelada.setId(3);
        unidadeDeMedidaTonelada.setNome("Tonelada");

        medidaList.add(unidadeDeMedidaTonelada);

        for (int i = 0; i < medidaList.size(); i++) {

            imprimirDados(medidaList.get(i));

        }




    }

    private static void incluir() {

        UnidadeDeMedida unidadeDeMedida = new UnidadeDeMedida();
        unidadeDeMedida.setId(1);
        unidadeDeMedida.setNome("Kilo");

        imprimirDados(unidadeDeMedida);

    }


    private static void imprimirDados(UnidadeDeMedida unidadeDeMedida){

        System.out.println("*** DADOS DA UNIDADE DE MEDIDA ***");
        System.out.println("ID:   :"+unidadeDeMedida.getId());
        System.out.println("Nome: :"+unidadeDeMedida.getNome());


    }

}
