package main.java.graphe;

import java.util.ArrayList;
import java.util.List;
public class GrapheLArcs extends Graphe {
    private List<Arc> arcs;
    private List<String> sommets;
    public GrapheLArcs() {
        this.arcs = new ArrayList<>();
        this.sommets = new ArrayList<>();
    }

    @Override
    public void ajouterSommet(String noeud) {
        // Vérifier si le sommet existe déjà dans la liste d'arcs
        if (!sommets.contains(noeud)) {
            // Si le sommet n'existe pas, l'ajouter à la liste des sommets
            sommets.add(noeud);
        }
    }

    @Override
    public void ajouterArc(String source, String destination, Integer valuation) {
        if (source == null || destination == null || source.isEmpty() || destination.isEmpty()) {
            throw new IllegalArgumentException("Les points de départ et d'arrivée ne peuvent pas être nulls ou vides");
        }
        if (valuation < 0) {
            throw new IllegalArgumentException("Le poids ne peut pas être négatif");
        }
        if(contientArc(source, destination)){
            throw new IllegalArgumentException("L'arc est déjà présent");
        }
        else{
            ajouterSommet(source);
            ajouterSommet(destination);
            arcs.add(new Arc(source, destination, valuation));
        }

    }

    @Override
    public void oterSommet(String noeud) {
        // Supprimer tous les arcs qui ont le noeud comme source ou destination
        arcs.removeIf(arc -> arc.getSource().equals(noeud) || arc.getDestination().equals(noeud));

        // Supprimer le noeud de la liste des sommets
        arcs.removeIf(arc -> arc.getSource().equals(noeud));
        arcs.removeIf(arc -> arc.getDestination().equals(noeud));
        sommets.remove(noeud);
    }

    @Override
    public void oterArc(String source, String destination) {
        if(!contientArc(source, destination)){
            throw new IllegalArgumentException("L'arc n'existe pas");
        }
        arcs.removeIf(arc -> arc.getSource().equals(source) && arc.getDestination().equals(destination));
    }

    public int getValuation(String source, String destination){
        for (Arc arc : arcs) {
            if (arc.getSource().equals(source) && arc.getDestination().equals(destination)) {
                return arc.getValuation();
            }
        }
        return Integer.MAX_VALUE; // Arc inexistant
    }

    @Override
    public boolean contientSommet(String sommet) {
        return sommets.contains(sommet);
    }

    @Override
    public boolean contientArc(String src, String dest) {
        for (Arc arc : arcs) {
            if (arc.getSource().equals(src) && arc.getDestination().equals(dest)) {
                return true; // L'arc est trouvé
            }
        }
        return false; // L'arc n'est pas trouvé
    }

    public List<Arc> getArcs() {
        return arcs;
    }

    @Override
    public List<String> getSommets() {
        return this.sommets;
    }

    public List<String> getSucc(String sommet){
        List<String> succ = new ArrayList<>();
        for (Arc arc : arcs) {
            if (arc.getSource().equals(sommet)){
                succ.add(arc.getDestination());
            }
        }
        return succ;
    }
}