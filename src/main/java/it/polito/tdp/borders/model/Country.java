package it.polito.tdp.borders.model;

public class Country {
   int ccode;
   String stateAbb;
   String stateNme;
public Country(int ccode, String stateAbb, String stateNme) {
	this.ccode = ccode;
	this.stateAbb = stateAbb;
	this.stateNme = stateNme;
}
public int getCcode() {
	return ccode;
}
public void setCcode(int ccode) {
	this.ccode = ccode;
}
public String getStateAbb() {
	return stateAbb;
}
public void setStateAbb(String stateAbb) {
	this.stateAbb = stateAbb;
}
public String getStateNme() {
	return stateNme;
}
public void setStateNme(String stateNme) {
	this.stateNme = stateNme;
}
   
}
