import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.shared.JenaException;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

public class JenaSparQL {
    public void executeQuery() {
//        FileManager.get().addLocatorClassLoader(QuerySparql.class.getClassLoader());
        String queryString =
                """
                        PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                        PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
                        SELECT DISTINCT ?id str(?label)
                        WHERE {
                        ?id rdfs:subClassOf* <http://purl.obolibrary.org/obo/HP_0500015> .
                        ?id rdfs:label ?label
                        }
                """;

        Model model = FileManager.getInternal().loadModelInternal("data/hp.owl");

        QueryFactory.create(queryString);
        QueryExecution queryExecution = QueryExecutionFactory.create(queryString, model);
        ResultSet resultSet = queryExecution.execSelect();


        if(resultSet.hasNext()) {
            System.out.println("Has results!!");
            while(resultSet.hasNext()) {
                QuerySolution querySolution = resultSet.nextSolution();
//                Literal literal = querySolution.getLiteral("id");
//                System.out.println(literal);

                Resource resource = querySolution.getResource("id"); //59
                System.out.println(resource.getLocalName());
            }
        } else {
            System.out.println("No results :((");
        }
    }
}