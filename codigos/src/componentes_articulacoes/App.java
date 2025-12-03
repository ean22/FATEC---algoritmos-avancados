package componentes_articulacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        int[][] grafo = criarGrafoAleatorio(4, 2);
        imprimirMatriz(grafo);

        System.out.println("Visitados: " + bfs(grafo));

    }

    public static int[][] criarGrafoAleatorio(int v, int e) {
        int maxArestas = v * (v - 1) / 2;
        if (e > maxArestas) {
            throw new IllegalArgumentException("Número de arestas excede o máximo para um grafo simples!");
        }

        int[][] matriz = new int[v][v];
        Random rand = new Random();

        int criadas = 0;
        while (criadas < e) {
            int u = rand.nextInt(v);
            int w = rand.nextInt(v);

            if (u != w && matriz[u][w] == 0) {
                matriz[u][w] = 1;
                matriz[w][u] = 1;
                criadas++;
            }
        }

        return matriz;
    }

    public static void imprimirMatriz(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String bfs(int[][] matriz) {

        List<Integer> visitados = new ArrayList<>();
        List<Integer> fila = new ArrayList<>();

        int verticeAtual = 0; // começando em 0 (pode ser parâmetro se quiser)

        visitados.add(verticeAtual);
        fila.add(verticeAtual);

        while (!fila.isEmpty()) {
            int v = fila.remove(0); // remove e guarda o vértice sendo explorado

            // percorre apenas a linha do vértice atual (seus vizinhos)
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[v][j] == 1 && !visitados.contains(j)) {
                    visitados.add(j);
                    fila.add(j);
                }
            }
        }

        return visitados.toString();
    }

}
