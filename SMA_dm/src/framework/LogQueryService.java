package framework;

import java.util.List;

public interface LogQueryService {
	public List<String> fullLog();
	
	public void registerToLog(LogObserver o);
}
