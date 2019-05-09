package jogo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.CSVFormat;

import util.GraphUtil;

public class Main {

	public static void main(String[] args) {
		//Criar Grafos
		Graph<String,DefaultEdge> graph = 
				new SimpleGraph <String,DefaultEdge> (DefaultEdge.class);
		
		 Graph<String, DefaultEdge> directedGraph =
	                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
	
		//Quantidade de tentativas - DEVE SER SETADO 
		int tentativas = 4;
		
		//Arquivo CSV de Entrada
        String filename = 	"./src/graphs/graph.csv";
        
        //Gera Grafo
        generateGraph(filename, graph);	      
        
        //Cria um ArrayList de Vertices
        ArrayList<String> vertices = new ArrayList(graph.vertexSet());

        //Seleciona um vertice aleatorio para ser a raiz
        Random r = new Random();
        String raiz = vertices.get(r.nextInt(vertices.size()));
        
        //Vertice 8 mocado para testes 
        //TODO
        raiz = "8";
            
        //Adiciona vertices no Grafo direcional        
        for (int i = 0; i < vertices.size(); i++) {
        	 directedGraph.addVertex( vertices.get(i));
        }
                            
       //Pecorrer array de vertices e insertir vertices no grafo direcional;]
    	for(int i = 0; i < vertices.size(); i++) {
    		String[] aresta = new String[2];
    		for(int j=0;j < graph.edgesOf(vertices.get(i)).size(); j++) {
    			aresta = graph.edgesOf(vertices.get(i)).toArray()[j].toString().replace("(", "").replace(")","").split(" : ");   
    			 directedGraph.addEdge(aresta[0], aresta[1]);
    		}
    	}
    	
    	Set <DefaultEdge> ArestasDaRaiz = new HashSet <DefaultEdge>(directedGraph.edgesOf(raiz));
    	directedGraph.removeVertex(raiz);
//    	System.out.println(ArestasDaRaiz);        

    	


        
//    	System.out.println("Vertices: " + directedGraph.edgeSet());        
//		System.out.println("Vertices: " + graph.vertexSet());
//		System.out.println("Arestas: " + graph.edgeSet());
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
