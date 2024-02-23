import logika.Hra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestNelzeOtevrit {

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

        assertEquals(hra1.zpracujPrikaz("otevri postel"), "Tenhle předmět nelze otevřít."
                +"\n"+ "Jsi v mistnosti/prostoru studená cela s postelí." + "\n" + "Veci: postel " + "\n"+ "východy: chodba"+ "\n" +
                "Obsah kapes: ");
    }
}

