package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  
 *@author     Roman Škvára
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param hra, abychom věděli kam chodit a mohli hru ukončit
    */    
    public PrikazJdi(Hra hra) {
        this.hra = hra;
        this.plan = hra.getHerniPlan();
    }

    /**
     *  Samotné provedení příkazu "jdi". Vstupuje do existujících sousedních prostorů a na neexistující vyhodí chybu
     *@param parametry - jméno východu
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        boolean leziMydlo = plan.getAktualniProstor().obsahujeVec("mydlo");
        if (parametry.length == 0) {
            return "Kam mám jít? Musíš zadat název východu.";
        }

        if (parametry.length > 1) {
            return "Můžeš zadat pouze jeden východ, kterým chceš jít.";
        }

        String nazevVychodu = parametry[0];
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec potrebnyPredmet = aktualniProstor.getPotrebnyPredmet(nazevVychodu);

        // Kontrola přístupnosti prostoru (Některé prostory potřebují keycard nebo klíč)
        if (potrebnyPredmet != null && !plan.getKapsy().obsahujeVec(potrebnyPredmet.getNazev())) {
            return "Do tohoto prostoru nemáš přístup bez potřebného předmětu."+ "\n" + plan.getAktualniProstor().dlouhyPopis();
        }

        Prostor cilovyProstor = aktualniProstor.vratSousedniProstor(nazevVychodu);

        // Menší překažka ve hře, která může hráče překvapit
        if (leziMydlo && ("sprchy".equals(aktualniProstor.getNazev()) && "zachod".equals(nazevVychodu) || "pradelna".equals(nazevVychodu)))
        {
            // Pokud hráč jde z prostoru sprchy do zachodu nebo pradelny a nemá mydlo, prohraje
            hra.ukonciHru();
            return "Na mýdle si uklozl a rozbil si hlavu.";

        }

        if (cilovyProstor == null) {
            return "Tam se odsud jít nedá.";
        }

        // Výhra
        plan.setAktualniProstor(cilovyProstor);
        if(cilovyProstor.equals(plan.getVyherniProstor())){
            hra.vratEpilog();
            hra.ukonciHru();
        }
        return cilovyProstor.dlouhyPopis();
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
