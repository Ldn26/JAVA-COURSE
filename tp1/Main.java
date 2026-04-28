

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // ============================================================
        // PARTIE 1 : Démonstration des 3 algorithmes sur un petit graphe
        // ============================================================
        System.out.println("============================");
        System.out.println("  DEMO sur 10 noeuds");
        System.out.println("============================\n");

        Graphe g = new Graphe(10);
        g.positionner();
        g.complet();

        List<Noeud> glouton = g.glouton();
        g.PrintGraphE(glouton);

        List<Noeud> mst = g.MSTAlgo();
        g.PrintGraphE(mst);

        List<Noeud> mm = g.MM();
        g.PrintGraphE(mm);

        // ============================================================
        // PARTIE 2 : Protocole expérimental (Question 4 & 5)
        // ============================================================
        System.out.println("\n============================");
        System.out.println("  PROTOCOLE EXPERIMENTAL");
        System.out.println("============================\n");

        int[] tailles = {10, 50, 100, 500, 1000};
        int repetitions = 5; // répéter pour moyenne et écart-type

        System.out.printf("%-8s | %-10s | %-15s %-12s %-12s | %-15s %-12s %-12s | %-15s %-12s %-12s%n",
            "Noeuds", "Répét.", 
            "GLOUTON dist", "temps(ms)", "écart-t",
            "MST dist",     "temps(ms)", "écart-t",
            "MM dist",      "temps(ms)", "écart-t");
        System.out.println("-".repeat(150));

        for (int n : tailles) {
            double[] distGlouton = new double[repetitions];
            double[] distMST     = new double[repetitions];
            double[] distMM      = new double[repetitions];
            long[]   tempsGlouton = new long[repetitions];
            long[]   tempsMST     = new long[repetitions];
            long[]   tempsMM      = new long[repetitions];

            for (int r = 0; r < repetitions; r++) {
                Graphe graphe = new Graphe(n);
                graphe.positionner();
                graphe.complet();

                // --- Glouton ---
                long t0 = System.currentTimeMillis();
                List<Noeud> cG = graphe.glouton();
                tempsGlouton[r] = System.currentTimeMillis() - t0;
                distGlouton[r]  = longueur(cG);

                // --- MST ---
                t0 = System.currentTimeMillis();
                List<Noeud> cM = graphe.MSTAlgo();
                tempsMST[r] = System.currentTimeMillis() - t0;
                distMST[r]  = longueur(cM);

                // --- MM ---
                t0 = System.currentTimeMillis();
                List<Noeud> cMM = graphe.MM();
                tempsMM[r] = System.currentTimeMillis() - t0;
                distMM[r]  = longueur(cMM);
            }

            System.out.printf("%-8d | %-10d | %-15.2f %-12.1f %-12.2f | %-15.2f %-12.1f %-12.2f | %-15.2f %-12.1f %-12.2f%n",
                n, repetitions,
                moyenne(distGlouton),  moyenne(tempsGlouton),  ecartType(distGlouton),
                moyenne(distMST),      moyenne(tempsMST),      ecartType(distMST),
                moyenne(distMM),       moyenne(tempsMM),       ecartType(distMM));
        }
    }

    // Calcule la longueur totale d'un circuit
    static double longueur(List<Noeud> circuit) {
        double total = 0;
        for (int i = 0; i < circuit.size() - 1; i++) {
            Noeud a = circuit.get(i);
            Noeud b = circuit.get(i + 1);
            total += Math.sqrt(Math.pow(a.abs - b.abs, 2) + Math.pow(a.ord - b.ord, 2));
        }
        return total;
    }

    static double moyenne(double[] vals) {
        double s = 0; for (double v : vals) s += v; return s / vals.length;
    }
    static double moyenne(long[] vals) {
        double s = 0; for (long v : vals) s += v; return s / vals.length;
    }
    static double ecartType(double[] vals) {
        double m = moyenne(vals), s = 0;
        for (double v : vals) s += Math.pow(v - m, 2);
        return Math.sqrt(s / vals.length);
    }
}