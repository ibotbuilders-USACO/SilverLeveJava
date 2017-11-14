import java.io.IOException;
import java.lang.*;

public class Test {
	
	public static void main(String[] args) throws IOException
	{
		int ballTravelPixels = 188,  iPadInchInRL = 5;
		  double iPadBallTravel, realBallTravel, mph, xDiff,yDiff,distance3;
		  int beginInchX = 100, beginInchY = 0, endInchX = 200, endInchY = 0;
		  xDiff = Math.pow(endInchX-beginInchX,2);
		  yDiff = Math.pow(endInchY-beginInchY,2);
		  distance3 = Math.sqrt(xDiff+yDiff);
	     iPadBallTravel = ballTravelPixels/distance3;
	     realBallTravel = iPadBallTravel * iPadInchInRL * 4;
	     mph = realBallTravel * 3600/63360;
	     
	     System.out.println("The ball's speed is " + mph + " mph.");//these co-ords are relative to the component
	}
}
