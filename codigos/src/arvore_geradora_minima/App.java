package arvore_geradora_minima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) {
        List<List<int[]>> grafo = listaAdj1();
        List<List<int[]>> grafo2 = listaAdj2();

        List<Aresta> agmKrl = kruskalAGM(grafo);
        List<Aresta> agmPrm = primAGM(grafo);

        List<Aresta> agmKrl2 = kruskalAGM(grafo2);
        List<Aresta> agmPrm2 = primAGM(grafo2);

        System.out.println("\n\n-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println("Grafo 1");

        exibirAGM(agmKrl, "Kruskal");
        exibirAGM(agmPrm, "Prim");

        System.out.println("\n\n-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        System.out.println("Grafo 2");

        exibirAGM(agmKrl2, "Kruskal");
        exibirAGM(agmPrm2, "Prim");
    }

    public static void exibirAGM(List<Aresta> agm, String agmTipo){
        int peso = 0;
        
        System.out.println("---------------------------------------------");
        System.out.println("Lista de arestas da AGM " + agmTipo + "\n");
        System.out.println("aresta|peso");

        for (Aresta e : agm) {
            System.out.println(e.u + " - " + e.v + " (" + e.peso + ")");
            peso += e.peso;
        }
        System.out.println("Peso total: " + peso);
    }

    public static List<List<int[]>> listaAdj1(){
        List<List<int[]>> grafo = new ArrayList<>();
        
        int n = 4;
        for (int i = 0; i < n; i++) grafo.add(new ArrayList<>());

        grafo.get(0).add(new int[]{1, 2});
        grafo.get(1).add(new int[]{0, 2});

        grafo.get(1).add(new int[]{2, 3});
        grafo.get(2).add(new int[]{1, 3});

        grafo.get(0).add(new int[]{3, 6});
        grafo.get(3).add(new int[]{0, 6});

        grafo.get(1).add(new int[]{3, 8});
        grafo.get(3).add(new int[]{1, 8});

        return grafo;
    } 


    public static List<List<int[]>> listaAdj2(){

        
        int n = 12;

        List<List<int[]>> grafo = new ArrayList<>();
        for (int i = 0; i < n; i++) grafo.add(new ArrayList<>());

        // --- Região A (0,1,2,3) ---
        grafo.get(0).add(new int[]{1, 4});
        grafo.get(1).add(new int[]{0, 4});

        grafo.get(0).add(new int[]{2, 6});
        grafo.get(2).add(new int[]{0, 6});

        grafo.get(1).add(new int[]{2, 3});
        grafo.get(2).add(new int[]{1, 3});

        grafo.get(1).add(new int[]{3, 5});
        grafo.get(3).add(new int[]{1, 5});

        grafo.get(2).add(new int[]{3, 7});
        grafo.get(3).add(new int[]{2, 7});

        // --- Região B (4,5,6,7) ---
        grafo.get(4).add(new int[]{5, 2});
        grafo.get(5).add(new int[]{4, 2});

        grafo.get(4).add(new int[]{6, 8});
        grafo.get(6).add(new int[]{4, 8});

        grafo.get(5).add(new int[]{6, 4});
        grafo.get(6).add(new int[]{5, 4});

        grafo.get(5).add(new int[]{7, 6});
        grafo.get(7).add(new int[]{5, 6});

        grafo.get(6).add(new int[]{7, 3});
        grafo.get(7).add(new int[]{6, 3});

        // --- Região C (8,9,10,11) ---
        grafo.get(8).add(new int[]{9, 1});
        grafo.get(9).add(new int[]{8, 1});

        grafo.get(8).add(new int[]{10, 5});
        grafo.get(10).add(new int[]{8, 5});

        grafo.get(9).add(new int[]{10, 2});
        grafo.get(10).add(new int[]{9, 2});

        grafo.get(9).add(new int[]{11, 7});
        grafo.get(11).add(new int[]{9, 7});

        grafo.get(10).add(new int[]{11, 4});
        grafo.get(11).add(new int[]{10, 4});

        // --- Ligações entre as regiões ---
        grafo.get(3).add(new int[]{4, 9});
        grafo.get(4).add(new int[]{3, 9});

        grafo.get(2).add(new int[]{5, 10});
        grafo.get(5).add(new int[]{2, 10});

        grafo.get(7).add(new int[]{8, 3});
        grafo.get(8).add(new int[]{7, 3});

        grafo.get(6).add(new int[]{9, 6});
        grafo.get(9).add(new int[]{6, 6});

        grafo.get(3).add(new int[]{11, 11});
        grafo.get(11).add(new int[]{3, 11});

        return grafo;
    } 

    public static List<Aresta> primAGM(List<List<int[]>> grafo) {

        int n = grafo.size();
        boolean[] visitado = new boolean[n];

        List<Aresta> agm = new ArrayList<>();

        PriorityQueue<Aresta> heap = new PriorityQueue<>(
            (a, b) -> a.peso - b.peso
        );

        visitado[0] = true;

        for (int[] par : grafo.get(0)) {
            heap.add(new Aresta(0, par[0], par[1]));
        }

        while (!heap.isEmpty() && agm.size() < n - 1) {

            Aresta menor = heap.poll();

            int v = menor.v;

            if (visitado[v]) continue;

            agm.add(menor);
            visitado[v] = true;

            for (int[] par : grafo.get(v)) {
                if (!visitado[par[0]]) {
                    heap.add(new Aresta(v, par[0], par[1]));
                }
            }
        }

        return agm;
    }

    
    public static List<Aresta> kruskalAGM(List<List<int[]>> grafo) {
        int n = grafo.size();
        List<Aresta> arestas = new ArrayList<>();

        for (int u = 0; u < n; u++) {
            for (int[] par : grafo.get(u)) {
                int v = par[0];
                int peso = par[1];

                if (u < v) {
                    arestas.add(new Aresta(u, v, peso));
                }
            }
        }

        Collections.sort(arestas);

        UnionFind uf = new UnionFind(n);

        List<Aresta> agm = new ArrayList<>();

        for (Aresta e : arestas) {
            if (uf.find(e.u) != uf.find(e.v)) {
                uf.union(e.u, e.v);
                agm.add(e);
            }
        }

        return agm;
    }

}

class Aresta implements Comparable<Aresta> {
    int u, v, peso;

    Aresta(int u, int v, int peso) {
        this.u = u;
        this.v = v;
        this.peso = peso;
    }

    @Override
    public int compareTo(Aresta outra) {
        return this.peso - outra.peso;
    }
}

class UnionFind {
    int[] pai;

    UnionFind(int n) {
        pai = new int[n];
        for (int i = 0; i < n; i++) pai[i] = i;
    }

    int find(int x) {
        if (pai[x] != x) pai[x] = find(pai[x]);
        return pai[x];
    }

    void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) pai[b] = a;
    }
}





