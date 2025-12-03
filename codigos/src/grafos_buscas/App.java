package grafos_buscas;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int resposta = -1;
        int [][] matriz = {{0}};


        while (resposta != 0) {
            mostrarMenu();
            resposta = sc.nextInt();
            
            switch (resposta) {
                case 1:
                    matriz = criarMatrizAdj(sc);
                    exibirMatrizAdj(matriz);
                    break;
            
                case 2:
                    exibirMatrizAdj(matriz);
                    break;
                
                case 3:
                    criarAresta(matriz, sc);
                    break;

                case 4:
                    System.out.println("Lista de visitados(BFS): " + buscaLargura(matriz, sc));
                    break;

                case 5:
                    System.out.println("Lista de visitados(DFS): " + buscaProfundidade(matriz, sc));
                    break;

                case 6:
                    int[][] novaMatriz = {
                        {0,1,1,0,0,0,0},
                        {1,0,0,1,1,0,0},
                        {1,0,0,0,0,0,0},
                        {0,1,0,0,0,1,1},
                        {0,1,0,0,0,0,0},
                        {0,0,0,1,0,0,0},
                        {0,0,0,1,0,0,0}
                    };

                    matriz = novaMatriz;
                    exibirMatrizAdj(matriz);
                    break;

                case 0:
                    System.out.println("Encerrando .........");
                    break;
            
             default:
                 break;
            }
        }
        sc.close();

    }

    public static void mostrarMenu(){      
        System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_- ");
        System.out.println("Digite o número da opção desejada");
        System.out.println("(1) - criar grafo");
        System.out.println("(2) - exibir grafo");
        System.out.println("(3) - criar arestas");
        System.out.println("(4) - busca em largura");
        System.out.println("(5) - busca em profundidade");
        System.out.println("(6) - criar grafo de teste");
        System.out.println("(0) - sair");
    }

    public static int[][] criarMatrizAdj(Scanner sc){
        int numVertices = 0;
        int [][] matrizAdj;

        System.out.println("Digite o número de vértices do grafo:");
        numVertices = sc.nextInt();

        matrizAdj = new int [numVertices] [numVertices];
        return matrizAdj;
    }

    public static void exibirMatrizAdj(int [][] matrizAdj){
        for(int i = 0; i < matrizAdj.length; i++){
            for(int j = 0; j < matrizAdj[0].length; j++){
                System.out.print(matrizAdj[i][j] + ", ");
            }

            System.out.println("");
        }

    }

    public static void criarAresta(int [][] matrizAdj, Scanner sc){
        int numArestas, origemCol, destinoLinha;
        numArestas = origemCol = destinoLinha = 0;       

        System.out.println("Digite quantas arestas você quer criar:");
        numArestas = sc.nextInt();
        sc.nextLine();

        while (numArestas > 0) {
            System.out.println("Digite o vértice de origem:");
            origemCol = sc.nextInt();

            System.out.println("Digite o vértice de destino:");
            destinoLinha = sc.nextInt();

            matrizAdj[origemCol][destinoLinha] = 1;
            matrizAdj[destinoLinha][origemCol] = 1;

            exibirMatrizAdj(matrizAdj);
            
            numArestas--;
        }

    }

    public static String buscaLargura(int [][] matrizAdj, Scanner sc){
        int vertice = 0;
        List<Integer> visitados = new ArrayList<>();
        List<Integer> fila = new ArrayList<>();

        System.out.println("Digite o vértice de inicio:");
        vertice = sc.nextInt();

        visitados.add(vertice);
        fila.add(vertice);

        while(!fila.isEmpty()){
            vertice = fila.remove(0);

            for(int i = 0; i < matrizAdj[0].length; i++){
                if(matrizAdj[vertice][i] == 1 && !visitados.contains(i)){
                    visitados.add(i);
                    fila.add(i);
                }
            }
        }
        return visitados.toString();
    }

    public static String buscaProfundidade(int[][] matrizAdj, Scanner sc) {
        List<Integer> visitados = new ArrayList<>();
        int vertice = 0;
        
        System.out.println("Digite o vértice inicial:");
        vertice = sc.nextInt();

        dfs(vertice, matrizAdj, visitados);

        return visitados.toString();
    }

    private static void dfs(int atual, int[][] matrizAdj, List<Integer> visitados) {
        visitados.add(atual);

        for (int i = 0; i < matrizAdj.length; i++) {
            if (matrizAdj[atual][i] == 1 && !visitados.contains(i)) {
                dfs(i, matrizAdj, visitados);
            }
        }
    }

}
