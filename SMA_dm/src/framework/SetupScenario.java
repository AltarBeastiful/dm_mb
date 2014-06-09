package framework;

public interface SetupScenario {
	public Scenario.AgentSpecies.Component addAgent(Object...parameters);
	public String randomUUID();
}
