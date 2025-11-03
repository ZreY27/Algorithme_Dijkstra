package main.java.graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrapheLAdj extends Graphe {
    private Map<String, List<String>> adjacence;
    private Map<Map<String,String>, Integer> listeValuee;

    public GrapheLAdj() {
        adjacence = new HashMap<>();
        listeValuee = new HashMap<>();
    }

    @Override
    public void ajouterSommet(String noeud) {
        // Si le sommet n'existe pas déjà, l'ajouter avec une liste d'adjacence vide
        adjacence.putIfAbsent(noeud, new ArrayList<>());
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valeur) {
        if(contientArc(source, destination)){
            throw new IllegalArgumentException("L'arc est déjà présent");
        }
        if(valeur < 0){
            throw new IllegalArgumentException("L'arc est déjà présent");
        }
        // Ajouter le sommet source s'il n'existe pas encore
        ajouterSommet(source);
        // Ajouter le sommet destination s'il n'existe pas encore
        ajouterSommet(destination);
        // Ajouter la destination à la liste d'adjacence du source
        adjacence.get(source).add(destination);
        HashMap<String, String> listeNonValuee;
        listeNonValuee = new HashMap<>();
        listeNonValuee.put(source, destination);
        listeValuee.put(listeNonValuee, valeur);
    }

    @Override
    public void oterSommet(String noeud) {
        // Retirer le sommet de la liste d'adjacence de chaque autre sommet
        for (List<String> voisins : adjacence.values()) {
            voisins.remove(noeud);
        }
        // Retirer le sommet de la liste d'adjacence globale
        adjacence.remove(noeud);
    }

    @Override
    public void oterArc(String source, String destination) {
        // Retirer la destination de la liste d'adjacence du source
        if(!contientArc(source, destination)){
            throw new IllegalArgumentException("L'arc est déjà présent");
        }
        adjacence.getOrDefault(source, new ArrayList<>()).remove(destination);
    }

    @Override
    public List<String> getSommets() {
        // Retourner la liste des sommets (clés de la map)
        return new ArrayList<>(adjacence.keySet());
    }

    @Override
    public List<String> getSucc(String sommet) {
        // Retourner la liste des successeurs du sommet (valeurs de la map)
        return adjacence.getOrDefault(sommet, new ArrayList<>());
    }

    @Override
    public int getValuation(String src, String dest) {
        // Pour un graphe non pondéré, la valuation est généralement 1 si l'arc existe et 0 sinon
        if (contientArc(src, dest)) {
            HashMap<String, String> liste;
            liste = new HashMap<>();
            liste.put(src, dest);
            return listeValuee.get(liste);
        }
        return contientArc(src, dest) ? 1 : 0;
    }

    @Override
    public boolean contientSommet(String sommet) {
        // Vérifier si le sommet existe dans la map d'adjacence
        return adjacence.containsKey(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        // Vérifier si la destination est présente dans la liste d'adjacence du source
        return adjacence.getOrDefault(src, new ArrayList<>()).contains(dest);
    }
}