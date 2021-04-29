package src; 

/**
 * Interface Observable. 
 * @author Mathilde MOTTAY 
 */
public interface Observable {
    /**
     * Attache un observateur 
     * @param o Observateur à attacher 
     */
    public void attacheObservateur(Observateur o); 

    /**
     * Détache un observateur 
     * @param o Observateur à détacher 
     */
    public void detacheObservateur(Observateur o); 

    /**
     * Notifie les observateurs pour qu'ils s'actualisent 
     */
    public void notifieObservateurs(); 
}
