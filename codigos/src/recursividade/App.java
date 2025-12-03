package recursividade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        int n1, n2, n3;
        n1 = 1499;
        n2 = 2;
        n3 = 6;
        System.out.println();
        System.out.println("Primeiro algarismo de " + n1 + " = " + primeiroAlgarismo(n1));

        List<Integer> lista = new ArrayList<>(Arrays.asList(0,1,2,3,5,2,7,1,2,3));
        System.out.println();
        System.out.println("Lista original: " + lista);
        System.out.println("Lista sem ocorrências de " + n2 + ": " + apagarOcorrencias(lista, n2));

        System.out.println();
        System.out.println(n3 + " é perfeito? " + isPerfeito(n3));
    }

    public static int primeiroAlgarismo(int n) {
        if (n < 10) {
            return n;
        }
        return primeiroAlgarismo(n / 10);
    }



    public static List<Integer> apagarOcorrencias(List<Integer> lista, int n) {
        if (lista.isEmpty()) {
            return new ArrayList<>();
        }

        int primeiro = lista.get(0);
        List<Integer> resto = apagarOcorrencias(lista.subList(1, lista.size()), n);

        if (primeiro != n) {
            resto.add(0, primeiro);
        }

        return resto;
    }


    public static boolean isPerfeito(int n) {
        return somaDivisores(n, 1) == n;
    }

    private static int somaDivisores(int n, int divisor) {
        if (divisor == n) {
            return 0;
        }

        int soma = (n % divisor == 0) ? divisor : 0;

        return soma + somaDivisores(n, divisor + 1);
    }
}
