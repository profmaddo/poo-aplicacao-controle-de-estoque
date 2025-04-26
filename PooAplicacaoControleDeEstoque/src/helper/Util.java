package helper;

import model.Tipo;

import java.math.BigDecimal;
import java.util.Scanner;

public class Util {

    public static int lerInt(Scanner scanner, String mensagem) {
        int valor = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensagem);
            try {
                valor = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Digite um número inteiro.");
            }
        }
        return valor;
    }

    public static BigDecimal lerBigDecimal(Scanner scanner, String mensagem) {
        BigDecimal valor = BigDecimal.ZERO;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensagem);
            try {
                valor = new BigDecimal(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida. Digite um número decimal, usando ponto (.) para casas decimais.");
            }
        }
        return valor;
    }

    public static String lerString(Scanner scanner, String mensagem) {
        String texto = "";
        while (texto.isBlank()) {
            System.out.print(mensagem);
            texto = scanner.nextLine();
            if (texto.isBlank()) {
                System.out.println("⚠️ Texto não pode ser vazio.");
            }
        }
        return texto;
    }

    public static Tipo lerTipoMovimentacao(Scanner scanner, String mensagem) {
        Tipo tipo = null;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensagem + " (ENTRADA, SAIDA, AJUSTE): ");
            try {
                tipo = Tipo.valueOf(scanner.nextLine().toUpperCase());
                valido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("⚠️ Tipo inválido. Tente novamente.");
            }
        }
        return tipo;
    }

}
