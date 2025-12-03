def grafo_euleriano(grafo: dict) -> None:

    # criando o ponto de partida do grafo
    start_point = None

    # descobrindo a existência de vértices com grau ímpar
    grau_impar: list = [v for v in grafo if len(grafo[v]) % 2 == 1]
    
    # definindo se é caminho, ciclo ou grafo não euleriano
    if len(grau_impar) == 0:
        print("Ciclo Euleriano")
        start_point = next(iter(grafo))
    elif len(grau_impar) == 2:
        print("Caminho Euleriano")
        start_point = grau_impar[0]
    else:
        print("O Grafo não é Euleriano")
        return

    # criando o algoritmo com base em stacks
    visitados = []
    stack = [start_point]

    while stack:

        # pegando o último vértice do stack
        vertice = stack[-1]

        # Se o vértice tiver qualquer outro vértice adjacente, remover a aresta que os liga e colocá-lo no stack
        if grafo[vertice]:
            adjacente = grafo[vertice].pop()
            grafo[adjacente].remove(vertice)
            
            stack.append(adjacente)

        # caso não, colocar o vértice na lista de visitados
        else:
            visitados.append(stack.pop())

    # encerrando o algoritmo
    print("Vértices visitados:", visitados[::-1])
    return

def hamiltoniano(grafo:dict, vertice, caminho=None):
    # Para recursividade não falhar
    if caminho is None:
        caminho = [vertice]

    # Quantidade de Vertices
    n_vertices = len(grafo)

    # Para quando o caminho terminar todos os vertices
    if len(caminho) == n_vertices:
        # Verifica se conectou para fechar um ciclo
        if caminho[0] in grafo.get(vertice, []):
            return caminho
        else:
            return None

    # Itera nos vizinhos
    for vizinho in grafo.get(vertice, []):
        # Verifica se o vizinho já foi visitado
        if vizinho not in caminho:
            # Adiciona Vertice ao caminho
            caminho.append(vizinho)
            
            # Chamada recursiva
            resultado = hamiltoniano(grafo, vizinho, caminho)
            if resultado is not None:
                return resultado
            
            # Backtracking: remove apenas se a tentativa falhou
            caminho.pop()
    return None

def achar_componentes(grafo):

    visitados = []
    componentes_conexos = 0

    for vertice_num, vertice in enumerate(grafo):

        # print(vertice)

        if vertice_num not in visitados:
            visitados.append(vertice_num)
            componentes_conexos += 1

            fila = [vertice_num]
            while fila:
                # Algoritmo de busca por largura
                for idx, conexao in enumerate(grafo[fila[0]]):

                    if conexao == 1 and idx not in visitados:
                        visitados.append(idx)
                        fila.append(idx)
                fila.pop(0)

### Função Auxiliar
def dict_para_matriz(grafo_dict):
    vertices = sorted(grafo_dict.keys())
    n = len(vertices)
    
    matriz = [[0 for _ in range(n)] for _ in range(n)]
    
    indice_vertice = {vertice: idx for idx, vertice in enumerate(vertices)}
    
    for vertice, vizinhos in grafo_dict.items():
        idx_i = indice_vertice[vertice]
        for vizinho in vizinhos:
            idx_j = indice_vertice[vizinho]
            matriz[idx_i][idx_j] = 1
            matriz[idx_j][idx_i] = 1
    
    return matriz

if __name__ == "__main__":
    grafo = {
        'A': ['B', 'C', 'D'],
        'B': ['A', 'C', 'E'],
        'C': ['A', 'B', 'D', 'E'],
        'D': ['A', 'C', 'E'],
        'E': ['B', 'C', 'D']
    }

    while True:
        print("\n=== MENU DE ANÁLISE DE GRAFOS ===")
        print("1 - Verificar se é Euleriano")
        print("2 - Encontrar ciclo Hamiltoniano")
        print("3 - Contar componentes conexos")
        print("4 - Sair")
        print("-" * 30)

        try:
            opcao = int(input("Escolha uma opção (1-4): "))
            
            if opcao == 1:
                print("\n=== VERIFICAÇÃO EULERIANA ===")
                grafo_euleriano(grafo.copy())
                
            elif opcao == 2:
                print("\n=== CICLO HAMILTONIANO ===")
                vertice_inicial = input("Digite o vértice inicial (A, B, C, D, E): ").upper()
                if vertice_inicial not in grafo:
                    print("Vértice inválido!")
                    continue
                    
                resultado = hamiltoniano(grafo, vertice_inicial)
                if resultado:
                    print(f"Ciclo Hamiltoniano encontrado: {' -> '.join(resultado)}")
                else:
                    print("Nenhum ciclo Hamiltoniano encontrado a partir deste vértice.")
                    
            elif opcao == 3:
                print("\n=== COMPONENTES CONEXOS ===")
        
                matriz = dict_para_matriz(grafo)
                
                visitados = []
                componentes_conexos = 0
                
                for i in range(len(matriz)):
                    if i not in visitados:
                        visitados.append(i)
                        componentes_conexos += 1
                        
                        fila = [i]
                        while fila:
                            vertice_atual = fila.pop(0)
                            for j, conexao in enumerate(matriz[vertice_atual]):
                                if conexao == 1 and j not in visitados:
                                    visitados.append(j)
                                    fila.append(j)
                
                vertices_ordenados = sorted(grafo.keys())
                print(f"Vértices: {vertices_ordenados}")
                print(f"Número de componentes conexos: {componentes_conexos}")
                
            elif opcao == 4:
                print("\nSaindo do programa...")
                break
                
            else:
                print("Opção inválida! Digite um número de 1 a 4.")
                
        except ValueError:
            print("Por favor, digite um número válido!")
        except KeyboardInterrupt:
            print("\n\nPrograma interrompido pelo usuário.")
            break
        

