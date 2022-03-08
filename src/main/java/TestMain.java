import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;


public class TestMain {
    public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
        Testing owlParser = new Testing();
        owlParser.shouldLoad();

    }
}
