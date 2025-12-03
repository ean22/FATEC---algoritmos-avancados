package arvore_geradora_minima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) {
        List<List<int[]>> grafo = listaAdj();

        List<Aresta> agmKrl = kruskalAGM(grafo);
        List<Aresta> agmPrm = primAGM(grafo);

        exibirAGM(agmKrl, "Kruskal");
        exibirAGM(agmPrm, "Prim");
    }

    public static void exibirAGM(List<Aresta> agm, String agmTipo){
        int peso = 0;
        
        System.out.println("---------------------------------------------");
        System.out.println("Lista de arestas da AGM " + agmTipo + "\n");
        System.out.println("aresta|peso");

        for (Aresta e : agm) {
            System.out.println(e.u + " - " + e.v + " (" + e.peso + ")");
            peso =+ e.peso;
        }
        System.out.println("Peso total: " + peso);
    }

    public static List<List<int[]>> listaAdj(){
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

    public static List<Aresta> primAGM(List<List<int[]>> grafo) {

        int n = grafo.size();
        boolean[] visitado = new boolean[n];

        List<Aresta> agm = new ArrayList<>();

        // Min-heap ordenada pelo peso
        PriorityQueue<Aresta> heap = new PriorityQueue<>(
            (a, b) -> a.peso - b.peso
        );

        // 1. Começamos do vértice 0
        visitado[0] = true;

        // Adiciona todas as arestas saindo do 0
        for (int[] par : grafo.get(0)) {
            heap.add(new Aresta(0, par[0], par[1]));
        }

        // 2. Prim
        while (!heap.isEmpty() && agm.size() < n - 1) {

            Aresta menor = heap.poll();

            int v = menor.v;

            // Se o destino já foi visitado, ignoramos
            if (visitado[v]) continue;

            // Aceita a aresta
            agm.add(menor);
            visitado[v] = true;

            // Adiciona as arestas do novo vértice
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

        // 1. Extrair arestas da lista de adjacência
        for (int u = 0; u < n; u++) {
            for (int[] par : grafo.get(u)) {
                int v = par[0];
                int peso = par[1];

                // Evitar duplicar (u,v) e (v,u)
                if (u < v) {
                    arestas.add(new Aresta(u, v, peso));
                }
            }
        }

        // 2. Ordenar pelo peso
        Collections.sort(arestas);

        // 3. Union-Find
        UnionFind uf = new UnionFind(n);

        // 4. AGM (resultado)
        List<Aresta> agm = new ArrayList<>();

        // 5. Kruskal
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





