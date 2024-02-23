package logika;

/**
 *  Třída PrikazKonec implementuje pro hru příkaz konec.
 *  
 *@author     Roman Škvára
 */

public class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "konec";

    private Hra hra;

    /**
     *  Konstruktor třídy
     *  
     *  @param hra odkaz na hru, která má být příkazem konec ukončena
     */    
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * Příkaz konec nepřijímá žádný parametr
     *
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Ukončit co? Nechápu, proč jste zadal druhé slovo.";
        }
        else {
            hra.setKonecHry(true);
            return "Hra ukončena příkazem konec";
        }
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
