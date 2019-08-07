package util;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;

public class Util {

	
public static Set<String> pais( Set<DefaultEdge> E, String V) {
		
		String[] aresta = new String[2];
		Set<String> pais = new HashSet<String>();
		
		for(DefaultEdge i : E) {
			aresta[0] = i.toString().replace("(", "").replace(")","").split(" : ")[0];
			aresta[1] = i.toString().replace("(", "").replace(")","").split(" : ")[1];

			if(aresta[1].equals(V)) {
				pais.add(aresta[0]);

			}
		}
		return pais;
	}

	public static Set<String> filhos( Set<DefaultEdge> E, String V) {
		
		String[] aresta = new String[2];
		Set<String> filhos = new HashSet<String>();
		
		for(DefaultEdge i : E) {
			aresta[0] = i.toString().replace("(", "").replace(")","").split(" : ")[0];
			aresta[1] = i.toString().replace("(", "").replace(")","").split(" : ")[1];
			
			if(aresta[0].equals(V)) {
				filhos.add(aresta[1]);

			}
		}
		return filhos;
	}
}
