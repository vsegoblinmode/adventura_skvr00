package logika;

import java.util.HashSet;
import java.util.Set;

/**
 * Tato třída pracuje s inventářem hráče
 * @author Roman Škvára
 */
public class Kapsy {
    private final int omezeniKapes;
    private Set<Vec> obsahKapes;

    public Kapsy(int omezeniKapes){
        this.omezeniKapes = omezeniKapes;
        obsahKapes = new HashSet<>();
    }

    // Vkládá předmět do inventáře pokud je v něm místo a je předmět přenositelný
    public boolean vlozDoKapes(Vec neco){
        if(this.obsahKapes.size()< omezeniKapes && neco.jePrenositelna() ) {
            obsahKapes.add(neco);
            return true;
        }
        else if(this.obsahKapes.size()== omezeniKapes && neco.jePrenositelna() && neco.getNadKapacitu())
        {
            obsahKapes.add(neco);
            return true;
        }
        return false;
    }

    // Vyndavá předmět z inventáře
    public Vec vyndejZKapes(String nazevVeci){
        for (Vec neco: obsahKapes){
            if (neco.getNazev().equals(nazevVeci)){
                this.obsahKapes.remove(neco);
                return neco;
            }
        }
        return null;
    }

    // true pokud daný předmět je v inventáři, false pokud ne
    public boolean obsahujeVec(String nazevVeci) {
        for (Vec vec : obsahKapes) {
            if (vec.getNazev().equals(nazevVeci)) {
                return true;
            }
        }
        return false;
    }


    // Výpis obsahu inventáře
    public String dlouhyPopis(){
        String vypis = "Obsah kapes: ";
        for(Vec neco: obsahKapes){
            vypis += neco.getNazev() + " ";
        }
        return vypis;
    }

}
