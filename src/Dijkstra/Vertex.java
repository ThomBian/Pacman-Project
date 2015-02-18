package Dijkstra;

import model.Tile;

/**
 * Classe correspond à un noeaud d'un graphe
 * @author Clément Taboulot
 */
public class Vertex implements Comparable<Vertex>
{
    public final Tile tile;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Vertex(Tile argTile) { tile = argTile; }
    public String toString() { return new String("(" + tile.getX() + ";" + tile.getY() + ")"); }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}
