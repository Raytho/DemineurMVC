package demineurmvc;

/*********************
 * DÉMINEUR
 * Thomas DÉFOSSEZ & Hugo BRUNET
 * Version finale
 */

/********************
 * Conformément au Sujet du TP, où il est indiqué : 
 * 
 * "munir le démineur d'une vue graphique (pouvant être utilisée 
 * en remplacement ou en complément de la vue texte)"
 * 
 * Ici nous avons commenté le contrôle texte pour laisser 
 * place à la vue et au contrôle graphique.
 * 
 * 
 * Durant les vacances le code a été entièrement revu pour mieux
 * correspondre au pattern de conception MVC :
 * Le modèle contient les données et fait les calculs
 * Les vues permettent de tout afficher
 * Le controleur gère la partie et donc les interactions modèle/vue
 */

public class DemineurMVC{
    public static void main(String[] args) {
        DemineurControler c = new DemineurControler();
        c.main();
    }
}
