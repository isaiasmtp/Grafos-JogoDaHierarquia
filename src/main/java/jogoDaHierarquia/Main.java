package jogoDaHierarquia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
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
		
		Scanner scanner = new Scanner(System.in);

		graph = MyJGraphTUtil.importGraphCSV(
				graph, 
				"./src/main/java/graphs/graph.csv",
				CSVFormat.MATRIX, 
				false,
				false,
				true); // MATRIX_FORMAT_NODEID
			         
        //Cria um ArrayList de Vertices
        ArrayList<String> vertices = new ArrayList<String>(graph.vertexSet());
        
        //Seleciona um vertice aleatorio para ser a raiz
        Random r = new Random();
        String raiz = vertices.get(r.nextInt(vertices.size()));
        raiz = "8";//TODO
       
		MyTreeUtil.getRootedTree(((SimpleGraph<String, DefaultEdge>) graph), raiz,((DefaultDirectedGraph<String, DefaultEdge>) directedGraph));    	 
    	 
    	 Set<DefaultEdge> E = new HashSet<>(directedGraph.edgeSet());
    	 
		 System.out.print("Quantidade de tentativas para o jogador: ");
		 
		 int tentativas = Integer.parseInt(scanner.next());
		 
		 MyJGraphTUtil.createAndShowGui(directedGraph,"Graph",
                 false,false,true,true,
                 MyJGraphTUtil.layout_type.CIRCLE);
	    System.out.println("Arvore: "+ graph.edgeSet()); 

    	 while(tentativas != 0) {
    		 
    		 System.out.print("Indique o vertice: ");
    	     String v = scanner.next();
    	     if(v.equals(raiz)) {
    	    	System.out.println("Você acertou! "); 
    	    	System.out.println("Arvore Enraizada: "+ directedGraph.edgeSet()); 
    	    	break;
    	     }
    	     System.out.println(v +" não é raiz. O pai de "+ v + " é " + util.Util.pais(E, v) +
    	    		 " e os filhos de " + v + " são " + util.Util.filhos(E, v) +".");
    	     tentativas--;
    	     
    	     if(tentativas==0) {
    	    	System.out.println("Número de tentativas excedido!");
     	    	System.out.println("Arvore Enraizada: "+ directedGraph.edgeSet()); 
    	     }         	     		 
    	 }   	 
	}
}
