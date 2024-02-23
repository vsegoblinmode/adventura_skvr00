import logika.Hra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AbrakadabraTest {

    private Hra hra1;

    @BeforeEach
    public void setUp() {
        hra1 = new Hra();
    }

    @Test
    public void testHry() {
        assertEquals(hra1.vratUvitani(), "Vítejte!" + "\n" + "Probudil ses v cele a v celém vězení je hrobové ticho, musíš se dostat pryč nebo tu zemřeš"
                + "\n"+"Napište 'pomoc', pokud si nevíte rady, jak hrát dál." + "\n"
                +"\n"+ "Jsi v mistnosti/prostoru studená cela s postelí." + "\n" + "Veci: postel " + "\n"+ "východy: chodba");

        assertEquals(hra1.zpracujPrikaz("abrakadabra"), "Jsi v mistnosti/prostoru , která je tajná."
                +"\n"+ "Veci: truhla klic_od_truhly " + "\n" + "východy:" + "\n"+ "Obsah kapes: ");

        assertEquals(hra1.zpracujPrikaz("seber klic_od_truhly"),"Sebral jsi klic_od_truhly"
                +"\n"+ "Jsi v mistnosti/prostoru , která je tajná."
                +"\n"+ "Veci: truhla " + "\n" + "východy:" + "\n"+ "Obsah kapes: klic_od_truhly ");

        assertEquals(hra1.zpracujPrikaz("otevri truhla"),"Spousta věcí v téhle truhle"
                +"\n"+ "Jsi v mistnosti/prostoru , která je tajná."
                +"\n"+ "Veci: mec sekera palcat raketomet paratko kouzelny_item " + "\n" + "východy:" + "\n"+ "Obsah kapes: klic_od_truhly ");

        assertEquals(hra1.zpracujPrikaz("seber mec"),"Sebral jsi mec"
                +"\n"+ "Jsi v mistnosti/prostoru , která je tajná."
                +"\n"+ "Veci: sekera palcat raketomet paratko kouzelny_item " + "\n" + "východy:" + "\n"+ "Obsah kapes: mec klic_od_truhly ");

        assertEquals(hra1.zpracujPrikaz("seber sekera"),"Sebral jsi sekera"
                +"\n"+ "Jsi v mistnosti/prostoru , která je tajná."
                +"\n"+ "Veci: palcat raketomet paratko kouzelny_item " + "\n" + "východy:" + "\n"+ "Obsah kapes: mec klic_od_truhly sekera ");

        assertEquals(hra1.zpracujPrikaz("seber raketomet"),"Sebral jsi raketomet"
                +"\n"+ "Jsi v mistnosti/prostoru , která je tajná."
                +"\n"+ "Veci: palcat paratko kouzelny_item " + "\n" + "východy:" + "\n"+ "Obsah kapes: mec klic_od_truhly raketomet sekera ");

        assertEquals(hra1.zpracujPrikaz("seber paratko"),"Nemáš místo v inventáři pro paratko"
                +"\n"+ "Jsi v mistnosti/prostoru , která je tajná."
                +"\n"+ "Veci: palcat kouzelny_item paratko " + "\n" + "východy:" + "\n"+ "Obsah kapes: mec klic_od_truhly raketomet sekera ");

        assertEquals(hra1.zpracujPrikaz("seber kouzelny_item"),"Sebral jsi kouzelny_item"
                +"\n"+ "Jsi v mistnosti/prostoru , která je tajná."
                +"\n"+ "Veci: palcat paratko " + "\n" + "východy:" + "\n"+ "Obsah kapes: mec kouzelny_item klic_od_truhly raketomet sekera ");
        System.out.println(hra1.zpracujPrikaz("poloz raketomet"));
        System.out.println(hra1.zpracujPrikaz("seber paratko"));
        assertEquals(hra1.zpracujPrikaz("abrakadabra"), "Vrátil jsi se zpět do prostoru 'cela'."
                +"\n"+"Jsi v mistnosti/prostoru studená cela s postelí."
                +"\n"+ "Veci: postel " + "\n" + "východy: chodba" + "\n"+ "Obsah kapes: mec kouzelny_item klic_od_truhly raketomet sekera ");
    }
}