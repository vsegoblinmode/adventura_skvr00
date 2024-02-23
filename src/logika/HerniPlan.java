package logika;


/**
 *
 *  Tato třída vytváří plán celé hry (prostory, předměty)
 *  a řeší propojení prostorů
 *
 *@author     Roman Škvára
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Prostor vyherniProstor; //PŘIDÁNÍ VÝHERNÍHO PROSTORU
    private Prostor secretProstor;

    private Kapsy kapsy;
    private Prostor puvodniProstor;
    private static final int OMEZENI_KAPES = 4;
    
     /**
      *  Konstruktor vytvářející prostory a jejich propojení
      *  Výchozím prostorem je cela kde celá hra začíná
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví cela.
     */
    private void zalozProstoryHry() {
        // Prostory
        Prostor cela = new Prostor("cela", "studená cela s postelí" );
        Prostor chodba = new Prostor("chodba", "chodba se 4 možnými cestami");
        Prostor sprchy = new Prostor("sprchy", "kde by ti mýdlo spadnout nemělo");
        Prostor zachod = new Prostor("zachod", "kde to smrdí ikdyž je vězení prázdné");
        Prostor pradelna = new Prostor("pradelna", "která by se měla jmenovat spíš prdelna jak je to tu zatuchlý");
        Prostor jidelna = new Prostor("jidelna", "kde se ani nedá věřit, že tady někdo někdy jedl");
        Prostor posilovna = new Prostor("posilovna", "kde se zvedá železo a žerou steroidy");
        Prostor guardRoom = new Prostor("guard_room", "kde se scházeli pigs");
        guardRoom.setPotrebnyPredmet("keycard"); // Prostor přístupný pouze s daným předmětem
        Prostor exit = new Prostor("exit", "kde už jsi svobodný");
        exit.setPotrebnyPredmet("exit_klic");
        Prostor secret = new Prostor("secret", ", která je tajná");

        // Propojení
        cela.setVychod(chodba);
        chodba.setVychod(cela);
        chodba.setVychod(sprchy);
        chodba.setVychod(jidelna);
        chodba.setVychod(guardRoom);
        sprchy.setVychod(chodba);
        sprchy.setVychod(zachod);
        sprchy.setVychod(pradelna);
        zachod.setVychod(sprchy);
        pradelna.setVychod(sprchy);
        jidelna.setVychod(chodba);
        jidelna.setVychod(posilovna);
        posilovna.setVychod(jidelna);
        guardRoom.setVychod(chodba);
        guardRoom.setVychod(exit);

        // Předměty
        cela.vlozVec(new Vec("postel", false, false, true, false, "", "Zde se spí.", false));
        sprchy.vlozVec(new Vec("mydlo", true, false, true, false, "", "To by ti tu spadnout nemělo.", false));
        zachod.vlozVec(new Vec("vidlicka", true, false, true, false, "", "Abys nejedl rukama.", false));
        zachod.vlozVec(new Vec("zachod", false, false, true, false, "", "Zde můžeš vykonat svou potřebu.", false));
        pradelna.vlozVec(new Vec("popsany_papirek", false, true, true, false,
                "Exit je v guard roomu, ale musíš od něj najít kartu zkus se podívat v jídelně.", "Zpráva od tajného ctitele?", false));
        pradelna.vlozVec(new Vec("pracka", false, false,true, false,
                "", "Zajímalo by mě jestli tam nemám některé moje ztracené ponožky.", false));
        jidelna.vlozVec(new Vec("stul", false, false, true, false, "", "Co chceš vědět o stolu?", false));
        jidelna.vlozVec(new Vec("plesnive_spagety", false, false, true, true, "",
                "Něco mí říká, že už to tu nějakou dobu leží.", false));
        jidelna.vlozVec(new Vec("napis_na_dne_talire", false, true, false, false,
                "Poslední bachař co tu byl zapomněl svojí keycard na benchpressu.", "Lidi ve vězení jsou zřejmě opravdu kreativní.", false));
                posilovna.vlozVec(new Vec("benchpress", false, false, true, false, "", "Ať ty kozy rostou!", false));
        guardRoom.vlozVec(new Vec("trezor", false, false, true, false, "", "Zde by mohly být schované nějaké cennosti.", false));

        secret.vlozVec(new Vec("truhla", false, false, true, false, "", "Tajná truhla plná překvapení.", false));
        secret.vlozVec(new Vec("klic_od_truhly", true, false, true, false, "", " Klíč od truhly, co víc chceš?", false));


        kapsy = new Kapsy(OMEZENI_KAPES); //Inventář

        vyherniProstor = exit;    //PŘIDÁNÍ VÝHERNÍHO PROSTORU
        aktualniProstor = cela;  // hra začíná v cele
        secretProstor = secret;
    }
    

    // Aktuální prostor
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    // Nastavení aktuálního prostoru - používá se při přechodu
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }

    // Výherní prostor
    public Prostor getVyherniProstor(){
        return vyherniProstor;
    }

    // Inventář
    public Kapsy getKapsy(){
        return kapsy;
    }
    public Prostor getPuvodniProstor() {
        return puvodniProstor;
    }
    public void setPuvodniProstor(Prostor prostor) {
        puvodniProstor = prostor;
    }
    public Prostor getSecretProstor()   {
        return secretProstor;
    }
}
