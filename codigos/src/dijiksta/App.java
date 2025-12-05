package dijiksta;

public class App {
    public static void main(String[] args) throws Exception {
        int[][] matriz = criarGrafo();
        exibirMatriz(matriz);

        dijkstra(matriz,1,2);
}

    public static int[][] criarGrafo(){
        // Representação como matriz de adjacências, da lista de adjacencias passada no exercício
        
        int[][] m = {
            { 0,  2, 3, 0, 6, 0, 0, 0, 0, 0, 4},
            { 2,  0, 0, 9, 0, 0, 0, 0, 0, 0, 0},
            { 3,  0, 0, 0, 0, 0, 0, 5, 0, 0, 9},
            { 0,  9, 0, 0, 0, 6, 0, 0, 0, 0, 0},
            { 6,  0, 0, 0, 0, 8, 0, 0, 0, 0, 7},
            { 0,  0, 0, 6, 8, 0, 5, 0, 0, 0, 0},
            { 0,  0, 0, 0, 0, 5, 0, 3,11, 0,10},
            { 0,  0, 0, 0, 0, 0, 3, 0, 0, 2, 0}, 
            { 0,  0, 0, 0, 0, 0,11, 0, 0, 3, 0},
            { 0,  0, 0, 0, 0, 0, 0, 2, 3, 0, 0}, 
            { 4,  0, 9, 0, 7, 0,10, 0, 0, 0, 0}  
        };

        return  m;
    }

    public static void exibirMatriz(int[][] m){       
        for(int i = 0; i< m.length; i++){
            for(int j = 0; j < m.length; j ++){
                System.out.print(m[i][j] + ",");
            }
            System.out.println();
        }
    }

    public static void exibirArray(int[] a){
        for(int i = 0; i < a.length; i++) System.out.print(a[i] + ",");
        System.out.println();
    }

    public static void dijkstra(int[][] grafo, int origem, int destino) {
        int n = grafo.length;

        int[] distancia = new int[n];
        int[] anterior = new int[n];
        boolean[] visitado = new boolean[n];

        for (int i = 0; i < n; i++) {
            distancia[i] = Integer.MAX_VALUE;
            anterior[i] = -1;
            visitado[i] = false;
        }

        distancia[origem] = 0;

        for (int i = 0; i < n; i++) {
            int u = -1;
            int menorDist = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                if (!visitado[j] && distancia[j] < menorDist) {
                    menorDist = distancia[j];
                    u = j;
                }
            }

            if (u == -1) break; 
            visitado[u] = true;

            for (int v = 0; v < n; v++) {
                if (grafo[u][v] > 0 && !visitado[v]) {

                    int novaDist = distancia[u] + grafo[u][v];

                    if (novaDist < distancia[v]) {
                        distancia[v] = novaDist;
                        anterior[v] = u;
                    }
                }
            }

            if (u == destino) break;
        }


        System.out.println("\nOrigem: " + origem);

        System.out.println("\nDestino: " + destino);

        System.out.println("\nDistâncias:");
        exibirArray(distancia);

        System.out.println("\nAnteriores:");
        exibirArray(anterior);
    }

}
