package logika;
/**
 *  Třída PrikazPrecti implementuje pro hru příkaz precti.
 *
 *@author     Roman Škvára
 */
public class PrikazPrecti implements IPrikaz{
    private static final String NAZEV = "precti";
    private final HerniPlan plan;
    /**
     * Konstruktor třídy
     *
     * @param plan abychom věděli co přečíst
     */
    public PrikazPrecti(HerniPlan plan){
        this.plan = plan;
    }

    /**
     * Samotné provedení příkazu přečti.
     * Ve hře jsou 2 předměty které mají nějakou zprávu a ta jde přečíst
     * Jsou to vodítka k dohrání hry
     * @param parametry - co chceme přečíst
     * @return - vrací zprávu hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám přečíst? Musíš zadat název předmětu.";
        }

        if (parametry.length > 1) {
            return "Příliš mnoho parametrů. Zadej pouze název předmětu.";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();

        if (aktualniProstor.obsahujeVec(nazevVeci)) {
            Vec pozadovanaVec = aktualniProstor.vyberVec(nazevVeci);
            if (pozadovanaVec == null) {
                return "Předmět '" + nazevVeci + "' zde není" + "\n" + plan.getAktualniProstor().dlouhyPopis();
            }

            if (pozadovanaVec.jeCitelna()) {
                return pozadovanaVec.getVzkaz()+ "\n" + plan.getAktualniProstor().dlouhyPopis();
            }
            else {
                aktualniProstor.vlozVec(pozadovanaVec); // Vložíme předmět zpět do prostoru
                return "Předmět '" + nazevVeci + "' nelze přečíst." + "\n" + plan.getAktualniProstor().dlouhyPopis();
            }
        }

        return "Předmět '" + nazevVeci + "' není v prostoru." + "\n" + plan.getAktualniProstor().dlouhyPopis();
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
