import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This project implements a simple application. Properties from a fixed
 * file can be displayed. 
 * 
 * 
 * @author Isaac Addo
 * @version 1.0
 */
public class PropertyViewer
{    
    private PropertyViewerGUI gui;     // the Graphical User Interface
    private Portfolio portfolio;
    
    protected int currentIndex = 0; // index of the property currently being displayed from the portfolio
    protected int propertiesViewed = 1; // number of properties viewed immediately when propertyviewer is ran- will update as more properties are viewed
    protected int totalPropertyPrice = 23; // price of the properties viewed immediately when propertyviewer is ran- will update as more properties are viewed
    
    /**
     * Create a PropertyViewer and display its GUI on screen.
     */
    public PropertyViewer()
    {
        gui = new PropertyViewerGUI(this);
        portfolio = new Portfolio("airbnb-london.csv");
        gui.showProperty(portfolio.getProperty(currentIndex));
        gui.showID(portfolio.getProperty(currentIndex));
    }

    /**
     * This method displays the next property, its ID and whether it is a favourite or not in the GUI
     */
    public void nextProperty()
    {
        if(currentIndex <= 9 && currentIndex >= 0){
        currentIndex += 1;
        
        gui.showID(portfolio.getProperty(currentIndex));
        gui.showProperty(portfolio.getProperty(currentIndex));
        
        propertiesViewed += 1;
        
        totalPropertyPrice += portfolio.getProperty(currentIndex).getPrice();
        } 
        
        else {
        this.currentIndex = currentIndex;
        }
        
        if(portfolio.getProperty(currentIndex).isFavourite == true){
        gui.showFavourite(portfolio.getProperty(currentIndex));
        }
        else {
        gui.showNotFavourite(portfolio.getProperty(currentIndex));
        }         
    }
    
    /**
     * This method displays the previous property, its ID and whether it is a favourite or not in the GUI
     */
    public void previousProperty()
    {
        if(currentIndex <= 10 && currentIndex >= 1){
             currentIndex -= 1;
             
             
             gui.showID(portfolio.getProperty(currentIndex));
             gui.showProperty(portfolio.getProperty(currentIndex));
             
             propertiesViewed += 1;
             
             totalPropertyPrice += portfolio.getProperty(currentIndex).getPrice();
        } else {
               this.currentIndex = currentIndex;
        }   
        
        if(portfolio.getProperty(currentIndex).isFavourite == true){
             gui.showFavourite(portfolio.getProperty(currentIndex));
        } 
        else {
             gui.showNotFavourite(portfolio.getProperty(currentIndex));
        }      
    }
    
   /**
     * When the toggle favourite button is clicked on the GUI, this stores the current property displayed on the GUI as a favourite property
     */
    public void toggleFavourite()
    {
        portfolio.getProperty(currentIndex).toggleFavourite();
        
        if(portfolio.getProperty(currentIndex).isFavourite == true){
            gui.showFavourite(portfolio.getProperty(currentIndex));
        } 
        else {
             gui.showNotFavourite(portfolio.getProperty(currentIndex));
        }   
    }
    //----- methods for challenge tasks -----
    
    /**
     * This method opens the system's default internet browser
     * The Google maps page should show the current properties location on the map.
     */
    public void viewMap() throws Exception
    {
       double latitude = portfolio.getProperty(currentIndex).getLatitude();
       double longitude = portfolio.getProperty(currentIndex).getLongitude();
       
       URI uri = new URI("https://www.google.com/maps/place/" + latitude + "," + longitude);
       java.awt.Desktop.getDesktop().browse(uri); 
    }
    
    /**
     * This method returns the number of properties viewed by the user
     */
    public int getNumberOfPropertiesViewed()
    {
        return propertiesViewed;
    }
    
    /**
     * This method returns the average price of all of the properties viewed by the user 
     */
    public int getAveragePropertyPrice()
    {
        int averagePrice = totalPropertyPrice/propertiesViewed;
        return averagePrice;
    }
}
