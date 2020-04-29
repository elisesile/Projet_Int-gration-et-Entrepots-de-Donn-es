package source;

import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import java.util.*;

public class DbpediaClient {

	public ArrayList<String> searchByTitle(String title) {
		
		//Prend le titre d'un film en entree
		//Renvoi le triplet Acteurs - Realisateur - Producteur
		//Chaque valeur est un String, si il y a plusieurs noms ils sont separes par des virgules
		
        String queryStr = "select ?real (group_concat(DISTINCT ?prod;SEPARATOR=\",\") as ?prods) (group_concat(DISTINCT ?acteur;SEPARATOR=\",\") as ?acteurs) WHERE { ?film a <http://dbpedia.org/ontology/Film>; <http://xmlns.com/foaf/0.1/name> \""+title+"\"@en; <http://dbpedia.org/ontology/director> ?real; <http://dbpedia.org/ontology/producer> ?prod; <http://dbpedia.org/ontology/starring> ?acteur.}GROUP BY ?real";
        Query query = QueryFactory.create(queryStr);
        ArrayList<String> all = new ArrayList<String>();
        QuerySolution qs = null;
        String real = null;
        String temp = null;

        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
            ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;

            ResultSet rs = qexec.execSelect();
            while (rs.hasNext()) {
            	qs = rs.nextSolution();
            	temp = qs.get("real").toString();
            	temp = temp.substring(temp.lastIndexOf("/"));
            	real += temp;
            	real += ",";
            }
            
            String[] sep = qs.get("acteurs").toString().split(",");
            temp = null;
            for (String value : sep) {
            	temp+= value.substring(value.lastIndexOf("/"))+",";
            }
            all.add(temp.substring(0,temp.lastIndexOf(",")));
            
            all.add(real.substring(0,real.lastIndexOf(",")));
        	
            temp = null;
            sep = qs.get("prods").toString().split(",");
            for (String value : sep ) {
            	temp += value.substring(value.lastIndexOf("/"))+",";
            }
            all.add(temp.substring(0,temp.lastIndexOf(",")));
        	
        	return all;
        
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
        
	}
	
	public static ArrayList<ArrayList<String>> searchByActor(String actor) {
		
		//Prend en entree le nom d'un acteur
		//Renvoi des triplets Titre - Realisateur - Producteur de tous les films dans lesquels il a joue
		//Chaque valeur est un string
		
		
        String queryStr = "SELECT ?titre (group_concat(DISTINCT ?real;SEPARATOR=\",\") as ?reals) (group_concat( DISTINCT ?prod;SEPARATOR=\",\") as ?prods) WHERE { ?acteur a <http://dbpedia.org/ontology/Person>; <http://xmlns.com/foaf/0.1/name> \""+actor+"\"@en. ?film a <http://dbpedia.org/ontology/Film>; <http://dbpedia.org/ontology/starring> ?acteur; <http://xmlns.com/foaf/0.1/name> ?titre; <http://dbpedia.org/ontology/director> ?real; <http://dbpedia.org/ontology/producer> ?prod. }Group by ?titre";
        Query query = QueryFactory.create(queryStr);
        ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
        ArrayList<String> temp = new ArrayList<String>();
        QuerySolution qs = null;
        String titre = null;

        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
            ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;

            ResultSet rs = qexec.execSelect();
            while (rs.hasNext()) {
            	qs = rs.nextSolution();
            	titre = qs.get("titre").toString();
            	temp.add(titre.substring(0,titre.indexOf("@")));
            	temp.add(qs.get("reals").toString());
            	temp.add(qs.get("prods").toString());
            	all.add(temp);
            	temp.clear();
            }
        	
        	return all;
        
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
        
	}
	
}

