package main.java.graphe;

import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
    public static void dijkstra(IGraphe graphe, String depart, Map<String, Integer> distance, Map<String, String> predecesseur) {
        // Initialisation des structures de données
        distance.clear();
        predecesseur.clear();
        PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> distance.get(a) - distance.get(b));

        // Initialisation de la distance de départ à 0 et des autres à l'infini
        for (String sommet : graphe.getSommets()) {
            distance.put(sommet, Integer.MAX_VALUE);
            predecesseur.put(sommet, null);
        }
        distance.put(depart, 0);

        // Ajout du départ à la file de priorité
        queue.add(depart);

        // Boucle principale
        while (!queue.isEmpty()) {
            String u = queue.poll();
            for (String v : graphe.getSucc(u)) {
                int poidsUV = graphe.getValuation(u, v);
                if (distance.get(u) + poidsUV < distance.get(v)) {
                    distance.put(v, distance.get(u) + poidsUV);
                    predecesseur.put(v, u);
                    queue.add(v);
                }
            }
        }
    }
}
