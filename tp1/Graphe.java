import java.io.File;
import java.io.FileNotFoundException;
// import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class  Graphe{
  LinkedList<Noeud> noeuds ;
  // HashMap<Integer, Noeud> hmap ;
  public Graphe(){
    this.noeuds = new LinkedList<Noeud>() ;
    // this.hmap = new HashMap<Integer, Noeud>() ;
    }
//     public Graphe(String file){
//       try {
// File monFichier = new File(file);
// Scanner sc = new Scanner(monFichier);
// while (sc.hasNextLine()) {
// String data = sc.nextLine();
// System.out.println(data);
// }
// sc.close();
// }
// catch (FileNotFoundException e) {
// System.out.println("An error occurred.");
// }
    
//     }


    
    public Graphe(int k){
        this.noeuds = new LinkedList<Noeud>() ;
        for(int i=0;i<k;i++){
            this.noeuds.add(new Noeud(i)) ;
            // this.hmap.put(i, new Noeud(i)) ;
        }
    }
    public void addNoeud(int n){
        for(int i = 0   ; i  < noeuds.size()  ; i++) {
                            //   System.out.println("n " + n +"  __id    :"  +noeuds.get(i).getId() );
            if(noeuds.get(i).getId() == n){
                System.out.println("L'element existe deja  ");
                return ;
            }    
        }
        noeuds.add(new Noeud(n));
       }

    // }
    public Noeud getNoeud(int n){
    //   Noeud el =  hmap.get(n) ;
    //   if(el == null){
    //             System.out.println("L'element n'esist pas ");

    //   }
    //   return el  ; 

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
        if(source != null && cible != null && !source.hasSuccesseur(y)){
            Arc a = new Arc(source, cible) ;
            source.succ.add(a) ;  }
}
public String toString(){
    String s = "Graphe : \n" ;
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
  

//     public void parcours(){
//     this.mark = true ;
//     System.out.println("the id   "+ this.id);
//     for(Arc a : this.succ){
//         if(!a.cible.mark){
//             a.cible.parcours();
//         }
//     }
//   }











}


// le pire des cas  c'est l'element c'est le dernier 
// la complixitty en temps  :  t ^ n  ?? 