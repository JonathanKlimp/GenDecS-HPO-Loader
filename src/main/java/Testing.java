import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.model.parameters.Imports;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;


import java.io.File;
import java.util.Set;

public class Testing {
    public void shouldLoad() throws OWLOntologyCreationException, OWLOntologyStorageException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        File file = new File("data/hp.owl");

        OWLOntology hpOntology = manager.loadOntologyFromOntologyDocument(file);
        System.out.println("Loaded Ontology: " + hpOntology);
//        hpOntology.saveOntology(new FunctionalSyntaxDocumentFormat(), System.out);

//        System.out.println(hpOntology.getOntologyID().getOntologyIRI().get());
//        System.out.println("GetclassesInSignature " + hpOntology.getClassesInSignature().size());
//        System.out.println("getDataPropertiesInSignature " + hpOntology.getDataPropertiesInSignature().size());
//        System.out.println("getDataTypesInsignature " + hpOntology.getDatatypesInSignature().size());


        // iterating over the axioms
        hpOntology.logicalAxioms().forEach(System.out::println);
//        for(OWLAxiom ax:hpOntology.getLogicalAxioms()) {
//            System.out.println(ax);
//        }

        OWLReasonerFactory rf = new ReasonerFactory();
        OWLReasoner r = rf.createReasoner(hpOntology);
        r.precomputeInferences(InferenceType.CLASS_HIERARCHY);


//        listClasses(hpOntology);
//        listAxiomsforClass(hpOntology);
//        entitySearchCLass(hpOntology);
    }

    // lists classes referred in hp.owl ontology
    public void listClasses(OWLOntology hpoOntology) {
        for (OWLClass cls : hpoOntology.getClassesInSignature()) {
//            System.out.println(cls.getIRI());

        }
    }

    public void listAxiomsforClass(OWLOntology hpoOntology) {
        OWLClass cls = (OWLClass) hpoOntology.getClassesInSignature().toArray()[3];
        // can also be Imports Included
        for (OWLAxiom a : hpoOntology.getAxioms(cls, Imports.INCLUDED)) {
            System.out.println(a);
        }
    }

    public void entitySearchCLass(OWLOntology hpoOntology) {
        for (OWLAxiom iterable_element : hpoOntology.getAxioms(Imports.EXCLUDED)) {
            if (iterable_element.isOfType(AxiomType.DECLARATION) && iterable_element.getSignature().iterator().next().isOWLClass()) {
                OWLClass aux = iterable_element.getSignature().iterator().next().asOWLClass();
                System.out.println("IRI: " + aux.getIRI());

                // prints ontology ID ex: HP_0010659
                System.out.println("Fragment: " + aux.getIRI().getFragment());
//                System.out.println("Fragment: " + aux.getIRI().getRemainder()); new and better method of getFragment

//                System.out.println(aux.getIRI().isOntology());
//                System.out.println("get annotation objects " + EntitySearcher.getAnnotationObjects(aux, hpoOntology).toList().size());
//                System.out.println("annatation object: " + EntitySearcher.getAnnotationObjects(aux, hpoOntology).toList().get(0));

                // always zero, why?
//                System.out.println("get instances " + EntitySearcher.getInstances(aux, hpoOntology).toList().size());
//                System.out.println("get individuals " + EntitySearcher.getIndividuals(aux, hpoOntology).toList().size());
            }
        }
    }
}