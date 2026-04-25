import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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






// prof
// public void parcourProf( Noeud n ){
//   for (int i = 0    ; i <noeuds.size()   ; i++ ) {
    
//     // Noeud n =   ; 
//     // // boolean mark  =  true   ;
//     //      for(Arc a : noeuds.get(i).succ){
//     //     if(!n.mark){
//     //     }
//   }

  

//     }
  
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


 


public void kruskal() {
    for(Noeud n : this.noeuds) n.mark = false;
    LinkedList<Arc> arcs = new LinkedList<>();
    for(Noeud n : this.noeuds) {
        arcs.addAll(n.succ);
        n.succ.clear(); 
    }

    arcs.sort(Comparator.comparingInt(a -> a.Weight));
    LinkedList<Arc> mst = new LinkedList<>();
    for(Arc a : arcs) {
        for(Noeud n : this.noeuds) n.mark = false;
        a.source.succ.add(a); 
        if(hasCycle(a.source, null)) {
            a.source.succ.remove(a);
        } else {
            mst.add(a);
            System.out.println("Ajouter à l’arbre : " + a);
        }
       // n-1 arc  
        if(mst.size() == this.noeuds.size() - 1) break;
    }
    System.out.println("Arbre couvrant de poids minimum :");
    for(Arc a : mst) {
        System.out.println(a);
    }




}









// algothims imple,emtation: 








  public List<Noeud> glouton() {
        List<Noeud> nonVisites = new ArrayList<>(this.noeuds);
        List<Noeud> circuit    = new ArrayList<>();
 
        // 1. Choisir un nœud de départ aléatoire
        Noeud depart = nonVisites.get(new Random().nextInt(nonVisites.size()));
        circuit.add(depart);
        nonVisites.remove(depart);
 
        Noeud courant = depart;
 
        // 2. À chaque étape, aller vers le plus proche non visité
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
 
        // 3. Revenir au départ pour fermer le circuit
        circuit.add(depart);
 
        System.out.println("=== GLOUTON ===");
        // afficherCircuit(circuit);
        return circuit;
    }



}






