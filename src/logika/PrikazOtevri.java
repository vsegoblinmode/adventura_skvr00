package logika;
/**
 *  Třída PrikazOtevri implementuje pro hru příkaz otevri.
 *
 *@author     Roman Škvára
 */
public class PrikazOtevri implements IPrikaz {
    private static final String NAZEV = "otevri";
    private HerniPlan herniPlan;

    /**
     * Konstruktor třídy
     *
     * @param herniPlan abychom věděli co otevřít
     */
    public PrikazOtevri(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Samotné provedení příkazu otevři.
     * Otevřít jde ve hře pouze jeden předmět (trezor)
     * Po jeho otevření se vytvoří předmět exit_klic (jakože je v tom trezoru)
     * @param parametry - co chceme otevřít
     * @return - vrací zprávu hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co chceš otevřít?";
        }

        String nazev = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        if ("trezor".equals(nazev)) {
            if (aktualniProstor.obsahujeVec("trezor")) {
                aktualniProstor.vlozVec(new Vec("exit_klic", true, false, true, false, "", "Díky tomuhle se dostanu kam bych neměl.", false));
                aktualniProstor.vyberVec("trezor").setViditelny(false);
                return "Ty idioti ho ani nezamkli, není divu že skončili jako bachaři" +"\n"+"Hele klíč od exitu!" + "\n" + herniPlan.getAktualniProstor().dlouhyPopis();

            }
            else {
                return "Nic takového tu není"+ "\n" + herniPlan.getAktualniProstor().dlouhyPopis();
            }
        }
        else if("truhla".equals(nazev) && herniPlan.getKapsy().obsahujeVec("klic_od_truhly"))
        {
            if(aktualniProstor.obsahujeVec("truhla")) {
                aktualniProstor.vlozVec(new Vec("mec", true, false, true, false, "", "Zbraň", false));
                aktualniProstor.vlozVec(new Vec("sekera", true, false, true, false, "", "Až se dostanu ven pokácím nějaký strom", false));
                aktualniProstor.vlozVec(new Vec("palcat", true, false, true, false, "", "Medival typebeat", false));
                aktualniProstor.vlozVec(new Vec("raketomet", true, false, true, false, "", "Jsem ještě vůbec ve vězení?", false));
                aktualniProstor.vlozVec(new Vec("paratko", true, false, true, false, "", "Na bordel mezi zuby", false));
                aktualniProstor.vlozVec(new Vec("kouzelny_item", true, false, true, false, "", "Zbraň", true));
                aktualniProstor.vyberVec("truhla").setViditelny(false);

                return "Spousta věcí v téhle truhle"+ "\n" + herniPlan.getAktualniProstor().dlouhyPopis();
            }
            else {
                return "Nic takového tu není"+ "\n" + herniPlan.getAktualniProstor().dlouhyPopis();
            }
        }
        else {
            return "Tenhle předmět nelze otevřít."+ "\n" + herniPlan.getAktualniProstor().dlouhyPopis();
        }
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

