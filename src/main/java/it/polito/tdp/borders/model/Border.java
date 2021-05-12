package it.polito.tdp.borders.model;

public class Border {
    Country state1;
    Country state2;
   
	public Border(Country state1, Country state2) {
		this.state1 = state1;
		this.state2 = state2;
		
	}
	public Country getState1() {
		return state1;
	}
	public void setState1(Country state1) {
		this.state1 = state1;
	}
	public Country getState2() {
		return state2;
	}
	public void setState2(Country state2) {
		this.state2 = state2;
	}

	
    
}
