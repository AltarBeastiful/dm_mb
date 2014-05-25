package framework;

public interface ActObservable{
	public void addActListener(ActListener ac);
	public void removeActListener(ActListener ac);
	public void fireAct();
}
