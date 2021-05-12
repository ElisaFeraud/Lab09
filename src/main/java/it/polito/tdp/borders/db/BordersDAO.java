package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<Integer,Country> idMap) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
			if(!idMap.containsKey(rs.getInt("ccode"))) {
				idMap.put(rs.getInt("ccode"), new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme")));;
			}
			}
			
			conn.close();



		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {

		System.out.println("TODO -- BordersDAO -- getCountryPairs(int anno)");
		return new ArrayList<Border>();
	}
    public List<Country> getVertici(Map<Integer, Country> idMap,int anno) {
    	String sql="SELECT state1no, state2no "
    			+ "FROM contiguity "
    			+ "WHERE YEAR<=? AND conttype=1";
    	List<Country> result= new LinkedList<>();
    	try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(idMap.containsKey(rs.getInt("state1no")) && idMap.containsKey(rs.getInt("state2no"))) {
					Country state1 = idMap.get(rs.getInt("state1no"));
					Country state2 = idMap.get(rs.getInt("state2no"));
					result.add(state1);
					result.add(state2);
					
					
				}
					//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
			}
			
			conn.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
    	return result;
    }

    public List<Border> getArchi(Map<Integer, Country> idMap,int anno) {
    	String sql="SELECT state1no, state2no "
    			+ "FROM contiguity "
    			+ "WHERE YEAR<=? AND conttype=1";
    	List<Border> result= new LinkedList<>();
    	try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(idMap.containsKey(rs.getInt("state1no")) && idMap.containsKey(rs.getInt("state2no"))) {
					Country state1 = idMap.get(rs.getInt("state1no"));
					Country state2 = idMap.get(rs.getInt("state2no"));
					result.add(new Border(state1,state2));
					
					
					
				}
					//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
			}
			
			conn.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
    	return result;
    }
   
}


