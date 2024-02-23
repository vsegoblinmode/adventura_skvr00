package logika;
/**
 * Trida Vec - popisuje jednotlivé předměty ve hře
 *
 * @author Roman Škvára
 */
public class Vec {

    private String nazev;
    private boolean prenositelnost;
    private boolean citelnost;
    private boolean viditelnost;
    private boolean jedlost;
    private String vzkaz;
    private String popis;
    private boolean nadKapacitu;

    /**
     *
     * @param nazev
     * @param prenositelnost
     * @param citelnost
     * @param viditelnost
     * @param jedlost
     * @param vzkaz
     * @param popis
     */
    public Vec(String nazev, boolean prenositelnost, boolean citelnost, boolean viditelnost, boolean jedlost, String vzkaz, String popis, boolean nadKapacitu) {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.citelnost = citelnost;
        this.viditelnost = viditelnost;
        this.jedlost = jedlost;
        this.vzkaz = vzkaz;
        this.popis = popis;
        this.nadKapacitu = nadKapacitu;
    }
    // Vrací název věci
    public String getNazev(){
        return  nazev;
    }

    // Vrací přenositelnost
    public boolean jePrenositelna(){
        return prenositelnost;
    }
    // Vrací čitelnost
    public boolean jeCitelna(){return citelnost;}

    // Vrací viditelnost
    public boolean jeViditelna(){return viditelnost;}

    // Vrací jedlost
    public boolean jeJedla(){return jedlost;}

    // Vrací vzkaz
    public String getVzkaz(){return vzkaz;}

    // Nastavuje viditelnost předmětu
    public void setViditelny(boolean viditelnost) {
        this.viditelnost = viditelnost;
    }

    // Vrací popis
    public String getPopis() {return popis;}
    public boolean getNadKapacitu() {return nadKapacitu;}

}
