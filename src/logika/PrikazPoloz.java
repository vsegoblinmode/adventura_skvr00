package logika;
/**
 *  Třída PrikazPoloz implementuje pro hru příkaz poloz.
 *
 *@author     Roman Škvára
 */
public class PrikazPoloz implements IPrikaz{

    private static final String NAZEV = "poloz";

    private final HerniPlan plan;
    /**
     * Konstruktor třídy
     *
     * @param plan abychom věděli co položit
     */
    public PrikazPoloz(HerniPlan plan){
        this.plan = plan;
    }

    /**
     * Samotné provedení příkazu poloz.
     * Položit jde pouze předmět co je v hráčově inventáři
     *
     * @param parametry - co chceme otevřít
     * @return - vrací zprávu hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0){
            return "Musíš zadat co chceš položit";
        }

        if (parametry.length > 1){
            return "Musíš si vybrat pouze jednu věc k položení";
        }

        String nazevVeci = parametry[0];
        Vec pozadovanaVec = plan.getKapsy().vyndejZKapes(nazevVeci);

        if (pozadovanaVec != null){

            plan.getAktualniProstor().vlozVec(pozadovanaVec);
            return nazevVeci + " jsi položil v prostoru"+ "\n" + plan.getAktualniProstor().dlouhyPopis();
        }

        return  nazevVeci + " v kapsách nemáš"+ "\n" + plan.getAktualniProstor().dlouhyPopis();
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
