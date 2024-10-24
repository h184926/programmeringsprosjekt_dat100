package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;


public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		setColor(0,0,255);
		
		for(int i = 0; i < gpspoints.length -1 ; i++) {
			int x1 = MARGIN + (int)((gpspoints[i].getLongitude() - minlon) * xstep);
	        int y1 = ybase - (int)((gpspoints[i].getLatitude() - minlat) * ystep);
	        
	        int x2 = MARGIN + (int)((gpspoints[i+1].getLongitude() - minlon) * xstep);
	        int y2 = ybase - (int)((gpspoints[i+1].getLatitude() - minlat) * ystep);
	        
	        drawLine(x1,y1, x2, y2);
		}
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
		int x = MARGIN;
	    int y = MARGIN;
		double weight = 80.0;

		setColor(0,0,0);
		
		//finner statisikk som skal vises
		
		double totalDistance = gpscomputer.totalDistance();
		double maxSpeed = gpscomputer.maxSpeed();
		double averageSpeed = gpscomputer.averageSpeed();
		double totalKcal = gpscomputer.totalKcal(weight); 
		
		double totalTime = gpscomputer.totalTime();
	    int hours = (int)(totalTime / 3600);
	    int minutes = (int)((totalTime % 3600) / 60);
	    int seconds = (int)(totalTime % 60);

	    // Formaterer tiden som en streng
	    String totalTid = String.format("%d t, %d min, %d sek", hours, minutes, seconds);

	    // Tegner statistikken i vinduet
	    drawString(String.format("Total Distance: %.2f km", totalDistance / 1000), x, y);
	    y += TEXTDISTANCE;
	    drawString("Total Time: " + totalTid, x, y); 
	    y += TEXTDISTANCE;
	    drawString(String.format("Average Speed: %.2f km/h", averageSpeed * 3.6), x, y);
	    y += TEXTDISTANCE;
	    drawString(String.format("Max Speed: %.2f km/h", maxSpeed * 3.6), x, y);
	    y += TEXTDISTANCE;
	    drawString(String.format("Total Kcal: %.2f kcal", totalKcal), x, y);
		
	}

	public void replayRoute(int ybase) {

		setSpeed(10);
		
		int radius = 5; 
		int circleId = fillCircle(MARGIN, ybase, radius); 
		
		for(int i = 0; i < gpspoints.length; i++) { 
			int x = MARGIN + (int)((gpspoints[i].getLongitude() - minlon) * xstep);
	        int y = ybase - (int)((gpspoints[i].getLatitude() - minlat) * ystep);
	        
	        moveCircle(circleId, x, y); // Flytt sirkelen langs ruten
	        pause(100);
		}
		
	}

}
