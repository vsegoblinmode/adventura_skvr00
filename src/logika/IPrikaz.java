package logika;

/**
 *  Třída, která implementuje toto rozhraní pracuje právě s jedním příkazem
 *@author     Roman ŠKvára
 */
interface IPrikaz {
	
	/**
     *  Provedení příkazu
     *  Parametry jsou parametry příkazu
     *  Konec a Pomoc parametry nepřijímají
     *  Seber, Poloz, Precti, Snez, Otevri, Prozkoumej prijimaji jeden
     */
    String provedPrikaz(String... parametry);
    
    // Vrací název příkazu
	String getNazev();
	
}
