import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");
        OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM);

        String filename = "data/hp.owl";
        try {
            InputStream inputStream = new FileInputStream(filename);
            model.read(inputStream, "OWL/XML");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        System.out.println(model.getSubGraphs().size());

        ExtendedIterator<OntClass> itI = model.listClasses();


        while (itI.hasNext()) {
            OntClass i = itI.next();
//            System.out.println(i);
//            System.out.println(i.getLabel(null));
//            System.out.println(i.getLocalName());
        }
        System.out.println("End of main");
    }
}
