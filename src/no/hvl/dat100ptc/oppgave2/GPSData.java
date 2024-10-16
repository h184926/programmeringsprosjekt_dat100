package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
	    this.gpspoints = new GPSPoint[n];
	    this.antall = 0;
	}


	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {
		
	    if (antall < gpspoints.length) {
	        gpspoints[antall] = gpspoint;  // Sett inn GPS-punkt
	        antall++;  // Øk antall med 1
	        return true;  // Innsetting vellykket
	    }
	    return false;  // Ingen plass igjen i tabellen
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {
		
	    GPSPoint gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
	    return insertGPS(gpspoint);
	}


	public void print() {   // begynne på null slutte på i<antall, for-løkke
		System.out.println("====== GPS Data - START ======");
		
		for (int i = 0; i < antall; i++) {
			GPSPoint point = gpspoints[i];
			
			System.out.print((i + 1) + " (" + point.getLatitude() + "," + point.getLongitude() + ") " + point.getElevation() + "\n");

		}
	
		System.out.println("====== GPS Data - SLUTT ======");
		
		
	}
}