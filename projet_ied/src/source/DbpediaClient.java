package source;

import org.apache.jena.query.*;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import data.Film;

import java.util.*;

public class DbpediaClient {

	public Film searchByTitle(String title, Film film) {
		
		//Prend le titre d'un film en entree
		//Renvoi le triplet Acteurs - Realisateur - Producteur
		//Chaque valeur est un String, si il y a plusieurs noms ils sont separes par des virgules
		
        String queryStr = "select ?real (group_concat(DISTINCT ?prod;SEPARATOR=\",\") as ?prods) (group_concat(DISTINCT ?acteur;SEPARATOR=\",\") as ?acteurs) WHERE { ?film a <http://dbpedia.org/ontology/Film>; <http://xmlns.com/foaf/0.1/name> \""+title+"\"@en; <http://dbpedia.org/ontology/director> ?real; <http://dbpedia.org/ontology/producer> ?prod; <http://dbpedia.org/ontology/starring> ?acteur.}GROUP BY ?real";
        Query query = QueryFactory.create(queryStr);
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
            
            film.setActeurs(temp.substring(0,temp.lastIndexOf(",")));
            
            film.setRealisateur(real.substring(0,real.lastIndexOf(",")));
        	
            temp = null;
            sep = qs.get("prods").toString().split(",");
            for (String value : sep ) {
            	temp += value.substring(value.lastIndexOf("/"))+",";
            }
            
            film.setProducteur(temp.substring(0,temp.lastIndexOf(",")));
            
        	return film;
        
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
        
	}
	
	public static ArrayList<Film> searchByActor(String actor) {
		
		//Prend en entree le nom d'un acteur
		//Renvoi des triplets Titre - Realisateur - Producteur de tous les films dans lesquels il a joue
		//Chaque valeur est un string
		
		
        String queryStr = "SELECT ?titre (group_concat(DISTINCT ?real;SEPARATOR=\",\") as ?reals) (group_concat( DISTINCT ?prod;SEPARATOR=\",\") as ?prods) WHERE { ?acteur a <http://dbpedia.org/ontology/Person>; <http://xmlns.com/foaf/0.1/name> \""+actor+"\"@en. ?film a <http://dbpedia.org/ontology/Film>; <http://dbpedia.org/ontology/starring> ?acteur; <http://xmlns.com/foaf/0.1/name> ?titre; <http://dbpedia.org/ontology/director> ?real; <http://dbpedia.org/ontology/producer> ?prod. }Group by ?titre";
        Query query = QueryFactory.create(queryStr);
        ArrayList<Film> all = new ArrayList<Film>();
        QuerySolution qs = null;
        String titre = null;
        String real = null;
        String prod = null;
        String chng = null;
        String [] teemp = null ;
        

        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
            ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;

            ResultSet rs = qexec.execSelect();
            while (rs.hasNext()) {
            	qs = rs.nextSolution();
            	titre = qs.get("titre").toString();
            	titre = titre.substring(0,titre.indexOf("@"));
            	
            	teemp = qs.get("reals").toString().split(",");
            	for (String value : teemp) {
            		chng+= value.substring(value.lastIndexOf("/"))+",";
            	}
            	real = chng.substring(0,chng.lastIndexOf(","));
            	
            	teemp = qs.get("prods").toString().split(",");
            	chng = null;
            	for (String value : teemp) {
            		chng+= value.substring(value.lastIndexOf("/"))+",";
            	}
            	prod = chng.substring(0,chng.lastIndexOf(","));
            	
            	Film f = new Film(titre,real,prod,actor);
            	
            	all.add(f);
            	
            	chng = null;
            	teemp = null;
            }
        	
        	return all;
        
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
        
	}
	
}

