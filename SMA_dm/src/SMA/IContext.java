package SMA;

public interface IContext {
//	public Entity queryPosition(int x, int y);

	public boolean isFree(int x, int y);
	public boolean isObstacle(int x, int y);
	public boolean isCaryable(int x, int y);
	public boolean isAgent(int x, int y);
	public boolean isAgentCarying(int x, int y);
}
