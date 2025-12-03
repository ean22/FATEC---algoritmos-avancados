# Análise de Grafos - Algoritmos de Teoria dos Grafos

Este programa implementa algoritmos fundamentais para análise de grafos, incluindo verificação de propriedades eulerianas, busca de ciclos hamiltonianos e contagem de componentes conexos.

## Funcionalidades

### 1. Verificação Euleriana

- **Ciclo Euleriano**: Todos os vértices têm grau par
- **Caminho Euleriano**: Exatamente 2 vértices têm grau ímpar
- **Não Euleriano**: Mais de 2 vértices têm grau ímpar

### 2. Ciclo Hamiltoniano

Busca um ciclo que visite cada vértice exatamente uma vez e retorne ao ponto inicial, usando algoritmo de backtracking.

### 3. Componentes Conexos

Conta o número de componentes conexos no grafo usando busca em largura (BFS).

## Como Executar

1. Certifique-se de ter Python 3.x instalado
2. Execute o arquivo `trabalho.py`:

   ```bash
   python trabalho.py
   ```
3. Escolha uma das opções no menu:

   - **1**: Verificar se o grafo é euleriano
   - **2**: Buscar ciclo hamiltoniano (escolha o vértice inicial)
   - **3**: Contar componentes conexos
   - **4**: Sair do programa

## Estrutura do Grafo

O grafo é representado como um **dicionário de listas de adjacência**:

```python
grafo = {
    'A': ['B', 'C', 'D'],    # A está conectado a B, C e D
    'B': ['A', 'C', 'E'],    # B está conectado a A, C e E
    'C': ['A', 'B', 'D', 'E'], # C está conectado a A, B, D e E
    'D': ['A', 'C', 'E'],    # D está conectado a A, C e E
    'E': ['B', 'C', 'D']     # E está conectado a B, C e D
}
```

### Como Alterar o Grafo

Para testar com um grafo diferente:

1. Abra o arquivo `trabalho.py`
2. Localize a variável `grafo` no bloco `if __name__ == "__main__"`
3. Modifique as conexões conforme necessário
4. Execute o programa

**Exemplo de grafo simples (triângulo):**

```python
grafo = {
    'A': ['B', 'C'],
    'B': ['A', 'C'],
    'C': ['A', 'B']
}
```

**Exemplo de grafo desconexo:**

```python
grafo = {
    'A': ['B'],
    'B': ['A'],
    'C': ['D'],
    'D': ['C'],
    'E': []  # Vértice isolado
}
```

## Funções Implementadas

### `grafo_euleriano(grafo)`

- Verifica se o grafo é euleriano
- Encontra um caminho/ciclo euleriano usando algoritmo de Hierholzer
- **Modifica o grafo durante a execução** (por isso usa cópia)

### `hamiltoniano(grafo, vertice_inicial)`

- Busca um ciclo hamiltoniano usando backtracking recursivo
- Retorna a lista do ciclo ou `None` se não encontrar

### `dict_para_matriz(grafo)`

- Converte representação de dicionário para matriz de adjacência
- Necessária para algoritmos que trabalham com índices numéricos

### `achar_componentes(grafo)` [implementação integrada]

- Conta componentes conexos usando BFS
- Trabalha com matriz de adjacência

## Limitações

- **Grafo não-dirigido**: Assume que todas as conexões são bidirecionais
- **Vértices como strings**: Usa letras/nomes em vez de números
- **Sem pesos**: Não suporta arestas com pesos
- **Backtracking exponencial**: Busca hamiltoniana pode ser lenta para grafos grandes
