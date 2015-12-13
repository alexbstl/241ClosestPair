public class ClosestPairDC {

	public final static double INF = java.lang.Double.POSITIVE_INFINITY;

	//
	// findClosestPair()
	//
	// Given a collection of nPoints points, find and ***print***
	//  * the closest pair of points
	//  * the distance between them
	// in the form "DC (x1, y1) (x2, y2) distance"
	//

	// INPUTS:
	//  - points sorted in nondecreasing order by X coordinate
	//  - points sorted in nondecreasing order by Y coordinate
	//
	
	static double mindist = INF;
	static XYPoint a = new XYPoint();
	static XYPoint b = new XYPoint();
	/**
	 * NOTE: This method assumes that the static fields XYPoint a, XYPoint b, and mindist are reset to their 
	 * initial values ( i.e. the points are null, mindist is INF) prior to with each new initial method call.
	 * This explicity doesn't happen with each recursive call.
	 * 
	 * The method findClosestPair finds the closest distance between any two points, given two copies of an array of XYPoints: one that is sorted by X values, and
	 * one that is sorted by Y Values.  It operates via a recursive call, breaking down the array into many subproblems and comparing the results for each
	 * subproblem to the distance already stored in a static field.  In order to take into account any points that may be closer but are not checked
	 * within a sub-problem, it also runs a combine step that checks for all points whose horizontal distance from the midpoint (i.e. the most "center" X value) 
	 * of a given array is less than the already found minimum distance, and then compares all points that meat that criteria.
	 * Finally, if the boolean "print" parameter is passed as true, it prints the result to the console.
	 * 
	 * @param pointsByX an Array of points sorted by increasing X Values
	 * @param pointsByY an Array of points sorted by increasing Y Values
	 * @param print a boolean variable that prints the points in the form "DC (point a) (point b) distance" if set to true
	 * 
	 */
	public static void findClosestPair(XYPoint pointsByX[], XYPoint pointsByY[],boolean print){

		int nPoints = pointsByX.length;
		if (nPoints==1){
			return;
		}else if(nPoints==2){
			if(pointsByX[1].dist(pointsByX[0])<mindist){
				mindist = pointsByX[1].dist(pointsByX[0]);
				a=pointsByX[0];
				b=pointsByX[1];
			}return;
		}else{
			int midpointindex = (int)Math.ceil(nPoints/2.0)-1;

			XYPoint midpoint=pointsByX[midpointindex];

			//Initialize Arrays
			XYPoint[] XLeft = new XYPoint[midpointindex+1];
			XYPoint[] XRight = new XYPoint[nPoints-XLeft.length];
			XYPoint[] YLeft = new XYPoint[midpointindex+1];
			XYPoint[] YRight = new XYPoint[nPoints-XLeft.length];
			for(int i = 0; i< XRight.length;i++){
				XLeft[i]=pointsByX[i];
				XRight[i]=pointsByX[midpointindex+i+1];
			}
			XLeft[XLeft.length-1]=midpoint;


			int leftcounter=0;
			int rightcounter=0;
			for(int i = 0; i< pointsByY.length;i++){
				if(pointsByY[i].isLeftOf(midpoint)||pointsByY[i].num==midpoint.num){
					YLeft[leftcounter]=pointsByY[i];
					leftcounter++;
				}else{
					YRight[rightcounter]=pointsByY[i];
					rightcounter++;
				}
			}

			findClosestPair(XLeft,YLeft,false);
			findClosestPair(XRight,YRight,false);

			XYPoint[] centerPoints = new XYPoint[pointsByY.length];
			int counter = 0;
			for(int i = 0;i<pointsByY.length;i++){
				if (Math.abs(midpoint.x-pointsByY[i].x)<=mindist){  //looks at all points whose X distance from midpoint is less than current mindist
					centerPoints[counter]=pointsByY[i];
					counter++;
				}
			}

			for(int i = 0; i<counter;i++){
				for(int j = i+1; j<counter;j++){
					if(centerPoints[i].dist(centerPoints[j])>mindist){
						continue;
					}
					else{
						mindist=centerPoints[i].dist(centerPoints[j]);
						a=centerPoints[i];
						b=centerPoints[j];
					}
				}
			}
			if(print){
				System.out.println("HERE!!!");
				System.out.println("DC "+" "+a+" "+b+" "+mindist);
			}

		}
	}
}
