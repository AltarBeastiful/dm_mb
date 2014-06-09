package framework;

public interface SetupScenario {
	public Scenario.AgentSpecies.Component addAgent(Object...parameters);
	public String randomUUID();
	
	public void redirectAgentLogToFile(String file);
	public void redirectAgentLogToConsole(boolean isTrue);
}
