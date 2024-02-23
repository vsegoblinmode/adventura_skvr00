package logika;

/**
 *  Třída PrikazPomoc implementuje pro hru příkaz pomoc.
 *  
 *@author     Roman Škvára
 *  
 */
public class PrikazPomoc implements IPrikaz {
    
    private static final String NAZEV = "pomoc";
    private SeznamPrikazu platnePrikazy;
    
    
     /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů,
    *                       které je možné ve hře použít,
    *                       aby je nápověda mohla zobrazit uživateli. 
    */    
    public PrikazPomoc(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    /**
     *  Základní nápověda s platnými příkazy
     *  
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "Tvým úkolem je dostat se pryč z vězení\n"
        + "prohledej to tu a dostaň se ven\n"
        + "\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu();
    }
    
     /**
     *  Metoda vrací název příkazu
     *  
     *  @ return nazev prikazu
     */
    @Override
      public String getNazev() {
        return NAZEV;
     }

}
