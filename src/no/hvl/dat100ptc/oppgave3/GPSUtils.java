package no.hvl.dat100ptc.oppgave3;



import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {
	public static void main(String[] args) {

		System.out.println(formatTime(10921));
		System.out.println(formatDouble(1.346));
    }

	public static double findMax(double[] da) {
		//sjekk at tabellen ikke er tom

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		//sjekk at tabell ikke er tim
		double min;

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;
		
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		
		double[] breddegrad= new double[gpspoints.length];
		for (int i=0;i<gpspoints.length;i++) {
			breddegrad[i]=gpspoints[i].getLatitude();
		}
		System.out.println(breddegrad); 
		return breddegrad;
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] lengdegrad= new double[gpspoints.length];
		for (int i=0;i<gpspoints.length;i++) {
			lengdegrad[i]=gpspoints[i].getLongitude();
		}
		System.out.println(lengdegrad); 
		return lengdegrad;
		
		
		// TODO 

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		latitude1=toRadians(gpspoint1.getLatitude());
		longitude1=toRadians(gpspoint1.getLongitude());
		latitude2=toRadians(gpspoint2.getLatitude());
		longitude2=toRadians(gpspoint2.getLongitude());

		double deltaLat=latitude2-latitude1;
		double deltaLong=longitude2-longitude1;
		
		double a=compute_a(latitude1, latitude2, deltaLat, deltaLong);
		double c=compute_c(a);
		
		d=R*c;
		return d;
		
		
		


	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	
		double a=Math.pow(sin(deltaphi/2), 2)+Math.cos(phi1)*Math.cos(phi2)*Math.pow(sin(deltadelta/2), 2);
		return a;
		


	}

	private static double compute_c(double a) {

		double c=2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return c;
		


	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		
		double meters=distance(gpspoint1,gpspoint2);
		
		int time1 = gpspoint1.getTime();
		int time2 = gpspoint2.getTime();
		
		secs=time2-time1;
		speed=meters/secs;
		return speed;

		
		// TODO

	}

	public static String formatTime(int secs) {

		String timestr = ("");
		
		int hh = secs/3600;
		int mm = (secs%3600)/60;
		int ss = secs%60;
		

		timestr=String.format("%02d:%02d:%02d",hh,mm,ss);
		String tidFormat = String.format("%10s", timestr);

		return tidFormat;
		
		// TODO 
		
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		str=String.format("%10.2f", d);
		return str;
		// TODO
		
	}
}
