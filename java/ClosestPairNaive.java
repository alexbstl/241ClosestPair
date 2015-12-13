public class ClosestPairNaive {

	public final static double INF = java.lang.Double.POSITIVE_INFINITY;

	//
	// findClosestPair()
	//
	// Given a collection of nPoints points, find and ***print***
	//  * the closest pair of points
	//  * the distance between them
	// in the form "NAIVE (x1, y1) (x2, y2) distance"
	//

	// INPUTS:
	//  - points sorted in nondecreasing order by X coordinate
	//  - points sorted in nondecreasing order by Y coordinate
	//
/**
 *  This is the naive method for computing the smallest distance between any two points from the array of points provided in the parameter.
 *  It works by iterating over the array, and then at each point in the points array, iterating over all points after that in the array, comparing
 *  the distance between those two points with an already stored minimum distance.  It then updates the minimum distance, if the examined distance is smaller.
 *   
 * @param points an (unsorted) array of XYPoints
 * @param print print the results to the console
 */
	public static void findClosestPair(XYPoint points[], boolean print)
	{
		if(points.length==1){
			return;
		}
		double mindist = INF;
		double dist = 0.0;
		int npoints=points.length;
		XYPoint point1 = new XYPoint();
		XYPoint point2 = new XYPoint();
		int i=0;
		while(i<npoints-1){  //XYPoint[i]
				int k = i+1;
				while (k<npoints){ //XYPoint[j]
					dist=points[i].dist(points[k]);
					if(dist<mindist){
						mindist=dist;
						point1=points[i];
						point2=points[k];
					}
					k++;
				}
				i++;
		}
		if (print){
			System.out.println("NAIVE " + point1+" "+point2+" "+mindist);
		}

	}
}
