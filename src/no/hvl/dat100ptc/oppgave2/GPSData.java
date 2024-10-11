package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {     // konstruktør 
		
		 this.gpspoints = new GPSPoint [antall];
		 this.antall = 0;
		 
		
		}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;
		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO 
	
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		throw new UnsupportedOperationException(TODO.method());

		// TODO 
		
	}

	public void print() {   // begynne på null slutte på i<antall, for-løkke
		

		throw new UnsupportedOperationException(TODO.method());

		// TODO 
	}
}
