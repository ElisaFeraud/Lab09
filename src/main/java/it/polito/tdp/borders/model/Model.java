package it.polito.tdp.borders.model;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;


public class Model {
	private BordersDAO dao;
	private Map<Integer,Country> idMap;
	Graph<Country,DefaultEdge> grafo;
	private List<String> percorsoMigliore;
	
	public Model() {
		dao = new BordersDAO();
		idMap= new HashMap<>();
		dao.loadAllCountries(idMap);	
		
	}
	
	 public void creaGrafo(int anno) {
		 this.grafo= new SimpleGraph<>(DefaultEdge.class);	
		 Graphs.addAllVertices(grafo, dao.getVertici(idMap, anno));
		 for(Border b: dao.getArchi(idMap, anno)) {
				if(this.grafo.containsVertex(b.getState1()) && this.grafo.containsVertex(b.getState2())) {
					DefaultEdge e = this.grafo.getEdge(b.getState1(),b.getState2());
					if(e==null) {
						Graphs.addEdgeWithVertices(grafo, b.getState1(), b.getState2());
					}
					
				}
		
	 }
		 System.out.format("Grafo creato con %d vertici e %d archi\n", this.grafo.vertexSet().size(),this.grafo.edgeSet().size());
			System.out.println("Archi: "+ this.grafo.edgeSet());
	 }
	 public int getNVertici() {
		 return grafo.vertexSet().size();
	 }
	 public List<Country> getVertici(Map<Integer, Country> idMap,int anno){
		 return dao.getVertici(idMap, anno);
	 }
	 public List<Country> getStati(){
		 List<Country> list = new LinkedList<>();
		 for(Country c: this.grafo.vertexSet()) {
			 list.add(c);
		 }
		 return list;
	 }
  
	 public int getNArchi() {
		 return grafo.edgeSet().size();
	 }
	 public String gradoVertice() {
		 String gradi="";
		 for(Country c: this.grafo.vertexSet()) {
			 gradi +=c.toString()+" "+this.grafo.degreeOf(c)+"\n";
		 }
		 return gradi;
	 }
	 public int getNumberOfConnectedComponents() {
			if (grafo == null) {
				throw new RuntimeException("Grafo non esistente");
			}

			ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(grafo);
			return ci.connectedSets().size();
		}
	 public List<Country> getReachableCountries(Country selectedCountry) {

			if (!grafo.vertexSet().contains(selectedCountry)) {
				throw new RuntimeException("Selected Country not in graph");
			}

			List<Country> reachableCountries;// = this.displayAllNeighboursIterative(selectedCountry);
			//System.out.println("Reachable countries: " + reachableCountries.size());
			reachableCountries = this.displayAllNeighboursJGraphT(selectedCountry);
			System.out.println("Reachable countries: " + reachableCountries.size());
			/*reachableCountries = this.displayAllNeighboursRecursive(selectedCountry);
			System.out.println("Reachable countries: " + reachableCountries.size());*/

			return reachableCountries;
		}
	private List<Country> displayAllNeighboursRecursive(Country selectedCountry) {
		// TODO Auto-generated method stub
		return null;
	}
	private List<Country> displayAllNeighboursJGraphT(Country selectedCountry) {
		// TODO Auto-generated method stub
		List<Country> visited = new LinkedList<Country>();
		GraphIterator<Country, DefaultEdge> dfv = new DepthFirstIterator<Country, DefaultEdge>(grafo, selectedCountry);
		while (dfv.hasNext()) {
			visited.add(dfv.next());
		}

		return visited;
		
		
	}
	private List<Country> displayAllNeighboursIterative(Country selectedCountry) {
		// TODO Auto-generated method stub
		return null;
	}
}
