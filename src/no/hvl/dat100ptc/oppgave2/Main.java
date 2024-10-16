package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		String s = "2017-08-13T08:52:26.000Z";
		String timeStreng = s.substring(11, 13);
		int t = Integer.parseInt(timeStreng);
		System.out.println(t);
	
        // Oppretter to GPSPoint-objekter
        GPSPoint point1 = new GPSPoint(31946, 60.385390, 5.217217, 61.9);
        GPSPoint point2 = new GPSPoint(32000, 60.385400, 5.217220, 62.0);

        // Oppretter et GPSData-objekt med plass til 2 punkter
        GPSData gpsData = new GPSData(2);

        // Setter inn de to GPSPoint-objektene
        gpsData.insertGPS(point1);
        gpsData.insertGPS(point2);

        // Skriver ut GPS-dataene
        gpsData.print();
    }
}
