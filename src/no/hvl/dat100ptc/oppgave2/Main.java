package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		String s = "2017-08-13T08:52:26.000Z";
		String timeStreng = s.substring(11, 13);
		int t = Integer.parseInt(timeStreng);
		System.out.println(t);
		
	}
}
