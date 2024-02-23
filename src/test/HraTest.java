import logika.Hra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HraTest {

    private Hra hra1;

    @BeforeEach
    public void setUp(){
        hra1= new Hra();
    }

    @Test
    public void testHry(){
//Úvod
        assertEquals(hra1.vratUvitani(), "Vítejte!" + "\n" + "Probudil ses v cele a v celém vězení je hrobové ticho, musíš se dostat pryč nebo tu zemřeš"
        + "\n"+"Napište 'pomoc', pokud si nevíte rady, jak hrát dál." + "\n"
        +"\n"+ "Jsi v mistnosti/prostoru studená cela s postelí." + "\n" + "Veci: postel " + "\n"+ "východy: chodba");
//1.krok JDI chodba
        assertEquals(hra1.zpracujPrikaz("jdi chodba"), "Jsi v mistnosti/prostoru" +
                " chodba se 4 možnými cestami." +  "\n" +
                "Veci: " +"\n" +
                "východy: sprchy guard_room cela jidelna" +"\n" +
                "Obsah kapes: ");

//2.krok JDI sprchy
        assertEquals(hra1.zpracujPrikaz("jdi sprchy"), "Jsi v mistnosti/prostoru" +
                " kde by ti mýdlo spadnout nemělo." +"\n" +
                "Veci: mydlo " +"\n" +
                "východy: zachod chodba pradelna" +"\n" +
                "Obsah kapes: ");

//3. krok SEBER mydlo (pokud mydlo neseberes uklouznes na nem, umres a hra konci)
        assertEquals(hra1.zpracujPrikaz("seber mydlo"), "Sebral jsi mydlo" +"\n" +
                "Jsi v mistnosti/prostoru" +
                " kde by ti mýdlo spadnout nemělo." +"\n" +
                "Veci: " +"\n" +
                "východy: zachod chodba pradelna" +"\n" +
                "Obsah kapes: mydlo " );

//4. krok JDI zachod
        assertEquals(hra1.zpracujPrikaz("jdi zachod"), "Jsi v mistnosti/prostoru" +
                " kde to smrdí ikdyž je vězení prázdné." +"\n" +
                "Veci: vidlicka zachod " +"\n" +
                "východy: sprchy" +"\n" +
                "Obsah kapes: mydlo ");

//5. krok SEBER vidlicka
        assertEquals(hra1.zpracujPrikaz("seber vidlicka"), "Vylovil si vidličku ze záchoda **nechutné**" +"\n" +
                "Jsi v mistnosti/prostoru" +
                " kde to smrdí ikdyž je vězení prázdné." +"\n" +
                "Veci: zachod " +"\n" +
                "východy: sprchy" +"\n" +
                "Obsah kapes: vidlicka mydlo ");

//6.krok JDI sprchy
        assertEquals(hra1.zpracujPrikaz("jdi sprchy"), "Jsi v mistnosti/prostoru" +
                " kde by ti mýdlo spadnout nemělo." +"\n" +
                "Veci: " +"\n" +
                "východy: zachod chodba pradelna" +"\n" +
                "Obsah kapes: vidlicka mydlo ");

//7. krok JDI pradelna
        assertEquals(hra1.zpracujPrikaz("jdi pradelna"), "Jsi v mistnosti/prostoru" +
                " která by se měla jmenovat spíš prdelna jak je to tu zatuchlý." +"\n" +
                "Veci: popsany_papirek pracka " +"\n" +
                "východy: sprchy" +"\n" +
                "Obsah kapes: vidlicka mydlo ");

//8. krok PRECTI popsany_papirek
        assertEquals(hra1.zpracujPrikaz("precti popsany_papirek"), "Exit je v guard roomu, ale musíš od něj najít kartu zkus se podívat v jídelně." +"\n" +
                "Jsi v mistnosti/prostoru" +
                " která by se měla jmenovat spíš prdelna jak je to tu zatuchlý." +"\n" +
                "Veci: pracka " +"\n" +
                "východy: sprchy" +"\n" +
                "Obsah kapes: vidlicka mydlo ");

//9. krok JDI sprchy
        assertEquals(hra1.zpracujPrikaz("jdi sprchy"), "Jsi v mistnosti/prostoru" +
                " kde by ti mýdlo spadnout nemělo." +"\n" +
                "Veci: " +"\n" +
                "východy: zachod chodba pradelna" +"\n" +
                "Obsah kapes: vidlicka mydlo ");

//10.krok JDI chodba
        assertEquals(hra1.zpracujPrikaz("jdi chodba"), "Jsi v mistnosti/prostoru" +
                " chodba se 4 možnými cestami." +"\n" +
                "Veci: " +"\n" +
                "východy: sprchy guard_room cela jidelna" +"\n" +
                "Obsah kapes: vidlicka mydlo ");

//11.krok JDI jidelna
        assertEquals(hra1.zpracujPrikaz("jdi jidelna"), "Jsi v mistnosti/prostoru" +
                " kde se ani nedá věřit, že tady někdo někdy jedl." +"\n" +
                "Veci: stul plesnive_spagety " +"\n" +
                "východy: posilovna chodba" +"\n" +
                "Obsah kapes: vidlicka mydlo ");

//12.krok SNEZ plesnive_spagety (bez nasi supr vidlicky ze zachoda to nepujde)
        assertEquals(hra1.zpracujPrikaz("snez plesnive_spagety"), "Tak to bylo nechutné, ale co je sakra tohle" +"\n" +
                "Jsi v mistnosti/prostoru" +
                " kde se ani nedá věřit, že tady někdo někdy jedl." +"\n" +
                "Veci: stul napis_na_dne_talire " +"\n" +
                "východy: posilovna chodba" +"\n" +
                "Obsah kapes: vidlicka mydlo ");

//13. krok PRECTI napis_na_dne_talire
        assertEquals(hra1.zpracujPrikaz("precti napis_na_dne_talire"), "Poslední bachař co tu byl zapomněl svojí keycard na benchpressu." +"\n" +
                "Jsi v mistnosti/prostoru" +
                " kde se ani nedá věřit, že tady někdo někdy jedl." +"\n" +
                "Veci: stul napis_na_dne_talire " +"\n" +
                "východy: posilovna chodba" +"\n" +
                "Obsah kapes: vidlicka mydlo ");

//14. krok JDI posilovna
        assertEquals(hra1.zpracujPrikaz("jdi posilovna"), "Jsi v mistnosti/prostoru" +
                " kde se zvedá železo a žerou steroidy." +"\n" +
                "Veci: benchpress keycard " +"\n" +
                "východy: jidelna" +"\n" +
                "Obsah kapes: vidlicka mydlo ");

//15. krok SEBER keycard
        assertEquals(hra1.zpracujPrikaz("seber keycard"), "Sebral jsi keycard" +"\n" +
                "Jsi v mistnosti/prostoru" +
                " kde se zvedá železo a žerou steroidy." +"\n" +
                "Veci: benchpress " +"\n" +
                "východy: jidelna" +"\n" +
                "Obsah kapes: keycard vidlicka mydlo ");
//16.krok JDI jidelna
        assertEquals(hra1.zpracujPrikaz("jdi jidelna"), "Jsi v mistnosti/prostoru" +
                " kde se ani nedá věřit, že tady někdo někdy jedl." +"\n" +
                "Veci: stul napis_na_dne_talire " +"\n" +
                "východy: posilovna chodba" +"\n" +
                "Obsah kapes: keycard vidlicka mydlo ");

//17.krok JDI chodba
        assertEquals(hra1.zpracujPrikaz("jdi chodba"), "Jsi v mistnosti/prostoru" +
                " chodba se 4 možnými cestami." +"\n" +
                "Veci: " +"\n" +
                "východy: sprchy guard_room cela jidelna" +"\n" +
                "Obsah kapes: keycard vidlicka mydlo ");
//17.krok JDI guard_room
        assertEquals(hra1.zpracujPrikaz("jdi guard_room"), "Jsi v mistnosti/prostoru" +
                " kde se scházeli pigs." +"\n" +
                "Veci: trezor " +"\n" +
                "východy: exit chodba" +"\n" +
                "Obsah kapes: keycard vidlicka mydlo ");

//18. krok OTEVRI trezor
        assertEquals(hra1.zpracujPrikaz("otevri trezor"), "Ty idioti ho ani nezamkli, není divu že skončili jako bachaři" +"\n" +
                "Hele klíč od exitu!"+"\n" +
                "Jsi v mistnosti/prostoru" +
                " kde se scházeli pigs." +"\n" +
                "Veci: exit_klic " +"\n" +
                "východy: exit chodba" +"\n" +
                "Obsah kapes: keycard vidlicka mydlo ");

//19. krok seber exit_klic
        assertEquals(hra1.zpracujPrikaz("seber exit_klic"), "Sebral jsi exit_klic" +"\n" +
                "Jsi v mistnosti/prostoru" +
                " kde se scházeli pigs." +"\n" +
                "Veci: " +"\n" +
                "východy: exit chodba" +"\n" +
                "Obsah kapes: keycard vidlicka exit_klic mydlo ");
//20. krok JDI exit
        assertEquals(hra1.zpracujPrikaz("jdi exit"), "Jsi v mistnosti/prostoru" +
                " kde už jsi svobodný." +"\n" +
                "Veci: " +"\n" +
                "východy:" +"\n" +
                "Obsah kapes: keycard vidlicka exit_klic mydlo ");
        assertEquals(hra1.vratEpilog(), "Tohle je konec, díky za zahrání "+"\n" +
                " Autor:Roman Škvára");

//Konec hry


    }

}