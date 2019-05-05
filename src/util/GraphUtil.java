package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.io.CSVFormat;
import org.jgrapht.io.CSVImporter;
import org.jgrapht.io.EdgeProvider;
import org.jgrapht.io.GmlImporter;
import org.jgrapht.io.ImportException;
import org.jgrapht.io.VertexProvider;


public class GraphUtil<V,E> {

    public static <V,E> void printGraph (Graph <V,E> g ) {
        System.out.println(g.vertexSet());
        System.out.println(g.edgeSet()+"\n");
    }

    public static <V,E> void printGraph (Graph <V,E> g, String title ) {
        System.out.println(title);
        System.out.println(g.vertexSet());
        System.out.println(g.edgeSet()+"\n");
    }

    /**
     * Os métodos a seguir realizam a importação de grafos no formato CSV e GML.
     *
     */

    public static Graph<String,DefaultEdge> importGraphCSV (Graph<String,DefaultEdge> graph, String filename, CSVFormat f) {
        VertexProvider<String> vp = (label, attributes) -> label;
        EdgeProvider<String, DefaultEdge> ep = (from, to, label, attributes) -> new DefaultEdge();

        CSVImporter<String, DefaultEdge> csvImporter = new CSVImporter<>(vp, ep);
        csvImporter.setFormat(f);

        try {
            csvImporter.importGraph(graph, readFile(filename));
        } catch (ImportException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }

    public static Graph<String,DefaultEdge> importGraphCSV (
            Graph<String,DefaultEdge> graph,
            String filename,
            CSVFormat f,
            boolean pMATRIX_FORMAT_ZERO_WHEN_NO_EDGE,
            boolean pEDGE_WEIGHT,
            boolean pMATRIX_FORMAT_NODEID) {
        VertexProvider<String> vp = (label, attributes) -> label;
        EdgeProvider<String, DefaultEdge> ep = (from, to, label, attributes) -> new DefaultEdge();

        CSVImporter<String, DefaultEdge> csvImporter = new CSVImporter<>(vp, ep);
        csvImporter.setFormat(f);
        csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,pMATRIX_FORMAT_ZERO_WHEN_NO_EDGE);
        csvImporter.setParameter(CSVFormat.Parameter.EDGE_WEIGHTS, pEDGE_WEIGHT);
        csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_NODEID, pMATRIX_FORMAT_NODEID);

        try {
            csvImporter.importGraph(graph, readFile(filename));
        } catch (ImportException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }

    public static Graph<String,DefaultWeightedEdge> importWeightedGraphCSV (
            Graph<String,DefaultWeightedEdge> graph,
            String filename,
            CSVFormat f,
            boolean pMATRIX_FORMAT_ZERO_WHEN_NO_EDGE,
            boolean pEDGE_WEIGHT,
            boolean pMATRIX_FORMAT_NODEID) {
        VertexProvider<String> vp = (label, attributes) -> label;
        EdgeProvider<String, DefaultWeightedEdge> ep = (from, to, label, attributes) -> new DefaultWeightedEdge();

        CSVImporter<String, DefaultWeightedEdge> csvImporter = new CSVImporter<>(vp, ep);
        csvImporter.setFormat(f);
        csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_ZERO_WHEN_NO_EDGE,pMATRIX_FORMAT_ZERO_WHEN_NO_EDGE);
        csvImporter.setParameter(CSVFormat.Parameter.EDGE_WEIGHTS, pEDGE_WEIGHT);
        csvImporter.setParameter(CSVFormat.Parameter.MATRIX_FORMAT_NODEID, pMATRIX_FORMAT_NODEID);

        try {
            csvImporter.importGraph(graph, readFile(filename));
        } catch (ImportException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }

    public static Graph<String,DefaultEdge> importDefaultGraphGML (Graph<String,DefaultEdge> graph, String filename) {
        VertexProvider<String> vp1 = (label, attributes) -> label;
        EdgeProvider<String, DefaultEdge> ep1 = (from, to, label, attributes) -> new DefaultEdge();
        GmlImporter<String, DefaultEdge> gmlImporter = new GmlImporter<>(vp1, ep1);
        try {
            gmlImporter.importGraph(graph, readFile(filename));
        } catch (ImportException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }

    static Reader readFile(String filename) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringReader readergml = new StringReader(contentBuilder.toString());
        return readergml;
    }


}
