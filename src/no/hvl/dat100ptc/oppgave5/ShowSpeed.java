package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	public void showSpeedProfile(int ybase) {
		int x = MARGIN; 
		double[] speeds = gpscomputer.speeds(); 
		double averageSpeed = gpscomputer.averageSpeed() * 3.6; //gjennomsnittshastighet

		// Tegner hver hastighet
		for (int i = 0; i < speeds.length; i++) {
			int speed = (int) (speeds[i] * 3.6); // Konverter m/s til km/t
			
			setColor(0,0,255); // Blå for hastigheter
			drawLine(x, ybase, x, ybase - speed); // Tegner linje for hastighet
			
			x += 2; // 2 i mellom hver hastighet x
		}
		
		// Tegner gjennomsnittshastighetslinjen
		setColor(0,255,0); // Grønn for gjennomsnitt
		int avgY = ybase - (int) averageSpeed; // Y-posisjon for gjennomsnitt
		drawLine(MARGIN, avgY, MARGIN + 2 * speeds.length, avgY); // Tegner linje for gjennomsnittshastighet
		
		// Sett vindutittelen til å vise gjennomsnittshastigheten
		setTitle("Gjennomsnittshastigheten: " + String.format("%.2f", averageSpeed) + " km/h"); 
	}
}
