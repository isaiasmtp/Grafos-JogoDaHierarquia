package jogoDaHierarquia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphTests;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.CSVFormat;

import examples.DefaultVertex;
import examples.MyJGraphTUtil;
import examples.MyTreeUtil;
import examples.RelationshipEdge;


public class Main {

	public static void main(String[] args) {
		//Criar Grafo
		
		Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
		
		 Graph<String, DefaultEdge> directedGraph =
	                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

		graph = MyJGraphTUtil.importGraphCSV(
				graph, 
				"./src/main/java/graphs/graph.csv",
				CSVFormat.MATRIX, 
				false,
				false,
				true); // MATRIX_FORMAT_NODEID
			
		//Quantidade de tentativas - DEVE SER SETADO 
		int tentativas = 4;
         
        //Cria um ArrayList de Vertices
        ArrayList<String> vertices = new ArrayList<String>(graph.vertexSet());


        //Seleciona um vertice aleatorio para ser a raiz
        Random r = new Random();
        String raiz = vertices.get(r.nextInt(vertices.size()));
        
        //Vertice 8 mocado para testes 
        //TODO
        raiz = "8";

       
       
		MyTreeUtil.getRootedTree(((SimpleGraph<String, DefaultEdge>) graph), "7",((DefaultDirectedGraph<String, DefaultEdge>) directedGraph));
        
    	 MyJGraphTUtil.createAndShowGui(directedGraph,"Graph",
                 false,false,true,true,
                 MyJGraphTUtil.layout_type.CIRCLE);

        
//    	System.out.println("Vertices: " + directedGraph.edgeSet());        
//		System.out.println("Vertices: " + graph.edgeSet());
		System.out.println("Arestas: " + directedGraph.edgeSet());
	}
	
	public void pais() {
		
	}

	public void filhos() {
		
	}
}
