import java.util.LinkedList;
public class Noeud {
    int id;
    LinkedList<Arc> succ ;   
            boolean mark = false;
    public Noeud(int id){
        this.id = id;
        this.succ = new LinkedList<Arc>();  
        this.mark = false;
    } 
     @Override
     public String toString(){
        String s  =""+ this.id;
        for(Arc a : this.succ){
            s += " -> " + a.cible.getId() ;
        }
        return s ;
     }  



       public String toStringOnlyId(){
        String s  =""+ this.id;
        return s ;
     }



    public int getId(){
        return this.id;
    }
// // prof
// public void parcours(){
//     this.mark = true ;
//     System.out.println("the id   "+ this.id);
//     for(Arc a : this.succ){
//         if(!a.cible.mark){
//             a.cible.parcours();
//         }
//     }
//   }







    
  public boolean hasSuccesseur(int j){
    for(Arc a : this.succ){
        if(a.cible.getId() == j){
            return true ;
        }
    }
    return false ;
  }
}