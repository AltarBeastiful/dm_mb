package SMA;

//Maybe remove agent parameter
public interface IActionable {
	public boolean move(Agent a, int newX, int newY);
	public boolean pickupBox(Agent a, int x, int y);
}
