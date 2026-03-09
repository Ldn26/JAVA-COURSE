public class Main {
    
    public static void main(String[] args) {
        Graphe g = new Graphe(6);
        g.addNoeud(8);
        g.addArc(0, 1);
        g.addArc(0, 2);
        g.addArc(2, 0);
        // System.out.println(g.toString());
                        // System.out.println(g.toString());

                                        System.out.println(g.toString());


        // g.addArc(1, 3);
        // g.addArc(2, 3);
        // g.addArc(3, 4);
        // g.addNoeud(8);
        // System.out.println(g.toString());

        // Graphe g2 = new Graphe("graphe.txt");
        // System.out.println(g2.toString());
    }
}