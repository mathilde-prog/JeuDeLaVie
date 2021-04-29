import java.awt.FontFormatException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import src.JeuDeLaVie;
import src.JeuDeLaVieUI; 
import src.CompteurGenerationEtCellulesEnVie;

/**
 * La classe LancementJeuDeLaVie permet au joueur de lancer le jeu. 
 * @author Mathilde MOTTAY 
 */
public class LancementJeuDeLaVie {

    /**
     * Programme principal 
     * Lance le jeu 
     * @param args
     * @throws IOException
     * @throws FontFormatException
     */
    public static void main(String[] args) throws IOException, FontFormatException{
        JeuDeLaVie jeu = new JeuDeLaVie(); 

        // 2 observateurs
        JeuDeLaVieUI jeuUI = new JeuDeLaVieUI(jeu); 
        jeu.attacheObservateur(jeuUI);
        CompteurGenerationEtCellulesEnVie cptGen = new CompteurGenerationEtCellulesEnVie(jeu); 
        jeu.attacheObservateur(cptGen);

        while(true){
            if(!jeu.estEnPause()){
                jeu.calculerGenerationSuivante();               
            }
            try {
                TimeUnit.MILLISECONDS.sleep(60000/jeu.getVitesse()); 
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}