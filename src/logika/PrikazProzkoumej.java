package logika;
/**
 *  Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 *
 *@author     Roman Škvára
 */
public class PrikazProzkoumej implements IPrikaz {
    private static final String NAZEV = "prozkoumej";
    private HerniPlan herniPlan;
    /**
     * Konstruktor třídy
     *
     * @param herniPlan abychom věděli co prozkoumat
     */
    public PrikazProzkoumej(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }
    /**
     * Samotné provedení příkazu prozkoumej.
     * Prozkoumat jdou ve hře věechny předměty, jak v prostoru tak v inventáři.
     * Zobrazí krátký popis předmětu
     * @param parametry - co chceme prozkoumat
     * @return - vrací zprávu hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co chceš prozkoumat? Musíš zadat název předmětu.";
        }

        String nazevPredmetu = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Kapsy kapsy = herniPlan.getKapsy();

        // Hledání předmětu v prostoru
        if (aktualniProstor.obsahujeVec(nazevPredmetu)) {
            Vec predmet = aktualniProstor.vyberVec(nazevPredmetu);
            aktualniProstor.vlozVec(predmet);
            return predmet.getPopis()+ "\n" + herniPlan.getAktualniProstor().dlouhyPopis();
        }

        // Hledání předmětu v inventáři
        if (kapsy.obsahujeVec(nazevPredmetu)) {
            Vec predmet = kapsy.vyndejZKapes(nazevPredmetu);
            kapsy.vlozDoKapes(predmet);
            return predmet.getPopis()+ "\n" + herniPlan.getAktualniProstor().dlouhyPopis();
        }

        return "Takový předmět tu není.";
    }
    /**
     *  Metoda vrací název příkazu
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
