


public class Arc{
    Noeud source ; 
     Noeud cible ;
     int Weight ; 
     public Arc(Noeud x, Noeud y , int p ){
          this.source = x ;
    this.cible = y ; 
    this.Weight = p ; 
     }
        public Arc(Noeud x, Noeud y ){
          this.source = x ;
    this.cible = y ; 
     }
     public String toString(){
                  return "(" + source.toStringOnlyId() + "," + cible.toStringOnlyId() + ", Weight=" + Weight + ")";

     }
}
