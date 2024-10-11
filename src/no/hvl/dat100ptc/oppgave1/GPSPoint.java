package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	private int time;
	private double latitude;
	private double longitude;
	private double elevation; 
	
	
	public GPSPoint(int time, double latitude, double longitude, double elevation) {    //konstruktør lage nye objekt
		
		this.time = time;    // this gjør at det blir objektvariabeler
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
		
		
		
	} 
    	
	public int getTime() {   // hente 
		return time;
		
	}

	public void setTime(int time) {  // endre
				
		this.time = time;
		
	}

	public double getLatitude() {
		
		return latitude; 
		
		
	}

	public void setLatitude(double latitude) {
		
		this.latitude = latitude;
		
	}

	public double getLongitude() {
		
		return longitude;
		
		
	}

	public void setLongitude(double longitude) {
		
		this.longitude = longitude;
		
	}

	public double getElevation() {
		
		return elevation;
		
		
	}

	public void setElevation(double elevation) {
		
		this.elevation = elevation;
		
		
	}
	
	public String toString() {
		
		String str;
		// "1 (2.0,3.0) 5.0\n"    tid, latitude, longitude, elevation , \n betyr ny linje, \t betyr tabilator
		
		str = "" +  time + "(" + latitude + "," + longitude + ")" + elevation + "\n";       // skriver det ovenfor. 
		return str;
		
		
		
	}
}
