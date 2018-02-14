package pathfinder.core.time;

public class Time {

	private double lastTime;

	public Time() {

		lastTime = System.nanoTime();

	}
	
	public double getTime() {
		
		return System.nanoTime() / 1000000000;
		
	}
	
	public float deltaTime() {
		
		double currentTime = getTime();
		
		float deltaTime = (float)(currentTime - lastTime);
		
		lastTime = currentTime;
		
		return deltaTime;
		
	}
	
	public double lastTime() {
		
		return lastTime;
		
	}

}
