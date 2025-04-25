/**
 * Classe principal do Contole de Estoque
 */
public class MenuPrincipal {

    public static Categoria novaCategoria;

    public static void main(String[] args) {

        novaCategoria = new Categoria();
        novaCategoria.setNome("Brinquedo");
    }

    public void salvarCategoria(){
        novaCategoria.setId(1);
    }

}
