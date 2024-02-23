/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;





/**
 *  Uživatelské rozhraní, které hra musí implementovat
 *
 *@author     Roman Škvára
 */
public interface IHra
{
    // Úvodní zpráva pro hráče
    String vratUvitani();
    
    // Závěrečná zpráva pro hráče
    String vratEpilog();
    
    // Konec hry
     boolean konecHry();
     
     // Zpracování příkazu
     String zpracujPrikaz(String radek);
   
    
     // Odkaz na herní plán
     HerniPlan getHerniPlan();

}
