package logika;
/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *
 *@author     Roman Škvára
 */
public class PrikazSeber implements IPrikaz{
    private static final String NAZEV = "seber";

    private final HerniPlan plan;

    /**
     * Konstruktor třídy
     *
     * @param plan abychom věděli co sebrat
     */
    public PrikazSeber(HerniPlan plan){
        this.plan = plan;
    }

    /**
     * Samotné provedení příkazu seber.
     * Vkládá předměty do inventáře.
     * Některé předměty jsou přenositelné a jíné ne.
     * @param parametry - co chceme sebrat
     * @return - vrací zprávu hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sebrat? Musíš zadat název předmětu.";
        }

        if (parametry.length > 1) {
            return "Moc věcí k sebrání. Prosím vyber si jednu";
        }

        String nazevVeci = parametry[0];

        Prostor aktualniProstor = plan.getAktualniProstor();
        if (aktualniProstor.obsahujeVec(nazevVeci)) {
            Vec pozadovanaVec = aktualniProstor.vyberVec(nazevVeci);
            if (pozadovanaVec == null) {
                return nazevVeci + " zde není.";
            }

            if (pozadovanaVec.jePrenositelna()) {
                boolean povedloSeVlozit = plan.getKapsy().vlozDoKapes(pozadovanaVec);
                if (povedloSeVlozit) {
                    // Speciální zpráva když se vyloví vidlička ze záchoda
                    if(pozadovanaVec.jePrenositelna()&&"vidlicka".equals(pozadovanaVec.getNazev())&&"zachod".equals(aktualniProstor.getNazev()))   {
                        plan.getKapsy().vlozDoKapes(pozadovanaVec);
                        return "Vylovil si vidličku ze záchoda **nechutné**" + "\n" + plan.getAktualniProstor().dlouhyPopis();
                    }
                    else {
                        return "Sebral jsi " + nazevVeci + "\n" + plan.getAktualniProstor().dlouhyPopis();
                    }
                }
                else {
                    aktualniProstor.vlozVec(pozadovanaVec);
                    return "Nemáš místo v inventáři pro " + nazevVeci + "\n" + plan.getAktualniProstor().dlouhyPopis();
                }
            }
            else {
                aktualniProstor.vlozVec(pozadovanaVec);
                return nazevVeci + " nejde sebrat" + "\n" + plan.getAktualniProstor().dlouhyPopis();
            }
        }

        return nazevVeci + " není v prostoru" + "\n" + plan.getAktualniProstor().dlouhyPopis();
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
