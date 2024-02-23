package logika;
/**
 *  Třída PrikazSnez implementuje pro hru příkaz snez.
 *
 *@author     Roman Škvára
 */
public class PrikazSnez implements IPrikaz {
    private static final String NAZEV = "snez";
    private HerniPlan herniPlan;
    /**
     * Konstruktor třídy
     *
     * @param herniPlan abychom věděli co snist
     */
    public PrikazSnez(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }
    /**
     * Samotné provedení příkazu snez.
     * Ve hře je jeden předmět co jde sníst (plesnive_spagety)
     * @param parametry - co chceme sníst
     * @return - vrací zprávu hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám sníst?";
        }

        String nazevPredmetu = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Vec predmet = aktualniProstor.vyberVec(nazevPredmetu);

        if (predmet != null) {
            if (predmet.jeViditelna() && predmet.jeJedla()) {
                // plesnive_spagety jde sníst pouze pokud máme v inventáři vidličku a odhalí tajný vzkaz
                // také se po snězení v posilovně vytvoří keycard aby tento krok nešel přeskočit
                if ("plesnive_spagety".equals(predmet.getNazev()) && herniPlan.getKapsy().obsahujeVec("vidlicka")) {
                    aktualniProstor.vlozVec(new Vec("napis_na_dne_talire", false, true, true, false,
                            "Poslední bachař co tu byl zapomněl svojí keycard na benchpressu.", "Lidi ve vězení jsou zřejmě opravdu kreativní.", false));
                    aktualniProstor.vratSousedniProstor("posilovna").vlozVec(new Vec("keycard", true, false, true,
                            false, "", "Díky tomuhle se dostanu kam bych neměl.", false));
                    return "Tak to bylo nechutné, ale co je sakra tohle"+ "\n" + herniPlan.getAktualniProstor().dlouhyPopis();
                }
                else if("plesnive_spagety".equals(predmet.getNazev())&& !herniPlan.getKapsy().obsahujeVec("vidlicka")){
                    return "Bez přiboru takovej hnus jíst nebudu"+ "\n" + herniPlan.getAktualniProstor().dlouhyPopis();
                }
            }
        }
        else {
            return "Takový předmět tu není"+ "\n" + herniPlan.getAktualniProstor().dlouhyPopis();
        }
        aktualniProstor.vlozVec(predmet);
        return "Toto sníst nemůžeš" + "\n" + herniPlan.getAktualniProstor().dlouhyPopis();

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
