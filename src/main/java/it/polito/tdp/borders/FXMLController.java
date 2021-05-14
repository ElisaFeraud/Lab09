
package it.polito.tdp.borders;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader
  
    @FXML
    private Button btnStati;
    
    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
     
    @FXML
    private ComboBox<Country> boxStati;
    
    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
         String yearS = txtAnno.getText();
         int year;
         try {
        	 year= Integer.parseInt(yearS);
         }catch(NumberFormatException e) {
     		txtResult.appendText("Devi inserire un numero!");
     		return;}
         if(year<1816 || year>2016) {
        	 txtResult.appendText("Devi inserire un anno compreso tra il 1816 e il 2016");
        	 return;
         }
         else {
        	
        	 model.creaGrafo(year);
        	 List<Country> countries = model.getStati();
 			  boxStati.getItems().addAll(countries);
        	 txtResult.appendText("Grafo creato con " +model.getNVertici()+" vertici e " +model.getNArchi()+" archi.\n");
        	 txtResult.appendText("Numero di componenti connesse: "+model.getNumberOfConnectedComponents());
        	 txtResult.appendText("\n"+model.gradoVertice());
        	 
         }
    }
    @FXML
    void doCammino(ActionEvent event) {
        
         txtResult.setText( ""+model.getReachableCountries(boxStati.getValue()));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxStati != null : "fx:id=\"boxStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStati != null : "fx:id=\"btnStati\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
  
    }
}
