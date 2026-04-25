import java.util.List;
public class Main {
    
    public static void main(String[] args) {

Graphe g = new Graphe(4);
g.positionner();
g.complet();
List<Noeud> circuit = g.glouton();
g.PrintGraphE(circuit);
// System.out.println(g.toString());
                        // System.out.println(g.toString());

                                        // System.out.println(g.toString());
                                        // g.parcours()  ; 
        // g.addArc(1, 3);
        // g.addArc(2, 3);
        // g.addArc(3, 4);
        // g.addNoeud(8);
        // System.out.println(g.toString());

        // Graphe g2 = new Graphe("graphe.txt");
        // System.out.println(g2.toString());
    }
}