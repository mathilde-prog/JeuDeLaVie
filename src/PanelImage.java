package src; 

import java.awt.*;
import javax.swing.*; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO ;
import src.Themes.Theme;

/**
 * La classe PanelImage représente un panel contenant une image. 
 * Hérite de JPanel 
 * @see JPanel 
 * 
 * @author Mathilde MOTTAY 
 */
public class PanelImage extends JPanel  {

    private static final long serialVersionUID = -6350324456764768311L;

    /**
     * Image dans le PanelImage 
     */
    private BufferedImage image;

    /**
     * Thème du PanelImage  
     */
    private Theme theme; 


    /**
     * Constructeur PanelImage
     * @param cheminImage Chemin de l'image à afficher en arrière plan
     * @param theme Thème 
     * @throws IOException Si le chemin n'est pas trouvé
     */
    public PanelImage(String cheminImage, Theme theme) throws IOException {
        super();
        setImage(cheminImage);
        this.theme = theme; 
    }
    
    /**
     * Affecte au thème le thème passé en paramètre 
     * @param theme Nouveau thème 
     */
    public void setTheme(Theme theme){
        this.theme = theme; 
        repaint(); 
    }

    /**
     * Affecte à l'image du JPanel l'image dont le chemin est passé en paramètre  
     * @param cheminImage Chemin vers la nouvelle image à afficher
     * @throws IOException Si le chemin n'est pas trouvé
     */
    public void setImage(String cheminImage) throws IOException {
        try {
            this.image = ImageIO.read(new File(cheminImage));
            repaint();
        } 
        catch (IOException e) {
            throw new IOException(cheminImage + " introuvable", e);
        }
    }

    /**
     * Dessine en appliquant le thème 
     * @param g Partie graphique du PanelImage  
     */
    @Override
    public void paintComponent(Graphics g){
        if(image != null){
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }

        theme.appliquerTheme(g, this);
    }

}