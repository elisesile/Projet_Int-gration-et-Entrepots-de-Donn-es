package clients;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;

import java.util.*;

public class dbpediaclient {

	public ArrayList<String> searchByTitle(String title) {
		
        String queryStr = "select ?real (group_concat(DISTINCT ?prod;SEPARATOR=\",\") as ?prods) (group_concat(DISTINCT ?acteur;SEPARATOR=\",\") as ?acteurs) WHERE { ?film a <http://dbpedia.org/ontology/Film>; <http://xmlns.com/foaf/0.1/name> \""+title+"\"@en; <http://dbpedia.org/ontology/director> ?real; <http://dbpedia.org/ontology/producer> ?prod; <http://dbpedia.org/ontology/starring> ?acteur.}GROUP BY ?real";
        Query query = QueryFactory.create(queryStr);
        ArrayList<String> all = new ArrayList<String>();
        QuerySolution qs = null;
        String real = null;

        try ( QueryExecution qexec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query) ) {
            ((QueryEngineHTTP)qexec).addParam("timeout", "10000") ;

            ResultSet rs = qexec.execSelect();
            while (rs.hasNext()) {
            	qs = rs.nextSolution();
            	real += qs.get("real").toString();
            	real += ",";
            }
            
            all.add(qs.get("acteurs").toString());
            all.add(real.substring(0,real.lastIndexOf(",")));
        	all.add(qs.get("prods").toString());
        	
        	return all;
        
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
        
	}
	
	public ArrayList<ArrayList<String>> searchByActor(String actor) {
		
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

