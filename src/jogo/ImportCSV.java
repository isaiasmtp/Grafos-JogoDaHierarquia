package jogo;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.CSVFormat;

import util.GraphUtil;

public class ImportCSV {

	public static void main(String[] args) {
		
		Graph<String,DefaultEdge> graph = 
				new SimpleGraph <String,DefaultEdge> (DefaultEdge.class);
	
        String filename = 	"./src/graphs/graph.csv";
        
        generateGraph(filename, graph);
		
		System.out.println("Vertices: " + graph.vertexSet());
		System.out.println("Arestas: " + graph.edgeSet());
	}
	
	private static void generateGraph(String filename, Graph<String, DefaultEdge> csvGraph) {
        csvGraph = GraphUtil.importGraphCSV(
                csvGraph,
                filename,
                CSVFormat.MATRIX,
                false,
                false,
                true);
    }
}
