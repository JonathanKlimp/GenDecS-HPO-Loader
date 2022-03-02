import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
        OntModel m = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null);
        m.read("file:hp.owl");

        System.out.println(m.getSubGraphs().size());
    }
}
