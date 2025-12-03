package arvore_geradora_minima;

import java.util.Random;

public class App {
    public static void main(String[] args) {
        int [][] matriz;

        matriz = gerarMatrizDinamica(4,5);

        imprimirMatriz(matriz);
    }

    public static int[][] gerarMatrizTeste(){
        int[][] matriz = {
            {0,0,5,1},
            {0,0,2,2},
            {5,2,0,7},
            {1,2,7,0}
        };

        return matriz;
    }

    public static int[][] gerarMatrizDinamica(int numV, int numE){
        int maxArestas = numV * (numV - 1) / 2;
        int[][] matriz = new int[numV][numV];
        int criadas = 0;
        int peso = 0;
        
        if (numE > maxArestas) {
            throw new IllegalArgumentException("Número de arestas excede o máximo para um grafo simples!");
        }

        Random rand = new Random();

        while (criadas < numE) {
            int u = rand.nextInt(numV);
            int w = rand.nextInt(numV);

            if (u != w && matriz[u][w] == 0) {
                peso = rand.nextInt(1,10);
                matriz[u][w] = peso;
                matriz[w][u] = peso;
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



    
}


