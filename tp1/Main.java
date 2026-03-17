public class Main {
    
    public static void main(String[] args) {
        Graphe g = new Graphe(9);

    //   1
        g.addArc(1, 3,1);
        g.addArc(1, 4,2);
        g.addArc(1, 5,2);
        g.addArc(1, 6,3);
         g.addArc(1, 7,4);

// 2
                  g.addArc(2, 1,5);
// 3
        g.addArc(3, 2,0);
        g.addArc(3, 6,1);
// 4
        g.addArc(4, 2,2);
                g.addArc(4, 9,3);
//   5  6 
// 7
        g.addArc(7, 3,1);
                g.addArc(7, 6,2);
// 8  
   
        g.addArc(8, 2,3);
        // 9




    g.kruskal();

      

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