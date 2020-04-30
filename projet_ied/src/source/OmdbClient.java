package source;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import data.Film;

public class OmdbClient {

    public static Film getPlotByTitle(Film film){
    	
    	//Prend en entree le titre d'un film 
    	//Renvoi son plot 

    	String uri = "http://www.omdbapi.com/?apikey=5153048a&type=movie&plot=full&r=xml&t="+film.getTitre();
    	String requete = "/root/movie/@plot";
    	
        try{
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder parseur = fabrique.newDocumentBuilder();
			Document document = parseur.parse(uri);

        	XPathFactory xfabrique = XPathFactory.newInstance();
        	XPath xpath = xfabrique.newXPath();
        
        	XPathExpression exp = xpath.compile(requete);
        	
        	if(exp.evaluate(document, XPathConstants.STRING).toString().length() > 5) {
        		film.setPlot(exp.evaluate(document, XPathConstants.STRING).toString());
        	}else {
        		film.setPlot(null);
        	}
        	
        	return film;
        	
        } catch(Exception e){
        	System.out.println(e.getMessage());
        }
        return null;
    }
	
}
