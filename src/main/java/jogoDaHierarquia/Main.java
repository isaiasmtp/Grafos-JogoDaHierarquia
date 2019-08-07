package jogoDaHierarquia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.CSVFormat;

import examples.MyJGraphTUtil;
import examples.MyTreeUtil;
import util.Util;


public class Main {

	public static void main(String[] args) {
		//Criar Grafos
		Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
		
		Graph<String, DefaultEdge> directedGraph =
	                new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        Random r = new Random();
		
		//Importa Grafo por um arquivo CSV
		graph = MyJGraphTUtil.importGraphCSV(
				graph, 
				"./src/main/java/graphs/graph.csv",
				CSVFormat.MATRIX, 
				false,
				false,
				true);
			         
        //Cria um ArrayList de Vertices e seleciona um aleatoriamente para ser a raiz
        ArrayList<String> vertices = new ArrayList<String>(graph.vertexSet());
        String raiz = vertices.get(r.nextInt(vertices.size()));
       
        //Cria o grafo em arvore com a raiz aleatoriamente selecionada
        MyTreeUtil.getRootedTree(((SimpleGraph<String, DefaultEdge>) graph), raiz,((DefaultDirectedGraph<String, DefaultEdge>) directedGraph));    	 
    	 
        //E recebe as arestas do grafo direcional tree com a raiz aleatoria
        Set<DefaultEdge> E = new HashSet<>(directedGraph.edgeSet());
    	 
        //Recebe numero de tentativas do jogador
        System.out.print("Quantidade de tentativas para o jogador: ");
        int tentativas = Integer.parseInt(scanner.next());
        
		//Imprime o grafo de forma intuitiva na tela do usuario
        MyJGraphTUtil.createAndShowGui(directedGraph,"Graph",
                 false,false,true,true,
                 MyJGraphTUtil.layout_type.CIRCLE);
        
        //Imprime no console o grafo original
	    System.out.println("Arvore: "+ graph.edgeSet()); 

	    //Inicia o jogo
	    //Dois metodos foram criados no package Util para retornar um Set com os vertices pais e filhos 
    	 while(tentativas != 0) {
    		 System.out.print("Indique o vertice: ");
    	     String v = scanner.next();
    	     if(v.equals(raiz)) {
    	    	System.out.println("Você acertou! "); 
    	    	System.out.println("Arvore Enraizada: "+ directedGraph.edgeSet()); 
    	    	break;
    	     }
    	     System.out.println(v +" não é raiz. O pai de "+ v + " é " + Util.pais(E, v) +
    	    		 " e os filhos de " + v + " são " + Util.filhos(E, v) +".");
    	     tentativas--;
    	     if(tentativas==0) {
    	    	System.out.println("Número de tentativas excedido!");
     	    	System.out.println("Arvore Enraizada: "+ directedGraph.edgeSet()); 
    	     }         	     		 
    	 }   	 
	}
}
