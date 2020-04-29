package clients;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class omdbclient {

    public String XPath(String titre, String requete){

    	String uri = "http://www.omdbapi.com/?apikey=5153048a&type=movie&plot=full&r=xml&t="+titre;
    	
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
