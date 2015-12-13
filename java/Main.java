//
// MAIN.JAVA
// Main driver code for CSE 241 Closest Pair Lab.
//
// WARNING: ANY CHANGES YOU MAKE TO THIS FILE WILL BE DISCARDED BY THE
// AUTO-GRADER!  Make sure your code works with the unmodified
// original driver before you turn it in.  (You may wish to modify
// your local copy to do the timing experiments requested by the lab.)

import java.util.*;

public class Main {

	static final long seed = 87654321;

	public static void main(String args[])
	{
		PRNG prng = new PRNG(seed); // seed random number generator
		XYPoint points [];
//		int times=4000;
//		long[] timesDC = new long[times];
//		long[] timesNaive = new long[times];
//
//		long maxDC=0;
//		long maxNaive=0;
//		long minNaive=Integer.MAX_VALUE;
//		long minDC=Integer.MAX_VALUE;
//		double averageDC;
//		double averageNaive;
		int nPoints = 0;
		String fileName;

		if (args.length >= 1)
		{
			fileName = args[0];
		}
		else
		{
			System.out.println("Syntax: Main [ <filename> | @<numPoints> ]");
			return;
		}


		// A filename argument of the form '@x', where x is a non-negative
		// integer, allocates x random points.  Any other argument is
		// assumed to be a file from which points are read.

		if (fileName.charAt(0) != '@')
		{
			points = PointReader.readXYPoints(fileName);
			nPoints = points.length;
		}
		else
		{
			nPoints = Integer.parseInt(fileName.substring(1));
			points = null;
		}

		if (nPoints < 2)
		{
			System.err.println("ERROR: need at least two points");
			return;
		}

		// Generate a set of points if one was not read.
		// When timing, call genPointsAtRandom() as shown
		// each time you want a new set of points.
		if (points == null)
			points = genPointsAtRandom(nPoints, prng);

		// run the DC algorithm
//		for(int i = 0; i< timesDC.length;i++)
		{
			ClosestPairDC.a=null;
			ClosestPairDC.b=null;
			ClosestPairDC.mindist=java.lang.Double.POSITIVE_INFINITY;
			//points=genPointsAtRandom(nPoints,prng);
			XComparator lessThanX = new XComparator();
			YComparator lessThanY = new YComparator();

			/////////////////////////////////////////////////////////////////
			// DC CLOSEST-PAIR ALGORITHM STARTS HERE	

			Date startTime = new Date();

			// Ensure sorting precondition for divide-and-conquer CP
			// algorithm.  NB: you should *not* have to call sort() in
			// your own code!

			// The algorithm expects two arrays containing the same points.
			XYPoint pointsByX [] = Arrays.copyOf(points, points.length);
			XYPoint pointsByY [] = Arrays.copyOf(points, points.length);

			Arrays.sort(pointsByX, lessThanX); // sort by x-coord
			Arrays.sort(pointsByY, lessThanY); // sort by y-coord

			ClosestPairDC.findClosestPair(pointsByX, pointsByY, true);

			Date endTime = new Date();

			// DC CLOSEST-PAIR ALGORITHM ENDS HERE
			/////////////////////////////////////////////////////////////////

			long elapsedTime = endTime.getTime() - startTime.getTime();
//			maxDC=elapsedTime>maxDC?elapsedTime:maxDC;
//			minDC=elapsedTime<minDC?elapsedTime:minDC;
//
//			timesDC[i]=elapsedTime;
						System.out.println("For n = " + points.length + 
								", the elapsed time is " +
								elapsedTime + " milliseconds.");
						System.out.println("");
//			Date startTime2 = new Date();
//
//			ClosestPairNaive.findClosestPair(points, false);
//
//			Date endTime2 = new Date();
//			//
//			long elapsedTime2 = endTime2.getTime() - startTime2.getTime();
//			timesNaive[i]=elapsedTime2;
//			maxNaive=elapsedTime>maxNaive?elapsedTime2:maxNaive;
//			minNaive=elapsedTime<minNaive?elapsedTime2:minNaive;
			//
			//		System.out.println("For n = " + points.length + 
			//				", the naive elapsed time is " +
			//				elapsedTime2 + " milliseconds.");
			//		System.out.println("--------------------------------");

		}	
//		double totalDC=0;
//		double totalNaive=0;
//		double varianceDC=0;
//		double varianceNaive=0;
//
//		for (int i = 0 ; i<timesDC.length;i++){
//			totalDC+=timesDC[i];
//			totalNaive+=timesNaive[i];
//		}
//		averageDC=totalDC/timesDC.length;
//		averageNaive=totalNaive/timesNaive.length;
//
//		for (int i = 0 ; i<timesDC.length;i++){
//			varianceDC+=((timesDC[i]-averageDC)*(timesDC[i]-averageDC));
//			varianceNaive+=((timesNaive[i]-averageNaive)*(timesNaive[i]-averageNaive));
//
//		}
//		varianceDC/=(timesDC.length-1);
//		varianceNaive/=(timesNaive.length-1);
//		double stdevDC=Math.sqrt(varianceDC);
//		double SEDC = stdevDC/Math.sqrt(timesDC.length);
//		double reportValDC=SEDC*1.660391;
//		double stdevNaive=Math.sqrt(varianceNaive);
//		double SENaive = stdevNaive/Math.sqrt(timesNaive.length);
//		double reportValNaive=SENaive*1.645;
//		System.out.println("DC: ");
//		System.out.println("Max: "+maxDC+"ms Min: "+minDC+"ms Average: "+averageDC+"ms");
//		System.out.println("STDev: "+stdevDC);
//		System.out.println("Report as: "+averageDC+(" +/- ")+reportValDC);
//		System.out.println("Naive: ");
//		System.out.println("Max: "+maxNaive+"ms Min: "+minNaive+"ms Average: "+averageNaive+"ms");
//		System.out.println("STDev: "+stdevNaive);
//		System.out.println("Report as: "+averageNaive+(" +/- ")+reportValNaive);
//

		// run the naive algorithm
		{
		    Date startTime = new Date();

		    ClosestPairNaive.findClosestPair(points, true);

		    Date endTime = new Date();

		    long elapsedTime = endTime.getTime() - startTime.getTime();

		    System.out.println("For n = " + points.length + 
				     ", the naive elapsed time is " +
				     elapsedTime + " milliseconds.");
		    System.out.println("");
		}
	}


	//
	// genPointsAtRandom()
	// Generate an array of specified size containing
	// points with coordinates chosen at random, using
	// the specified random sequence generator.
	//

	static XYPoint[] genPointsAtRandom(int nPoints, 
			PRNG prng) 
	{
		XYPoint points[] = new XYPoint [nPoints];

		double x = 0.0;
		double y = 0.0;

		double step = Math.sqrt(nPoints);

		for (int j = 0; j < nPoints; j++) 
		{
			// jitter next point's X coordinate
			x += 10000.0 * (prng.nextDouble() - 0.5);

			// move the Y coordinate a random amount up,
			// while keeping it within limits [0 .. nPoints)
			y = (y + step * prng.nextDouble()) % nPoints;

			points[j] = new XYPoint((int) Math.round(x), 
					(int) Math.round(y));
		}

		return points;
	}
}
