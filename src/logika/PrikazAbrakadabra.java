package logika;

public class PrikazAbrakadabra implements IPrikaz{
    private static final String NAZEV = "abrakadabra";
    private HerniPlan herniPlan;

    public PrikazAbrakadabra(HerniPlan herniPlan){
        this.herniPlan=herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (herniPlan.getPuvodniProstor() == null) {
            herniPlan.setPuvodniProstor(herniPlan.getAktualniProstor()); // Uložení původního prostoru
        }

        Prostor secretProstor = herniPlan.getSecretProstor();
        if (herniPlan.getAktualniProstor().getNazev().equals("secret")) {
            herniPlan.setAktualniProstor(herniPlan.getPuvodniProstor()); // Přesun hráče zpět do původního prostoru
            herniPlan.setPuvodniProstor(null); // Vynulování původního prostoru pro další použití
            return "Vrátil jsi se zpět do prostoru '" + herniPlan.getAktualniProstor().getNazev() + "'." + "\n" + herniPlan.getAktualniProstor().dlouhyPopis(); // Zpráva o návratu do původního prostoru
        } else {
            herniPlan.setAktualniProstor(secretProstor); // Přesun hráče do prostoru "secret"
            return secretProstor.dlouhyPopis(); // Výpis popisu prostoru "secret"
        }
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }
}
