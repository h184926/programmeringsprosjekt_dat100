package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double totalDistance = 0;


	    for (int i = 0; i < gpspoints.length - 1; i++) {
	        totalDistance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
	    }

	    return totalDistance;

	}

	public double totalElevation() {

		double elevation = 0;

	    for (int i = 0; i < gpspoints.length - 1; i++) {
	        double elevationDiff = gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();
	        if (elevationDiff > 0) {
	            elevation += elevationDiff;
	        }
	    }

	    return elevation; 
		
	}

	public int totalTime() {
		
		if(gpspoints.length <2 ) {
			return 0;
		}

	    int startTime = gpspoints[0].getTime();
	    int endTime = gpspoints[gpspoints.length - 1].getTime();

	    return endTime - startTime;
		
	}
		

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];

	    for (int i = 0; i < gpspoints.length - 1; i++) {
	        double distance = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]); // Avstand i meter
	        
	        int timeDiff = gpspoints[i + 1].getTime() - gpspoints[i].getTime(); // Tidsforskjell i sekunder
	        
	        if (timeDiff > 0) {
	        	speeds[i] = distance / timeDiff; // Hastighet i meter per sekund
	        } else { 
	        	speeds[i] = 0;
	        }
	    }

	    return speeds;
		
	}
	
	public double maxSpeed() {
		
		double[] speeds = speeds();  // Få tabellen med hastigheter
	    double maxSpeed = speeds[0]; // Start med første hastighet som foreløpig maksimum

	    for (int i = 1; i < speeds.length; i++) {
	        if (speeds[i] > maxSpeed) {
	            maxSpeed = speeds[i];  // Oppdater hvis vi finner en høyere hastighet
	        }
	    }

	    return maxSpeed;
	
	}

	public double averageSpeed() {

		double average = 0;
	    double totalDistance = totalDistance(); // Total distanse i meter
	    int totalTime = totalTime();            // Total tid i sekunder

	    if (totalTime > 0) {
	        average = totalDistance / totalTime; // Gjennomsnittshastighet i meter per sekund
	    }

	    return average;
		
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;		
		double speedmph = speed * MS;

		    // Bestem MET basert på hastigheten i mph
		    if (speedmph < 10) {
		        met = 4.0;
		    } else if (speedmph < 12) {
		        met = 6.0;
		    } else if (speedmph < 14) {
		        met = 8.0;
		    } else if (speedmph < 16) {
		        met = 10.0;
		    } else if (speedmph < 20) {
		        met = 12.0;
		    } else {
		        met = 16.0;
		    }

		    // Omregne tid fra sekunder til timer
		    double hours = secs / 3600.0;

		    // Beregne forbrente kalorier
		    return met * weight * hours;
		}


	public double totalKcal(double weight) {

		double totalKcal = 0;
		double[] speeds = speeds();  // Få hastighetene mellom GPS-punktene
		int[] times = new int[gpspoints.length - 1];  // Tidsforskjell mellom punkter
           // Beregn tid mellom punktene
		  for (int i = 0; i < gpspoints.length - 1; i++) {
		      times[i] = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
		    }

		    // Legg sammen kaloriforbruket for hvert segment
		    for (int i = 0; i < speeds.length; i++) {
		        totalKcal += kcal(weight, times[i], speeds[i]);
		    }

		    return totalKcal;

		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		 double weight = 80.0; // Vekten satt til 80 kg
		 // Total tid (i sekunder)
		 int totalTime = totalTime();
		 int hours = totalTime / 3600;
		 int minutes = (totalTime % 3600) / 60;
		 int seconds = totalTime % 60;
		    
	 // Total distanse (i kilometer)
		  double totalDistanceKm = totalDistance() / 1000.0;
		    
		// Total høydemeter (i meter)
		  double totalElevation = totalElevation();
		    
	    // Maks hastighet (i km/t)
	    double maxSpeedKmPerH = maxSpeed() * 3.6;
		    
		// Gjennomsnittshastighet (i km/t)
	    double averageSpeedKmPerH = averageSpeed() * 3.6;
		    
	    // Total forbrente kalorier
	     double totalKcal = totalKcal(weight);
		    
	    System.out.println("==============================================");
	    System.out.printf("Total Time     :   %02d:%02d:%02d%n", hours, minutes, seconds);
	    System.out.printf("Total distance :   %10.2f km%n", totalDistanceKm);
	    System.out.printf("Total elevation:   %10.2f m%n", totalElevation);
	    System.out.printf("Max speed      :   %10.2f km/t%n", maxSpeedKmPerH);
	    System.out.printf("Average speed  :   %10.2f km/t%n", averageSpeedKmPerH);
	    System.out.printf("Energy         :   %10.2f kcal%n", totalKcal);
	    System.out.println("==============================================");
		
	}

}
