package Dijkstra;

/**
 * @authors Bianchini - Couret - Taboulot - Valette
 * Classe modélisant un arc entre 2 Vertex (noeuds)
 */
public class Edge extends Object{
    public final Vertex target;
    public final double weight;
    public Edge(Vertex argTarget, double argWeight)
    { target = argTarget; weight = argWeight; }
}

