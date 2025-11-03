package main.java.graphe;

public class Arc {

    private String source;
    private String destination;
    private int valuation;

    public Arc(String source, String destination, int valuation) {
        this.source = source;
        this.destination = destination;
        this.valuation = valuation;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getValuation() {
        return valuation;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setValuation(int valuation) {
        this.valuation = valuation;
    }

    @Override
    public String toString() {
        return "Arc{" +
                "source=" + source +
                ", destination=" + destination +
                ", valuation=" + valuation +
                '}';
    }
}
