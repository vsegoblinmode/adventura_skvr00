package logika;

import java.util.*;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * @author Roman Škvára
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private List<Vec> seznamVeci;
    private String potrebnyPredmet;

    /**
     * Vytvoření prostoru se zadaným popisem
     * @param nazev nazev prostoru
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        seznamVeci = new ArrayList<Vec>();
    }

    /**
     * Definuje východ z prostoru
     *
     * @param vedlejsi sousedící prostor
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů.
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return Objects.equals(this.nazev, druhy.nazev);
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/prostoru " + popis + ".\n"
                + "Veci: " + seznamVeci() + "\n"
                + popisVychodu();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: chodba ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr.
     *
     * @param nazevSouseda Jméno východu
     * @return Prostor, který se nachází za příslušným východem
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        for (Prostor sousedni : vychody) {
            if (sousedni.getNazev().equals(nazevSouseda)) {
                return sousedni;
            }
        }
        return null;
    }

    // Vkládá předmět do prostoru
    public void vlozVec(Vec vec){
        seznamVeci.add(vec);
    }

    // Rozhoduje jestli předmět je v prostoru
    public boolean obsahujeVec(String nazevVeci){
        for(Vec neco: seznamVeci){
            if (neco.getNazev().equals(nazevVeci)){
                return true;
            }
        }
        return false;
    }

    // Vybíra věc v prostoru se kterou chceme nějak pracovat (Napřiklad sníst nebo otevřit atd.)
    public Vec vyberVec(String nazevVeci) {
        Vec vybranaVec = null;
        Iterator<Vec> iterator = seznamVeci.iterator();

        while (iterator.hasNext()) {
            Vec vec = iterator.next();
            if (vec.getNazev().equals(nazevVeci)) {
                vybranaVec = vec;
                iterator.remove(); // Odstranit věc ze seznamu
                break; // Ukončit smyčku po nalezení shodující se věci
            }
        }

        return vybranaVec;
    }

    // Vypisuje seznam věcí v aktuálním prostoru pokud jsou v aktuální fázi hry viditelné
    private String seznamVeci(){
        String seznam = "";
        for( Vec vec : seznamVeci){
            if(vec.jeViditelna()) {
                seznam = seznam + vec.getNazev() + " ";
            }
        }
        return  seznam;
    }

    // Nastavuje potřebný předmět pro přístup do prostoru
    public void setPotrebnyPredmet(String predmet) {
        potrebnyPredmet = predmet;
    }

    // Konkrétní předměty
    public Vec getPotrebnyPredmet(String nazevVychodu) {
        if ("guard_room".equals(nazevVychodu)) {
            return new Vec("keycard", true, false, true, false, "", "Díky tomuhle se dostanu kam bych neměl.", false);
        }
        else if ("exit".equals(nazevVychodu)){
            return new Vec("exit_klic", true, false,false,false,"", "Ticket to freedom.", false);
        }
        // Pokud pro východ není potřebný žádný předmět, vrátíme null
        return null;
    }



}
