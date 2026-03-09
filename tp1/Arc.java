


public class Arc{
    Noeud source ; 
     Noeud cible ;
     public Arc(Noeud x, Noeud y){
          this.source = x ;
    this.cible = y ; 
     }
     public String toString(){
            return "("+source.toStringOnlyId()+","+cible.toStringOnlyId()+")" ;
     }
}
