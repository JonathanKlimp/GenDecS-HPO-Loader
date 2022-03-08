import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;

import java.util.ArrayList;

public class JenaSparQL {
    Model model = FileManager.getInternal().loadModelInternal("data/hp.owl");
    String hpoID;
    ArrayList<String> parents;
    ArrayList<String> subClasses;


    public JenaSparQL(String id) {
        hpoID = id;
    }


    public ResultSet querySubClasses(String hpoID) {
        String queryString = String.format(
                """
                        PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                        PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
                        SELECT DISTINCT ?id ?label
                        WHERE {
                        ?id rdfs:subClassOf* <http://purl.obolibrary.org/obo/%s> .
                        ?id rdfs:label ?label
                        }
                """, hpoID);
        QueryFactory.create(queryString);
        QueryExecution queryExecution = QueryExecutionFactory.create(queryString, model);
        return queryExecution.execSelect();
    }


    public ResultSet queryParentClass(String hpoID) {
        String queryString =

                String.format("""
                                                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                        PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
                        PREFIX owl: <http://www.w3.org/2002/07/owl#>
                        SELECT DISTINCT ?predicate ?label
                        WHERE {
                            <http://purl.obolibrary.org/obo/%s> ?predicate ?label .
                        }
                        
                        """, hpoID);

        QueryFactory.create(queryString);
        QueryExecution queryExecution = QueryExecutionFactory.create(queryString, model);
        return queryExecution.execSelect();
    }


    public ArrayList<String> getParents(ResultSet resultSet) {
        ArrayList<String> parents = new ArrayList<>();
        if(resultSet.hasNext()) {
            System.out.println("Has results!!");
            while(resultSet.hasNext()) {
                QuerySolution querySolution = resultSet.nextSolution();

                Resource resource = querySolution.getResource("predicate");
                if(resource.getLocalName().equals("subClassOf")) {
                    // adds id of the parent
                    Resource literal = querySolution.getResource("label");
                    parents.add(literal.getLocalName());
                }
            }
        } else {
            System.out.println("No results");
        }
        return parents;
    }


    public ArrayList<String> getSubClasses(ResultSet resultSet) {
        ArrayList<String> hpoTerms = new ArrayList<>();
        if(resultSet.hasNext()) {
            System.out.println("Has results!!");
            while(resultSet.hasNext()) {
                QuerySolution querySolution = resultSet.nextSolution();

                Literal literal = querySolution.getLiteral("label");
                hpoTerms.add(literal.getString());

//                Resource resource = querySolution.getResource("id");
                // print hpo id's
//                System.out.println(resource.getLocalName());
            }

        } else {
            System.out.println("No results");
        }
        return hpoTerms;
    }


    public void executeQuery() {
        ResultSet resultsParents = queryParentClass(hpoID);
        ResultSet resultsSubClasses = querySubClasses(hpoID);

        parents = getParents(resultsParents);
        subClasses = getSubClasses(resultsSubClasses);
    }


    public ArrayList<String> getParents() {
        return parents;
    }


    public ArrayList<String> getSubClasses() {
        return subClasses;
    }
}