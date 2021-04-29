package src.Themes; 

import java.io.IOException;
import java.awt.*;
import src.JeuDeLaVie;
import src.PanelImage;
import src.JeuDeLaVieUI;

/**
 * La classe ThemeOrange représente un thème où les cellules vivantes sont des points oranges à l'affichage.
 * @author Mathilde MOTTAY 
 */
public class ThemeOrange extends Theme {
 
    /**
     * Constructeur ThemeOrange 
     * @param jeu Jeu de la vie 
     * @param jeuUI Interface graphique du jeu 
     * @throws IOException
     */
    public ThemeOrange(JeuDeLaVie jeu, JeuDeLaVieUI jeuUI) throws IOException {
        super(jeu, jeuUI); 
    }

    /**
     * Applique le thème en affichant les cellules vivantes 
     * @param g Partie graphique 
     * @param panel PanelImage 
     */
    @Override
    public void appliquerTheme(Graphics g, PanelImage panel) {
        Color orange = new Color(255,120,1); 
        int width = 3 * jeu.getZoom(); 
        int espaceX = width + 5; 
        int espaceY = width*2; 

        g.setColor(orange);
        for(int x = 0; x <= getJeu().getXMax(); x++){
            for(int y = 0; y <= getJeu().getYMax(); y++){
                if(getJeu().getGrilleXY(x,y).estVivante()){
                    g.fillOval(panel.getLocation().x+15+x*espaceX,panel.getLocation().y+15+y*espaceY,width,width);
               }
            }
        }                         
    }

    /**
     * Retourne le chemin de l'image en arrière-plan du thème 
     * @return Chemin de l'image en arrière-plan du thème 
     */
    @Override
    public String getBackground() {
        return "img/backgroundOrange.png";
    }
}
