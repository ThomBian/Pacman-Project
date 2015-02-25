package Dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @authors Bianchini - Couret - Taboulot - Valette
 * Classe de l'algorithme de Djikstra
 */
public class Dijkstra
{
	/**
	 * Cette méthode détermine le chemin à suivre pour atteindre la cible
	 * @param source Correspond à la source de mon graphe
	 */
    public static void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      	vertexQueue.add(source);

      	//Tant que je n'ai pas visiter tout mes noeuds
		while (!vertexQueue.isEmpty()) {
		    Vertex u = vertexQueue.poll();
		    
	            // Je visit tout les noeuds adjencents
	            for (Edge e : u.adjacencies)
	            {
	                Vertex v = e.target;
	                double weight = e.weight;
	                double distanceThroughU = u.minDistance + weight;
	                //Si mon chemin et plus court qu'un précedent
					if (distanceThroughU < v.minDistance) {
					    vertexQueue.remove(v);
					    v.minDistance = distanceThroughU ;
					    v.previous = u;
					    vertexQueue.add(v);
					}
	            }
	        }
    }

    /**
     * Parcourt le graphe en sens inverse pour retourner le plus court chemin jusqu'à la cible
     * @param target Correspond à la cible à atteindre 
     * @return
     */
    public static List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }

    /**
     * Méthode de lancement du calcul du plus court chemin avec l'algorithme de djikstra
     * @param source Source de mon graphe
     * @param target Cible à atteindre
     * @return Retourne la liste du chemin pour aller de la source à la cible
     */
    public static List<Vertex> run(Vertex source, List<Vertex> target)
    {
    	List<Vertex> path = null;
    	int pathLenght = Integer.MAX_VALUE;
    	Vertex finalTarget = null;
    	computePaths(source);
    	for(Vertex v : target){
	        if(v.minDistance < pathLenght){
	        	pathLenght = (int) v.minDistance;
	        	finalTarget = v;
			    path = getShortestPathTo(v);
			    path.remove(0);
	        }
    	}
    	System.out.println("Distance from " + source + " to " + finalTarget + ": " + finalTarget.minDistance + "cases");
	    System.out.println("Path: " + path);
	    return path;
    }
}
