package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

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

		double min;
		
		min = da[0];
		
		for (double i : da) {
			if( i < min) {
				min = i;
			}
		}
		
		return min;
	
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		
		double [] latitudesTab = new double [gpspoints.length];
		
		for(int i = 0; i < gpspoints.length; i++) {
			gpspoints[i].getLatitude();
			
			latitudesTab [i] = gpspoints[i].getLatitude();
		}
		
		
		return latitudesTab;
		
		
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double [] longitudesTab = new double [gpspoints.length];
		
		for(int i = 0; i < gpspoints.length; i++) {
			
			longitudesTab[i] = gpspoints[i].getLongitude();
		}
		
		return longitudesTab;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		
		double latitude1 = toRadians(gpspoint1.getLatitude());
		double latitude2 = toRadians(gpspoint2.getLatitude());
		double longitude1 = toRadians(gpspoint1.getLongitude());
		double longitude2 = toRadians(gpspoint2.getLongitude());
		
		double deltaLat = (latitude2 - latitude1);
		double deltaLong = (longitude2 - longitude1);
		
		double a = pow(sin(deltaLat/2),2) + cos(latitude1) * cos(latitude2) *pow(sin(deltaLong/2),2);
		double c = 2 * atan2(sqrt(a),sqrt(1 - a));
		
		d = R * c;
		
		return d;
		

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double secs = gpspoint2.getTime() - gpspoint1.getTime();
		double timer = secs / (60 * 60);
		double distanse =  distance(gpspoint1,gpspoint2) / 1000; 
		double speed = distanse / timer;

		return speed;
	}

	public static String formatTime(int secs) {
		
		int timer = secs / 3600;
		int min = (secs % 3600) / 60;
		int sec = secs % 60 ;


		String timestr = String.format("%02d:%02d:%02d", timer, min, sec);
		
		
		return String.format("%10s",timestr);
	

		

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		double roundedDouble = Math.round(d * 100.0) / 100.0; 
		
		String str = Double.toString(roundedDouble);

		return String.format("%" + TEXTWIDTH + "s", str);
		
	}
}
