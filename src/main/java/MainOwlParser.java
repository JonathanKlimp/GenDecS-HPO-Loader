import org.semanticweb.owlapi.model.OWLOntologyCreationException;


public class MainOwlParser {
    public static void main(String[] args) throws OWLOntologyCreationException {
        OwlParser owlParser = new OwlParser();
        owlParser.shouldLoad();

    }
}
