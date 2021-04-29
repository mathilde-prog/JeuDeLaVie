package src.Themes; 

import java.awt.*;
import java.io.IOException;
import src.JeuDeLaVie;
import src.JeuDeLaVieUI;
import src.PanelImage;

/**
 * La classe Theme est une classe abstraite représentant le thème du jeu. 
 * @author Mathilde MOTTAY 
 */
public abstract class Theme {

    // Jeu de la vie 
    protected JeuDeLaVie jeu; 

    // Interface graphique du jeu de la vie 
    private JeuDeLaVieUI jeuUI; 

    // Zoom du jeu 
    protected int zoom; 
    
    /**
     * Constructeur Theme. 
     * @param jeu Jeu de la vie 
     * @param jeuUI Interface graphique du jeu de la vie 
     * @throws IOException
     */
    public Theme(JeuDeLaVie jeu, JeuDeLaVieUI jeuUI) throws IOException {
        this.jeu = jeu; 
        this.jeuUI = jeuUI; 
    }
    
    /**
     * Retourne le chemin de l'image en arrière-plan du thème 
     * @return Chemin de l'image en arrière-plan du thème 
     */
    public abstract String getBackground(); 

    /**
     * Applique le thème en affichant les cellules vivantes 
     * @param g Partie graphique 
     * @param panel PanelImage 
     */
    public abstract void appliquerTheme(Graphics g, PanelImage panel); 

    /**
     * Retourne le jeu de la vie sur lequel on applique le thème 
     * @return jeu de la vie sur lequel on applique le thème 
     */
    public JeuDeLaVie getJeu(){
        return jeu; 
    }

    /**
     * Retourne l'interface graphique du jeu sur laquelle on applique le thème 
     * @return interface graphique du jeu sur  laquelle on applique le thème 
     */
    public JeuDeLaVieUI getJeuUI(){
        return jeuUI; 
    }    
}
