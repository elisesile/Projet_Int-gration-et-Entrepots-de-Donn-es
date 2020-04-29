package source;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class OmdbClient {

    public String getPlotByTitle(String titre){
    	
    	//Prend en entree le titre d'un film 
    	//Renvoi son plot 

    	String uri = "http://www.omdbapi.com/?apikey=5153048a&type=movie&plot=full&r=xml&t="+titre;
    	String requete = "/root/movie/@plot";
    	
        try{
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder parseur = fabrique.newDocumentBuilder();
			Document document = parseur.parse(uri);

        	XPathFactory xfabrique = XPathFactory.newInstance();
        	XPath xpath = xfabrique.newXPath();
        
        	XPathExpression exp = xpath.compile(requete);
        	return exp.evaluate(document, XPathConstants.STRING).toString();
        	
        } catch(Exception e){
        	System.out.println(e.getMessage());
        }
        return null;
    }
	
}
