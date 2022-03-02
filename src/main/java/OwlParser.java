import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.model.parameters.Imports;
import org.semanticweb.owlapi.search.EntitySearcher;


import java.io.File;

public class OwlParser {
    public void shouldLoad() throws OWLOntologyCreationException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        File file = new File("data/hp.owl");

        OWLOntology hpOntology = manager.loadOntologyFromOntologyDocument(file);
        System.out.println("Loaded Ontology: " + hpOntology);

        System.out.println(hpOntology.getOntologyID().getOntologyIRI().get());
        System.out.println("GetclassesInSignature " + hpOntology.getClassesInSignature().size());
        System.out.println("getDataPropertiesInSignature " + hpOntology.getDataPropertiesInSignature().size());
        System.out.println("getDataTypesInsignature " + hpOntology.getDatatypesInSignature().size());

        System.out.println(hpOntology.getClassesInSignature());
//        listClasses(hpOntology);
//        listAxiomsforClass(hpOntology);
        entitySearchCLass(hpOntology);
    }

    // lists classes refered in hp.owl ontology
    public void listClasses(OWLOntology hpoOntology) {
        for (OWLClass cls : hpoOntology.getClassesInSignature()) {
            System.out.println(cls.getIRI());
        }
    }

    public void listAxiomsforClass(OWLOntology hpoOntology) {
        OWLClass cls = (OWLClass) hpoOntology.getClassesInSignature().toArray()[3];
        // can also be Imports Included
        for (OWLAxiom a : hpoOntology.getAxioms(cls, Imports.EXCLUDED)) {
            System.out.println(a);
        }
    }

    public void entitySearchCLass(OWLOntology hpoOntology) {
        for (OWLAxiom iterable_element : hpoOntology.getAxioms(Imports.EXCLUDED)) {
            if(iterable_element.isOfType(AxiomType.DECLARATION) && iterable_element.getSignature().iterator().next().isOWLClass()) {
                OWLClass aux = iterable_element.getSignature().iterator().next().asOWLClass();
                System.out.println(aux.getIRI());
                System.out.println("get annotation objects " + EntitySearcher.getAnnotationObjects(aux, hpoOntology).toList().size());
                System.out.println("get instances " + EntitySearcher.getInstances(aux, hpoOntology).toList().size());
                System.out.println("get individuals " + EntitySearcher.getIndividuals(aux, hpoOntology).toList().size());
            }
        }
    }





}