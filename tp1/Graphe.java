import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class  Graphe{
  LinkedList<Noeud> noeuds ;
  HashMap<Integer, Noeud> hmap ;
  public Graphe(){
    this.noeuds = new LinkedList<Noeud>() ;
    this.hmap = new HashMap<Integer, Noeud>() ;
    }

    
public Graphe(int k){
    this.noeuds = new LinkedList<Noeud>();
    this.hmap = new HashMap<Integer, Noeud>();

    for(int i = 1; i <= k; i++){
        Noeud node = new Noeud(i);
        this.noeuds.add(node);
        this.hmap.put(i, node);
    }
}




    public void addNoeud(int n){
 
            if(hmap.containsKey(n)){
        System.out.println("L'element existe deja");
        return;
    }


    Noeud node = new Noeud(n);

    noeuds.add(node);
    hmap.put(n, node);
       }

    public Noeud getNoeud(int n){
        // with O(1) complexity   (key , value)   
        // cause get  work 
        // hash(8 , node) --> java put the value hash(8) %8  --> 0  and put the node in the table in the index 0
        // get(8) --> java calculate hash(8)    and put it in the table  and return the node in the index 0
        // 1 calcule , 1 access 
        //   java calculate hash (8 )  and put it in the table  

        //       Noeud el =  hmap.get(n) ;

// Si deux clés donnent le même index : 
// hash(5) % 8 = 3
// hash(13) % 8 = 3
// On obtient :3 → (5,node) → (13,node)  Donc Java doit parcourir cette petite liste.
    //   Noeud el =  hmap.get(n) ;
    //   if(el == null){
    //             System.out.println("L'element n'esist pas ");
    //             return null ;

    //   }
    //   return el  ; 
  
    //   With O(n) complexity
        for(Noeud node : this.noeuds){
            if(node.getId() == n){
                return node ;
            }
        }
        return null ;
        
    }

    public void addArc(int x, int y){
        Noeud source = this.getNoeud(x) ;
        Noeud cible = this.getNoeud(y) ;

        if(source.hasSuccesseur(y)){
               System.out.println("L'arc existe deja  ");
               return ;
        }
        if(source != null && cible != null && !source.hasSuccesseur(y)){
            Arc a = new Arc(source, cible ) ;
            source.succ.add(a) ;
        
        }
}
    public void addArc(int x, int weight,  int y){
        Noeud source = this.getNoeud(x) ;
        Noeud cible = this.getNoeud(y) ;
        if(source.hasSuccesseur(y)){
               System.out.println("L'arc existe deja  ");
               return ;
        }
        if(source != null && cible != null && !source.hasSuccesseur(y)){
            Arc a = new Arc(source, cible ,weight ) ;
            source.succ.add(a) ;
        
        }
}
@Override
public String toString(){
    String s = "Graphe : \n" ;
    if(this.noeuds.size() == 0){
        return s + "Le graphe est vide " ;
    }
    for(Noeud n : this.noeuds){
        s += n.toString() + "\n" ;
    }

    return s ;
}


  
    public void parcours(){
     for(Noeud n : this.noeuds){
        n.mark = false ;
     }
        for(Noeud n : this.noeuds){
            if(!n.mark){
                // profR(n) ;
                profI(n)  ; 
            }
        }
  }

// DFS parcours en profondeur    == debth récursif   we use a stack (LIFO ) for successors   
// complexity in time : O(n + m)  n : number of nodes  m : number of arcs 

    public void profR(Noeud n){
     n.mark = true ;
     System.out.println(n.toString());
        for(Arc a : n.succ){
            if(!a.cible.mark){
                profR(a.cible) ;
            }
        }
    }




   



// iterative DFS parcours en profondeur    == debth itératif   we use a stack (LIFO ) for successors
public void profI(Noeud n) {
    Stack<Noeud> stack = new Stack<>();
    n.mark = true;               
    stack.push(n);
    System.out.println(n.toStringOnlyId());

    while (!stack.empty()) {
        Noeud current = stack.peek(); 

        boolean allMarked = true;   

        for (Arc a : current.succ) {
            if (!a.cible.mark) {
                a.cible.mark = true;              
                stack.push(a.cible);             
                System.out.println(a.cible.toStringOnlyId());
                allMarked = false;                
                break;                           
            }
        }

        if (allMarked) {
            stack.pop();
        }
    }
}

// print Graphe
public void PrintGraphE(List<Noeud> circuit) {
    System.out.print("Circuit : ");
    for (int i = 0; i < circuit.size(); i++) {
        System.out.print(circuit.get(i).id);
        if (i < circuit.size() - 1) System.out.print(" -> ");
    }
    System.out.println();

    double longueur = 0;
    for (int i = 0; i < circuit.size() - 1; i++) {
        longueur += distance(circuit.get(i), circuit.get(i + 1));
    }
    System.out.printf("somme distance totale : %.2f%n", longueur);
}
   






private boolean hasCycle(Noeud start, Noeud parent) {
    start.mark = true;
    for(Arc a : start.succ) {
        Noeud next = a.cible;
        if(!next.mark) {
            if(hasCycle(next, start)) return true;
        } else if(next != parent) {
            return true;
        }
    }
    return false;
}


  

//    calc dis bettwen 2arcs AVEC LA FORMULE d = rcine ((x2 - x1)² + (y2 - y1)²)
    private double distance(Noeud a, Noeud b) {
        return Math.sqrt(Math.pow(a.abs - b.abs, 2) + Math.pow(a.ord - b.ord, 2));
    }

// fill the graphe with  random positions for nodes
public void positionner() {
    for(Noeud n : this.noeuds) {
        n.abs = (int)(Math.random() * 100);
        n.ord = (int)(Math.random() * 100);
    }
}

//   add arcs between all nodes and make w = diace
public void complet() {
    for(int i = 0; i < this.noeuds.size(); i++) {
        for(int j = i + 1; j < this.noeuds.size(); j++) {
            Noeud n1 = this.noeuds.get(i);
            Noeud n2 = this.noeuds.get(j);
//    distance = racine carrée de ((x2 - x1)² + (y2 - y1)²)
            int weight = (int)distance(n1, n2);
            this.addArc(n1.id, weight, n2.id);
            this.addArc(n2.id, weight, n1.id); 
        }
    }

  }   

public void partiel(int k) {
    for(Noeud n : this.noeuds) {
        ArrayList<Noeud> neighbors = new ArrayList<>(this.noeuds);
        neighbors.remove(n);
                    neighbors.sort(Comparator.comparingDouble(v -> distance(n, v)));
        for(int i = 0; i < Math.min(k, neighbors.size()); i++) {
            Noeud neighbor = neighbors.get(i);
            int weight = (int)distance(n, neighbor);
            this.addArc(n.id, weight, neighbor.id);
            this.addArc(neighbor.id, weight, n.id); 
        }
    }  }
// algos
  public List<Noeud> glouton() {
        List<Noeud> nonVisites = new ArrayList<>(this.noeuds);
        List<Noeud> circuit    = new ArrayList<>();
  int randomIndex = new Random().nextInt(nonVisites.size());
        Noeud depart = nonVisites.get(randomIndex);
        circuit.add(depart);
        nonVisites.remove(depart);
        Noeud courant = depart;
 
        while (!nonVisites.isEmpty()) {
            Noeud plusProche = null;   
            double distMin   = Double.MAX_VALUE;
            for (Noeud candidat : nonVisites) {
                double d = distance(courant, candidat);
                if (d < distMin) {
                    distMin    = d;
                    plusProche = candidat;
                }
            }
 
            circuit.add(plusProche);
            nonVisites.remove(plusProche);
            courant = plusProche;
        }
   
        circuit.add(depart);
        System.out.println("=== glouton algorithme   ===");
        return circuit;
    }
    /**
     * Méthode MST complète :
     * 1. Calcule le MST par Kruskal
     * 2. Effectue un DFS sur le MST
     * 3. Retourne le circuit (on revient au départ)
     */
public Graphe kruskal() {
    List<Arc> arcs = new ArrayList<>();
    for (Noeud n : this.noeuds) {
        arcs.addAll(n.succ);
    }  
    // log divi  use timsrt
        arcs.sort(Comparator.comparingInt(a -> a.Weight));

        Graphe mst = new Graphe();
    for (Noeud n : this.noeuds) {
        mst.addNoeud(n.id);
    }

    int count = 0;
    for (Arc a : arcs) {
        if (count == this.noeuds.size() - 1) break;
        for (Noeud n : mst.noeuds) n.mark = false;
        // ajouter dans les DEUX sens
        mst.addArc(a.source.id, a.Weight, a.cible.id);
        mst.addArc(a.cible.id, a.Weight, a.source.id);
        // tester cycle
        for (Noeud n : mst.noeuds) n.mark = false;
        if (mst.hasCycle(mst.getNoeud(a.source.id), null)) {
            // retirer
            Noeud s = mst.getNoeud(a.source.id);
            Noeud c = mst.getNoeud(a.cible.id);
            s.succ.removeIf(x -> x.cible == c);
            c.succ.removeIf(x -> x.cible == s);
        } else {
            count++;
        }
    }

    return mst;
}

    private void RunDFSinMTS(Noeud n, List<Noeud> ordre, Set<Integer> visites) {
        visites.add(n.id);
        ordre.add(n);
        for (Arc a : n.succ) {
            if (!visites.contains(a.cible.id)) {
                // on refait le dfs sur  le voisin
                RunDFSinMTS(a.cible, ordre, visites);
            }
        }
    }
 

  public List<Noeud> MSTAlgo() {
    Graphe arbre = this.kruskal();
//    path dfs
    List<Noeud> ordre = new ArrayList<>();
    Set<Integer> visites = new HashSet<>();
    Noeud depart = arbre.noeuds.get(0);
    RunDFSinMTS(depart, ordre, visites);
//   build circuit
    List<Noeud> circuit = new ArrayList<>();
    for (Noeud n : ordre) {
        circuit.add(this.getNoeud(n.id));
    }
    circuit.add(this.getNoeud(depart.id));
    return circuit;
}
     











// ÉTAPES :
// 1. Calculer le MST (Kruskal)
// 2. Trouver les nœuds de degré impair dans le MST
// 3. Faire un couplage parfait minimal entre ces nœuds
//    (version greedy : on prend les paires les plus proches)
// 4. Ajouter les arcs du couplage au MST → multigraphe eulérien
// 5. Trouver un circuit eulérien (Hierholzer)
// 6. Raccourcir (shortcutting) : supprimer les doublons

public List<Noeud> MM() {
    // --- ÉTAPE 1 : MST par Kruskal ---
    Graphe mst = this.kruskal();

    // --- ÉTAPE 2 : Nœuds de degré impair ---
    List<Noeud> impairs = new ArrayList<>();
    for (Noeud n : mst.noeuds) {
        if (n.succ.size() % 2 != 0) {
            impairs.add(this.getNoeud(n.id)); // nœuds du graphe original
        }
    }

    // --- ÉTAPE 3 : Couplage parfait minimal (greedy) ---
    // On couple les nœuds impairs entre eux par distance minimale
    List<int[]> couplage = new ArrayList<>();
    List<Noeud> nonCouples = new ArrayList<>(impairs);

    while (nonCouples.size() >= 2) {
        Noeud u = nonCouples.remove(0);
        double distMin = Double.MAX_VALUE;
        Noeud meilleur = null;
        for (Noeud v : nonCouples) {
            double d = distance(u, v);
            if (d < distMin) {
                distMin = d;
                meilleur = v;
            }
        }
        nonCouples.remove(meilleur);
        couplage.add(new int[]{u.id, meilleur.id, (int) distMin});
    }

    // --- ÉTAPE 4 : Multigraphe = MST + arcs couplage ---
    // On crée un graphe combiné (listes d'adjacence avec doublons possibles)
    // Représentation : Map<Integer, List<int[]>> où int[] = {voisin, poids}
    HashMap<Integer, List<int[]>> multi = new HashMap<>();
    for (Noeud n : mst.noeuds) {
        multi.put(n.id, new ArrayList<>());
    }
    for (Noeud n : mst.noeuds) {
        for (Arc a : n.succ) {
            multi.get(n.id).add(new int[]{a.cible.id, a.Weight});
        }
    }
    for (int[] pair : couplage) {
        multi.get(pair[0]).add(new int[]{pair[1], pair[2]});
        multi.get(pair[1]).add(new int[]{pair[0], pair[2]});
    }

    // --- ÉTAPE 5 : Circuit eulérien (Hierholzer) ---
    List<Integer> eulerCircuit = hierholzer(multi, mst.noeuds.get(0).id);

    // --- ÉTAPE 6 : Shortcutting (suppression des revisites) ---
    List<Noeud> circuit = new ArrayList<>();
    Set<Integer> vus = new HashSet<>();
    for (int id : eulerCircuit) {
        if (!vus.contains(id)) {
            vus.add(id);
            circuit.add(this.getNoeud(id));
        }
    }
    circuit.add(this.getNoeud(circuit.get(0).id)); // retour au départ

    System.out.println("=== MM (Christofides simplifié) ===");
    return circuit;
}
  

// Algorithme de Hierholzer pour trouver un circuit eulérien
// Complexité : O(E) où E = nombre d'arcs
private List<Integer> hierholzer(HashMap<Integer, List<int[]>> graph, int depart) {
    Stack<Integer> stack = new Stack<>();
    List<Integer> circuit = new ArrayList<>();
    stack.push(depart);

    while (!stack.isEmpty()) {
        int v = stack.peek();
        if (graph.get(v) != null && !graph.get(v).isEmpty()) {
            int[] arc = graph.get(v).remove(0);
            int voisin = arc[0];
            // Supprimer l'arc dans l'autre sens aussi
            List<int[]> voisinArcs = graph.get(voisin);
            if (voisinArcs != null) {
                voisinArcs.removeIf(a -> a[0] == v);
            }
            stack.push(voisin);
        } else {
            circuit.add(0, stack.pop());
        }
    }
    return circuit;
}























}






