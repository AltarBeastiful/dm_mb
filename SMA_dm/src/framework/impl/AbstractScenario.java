package framework.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import framework.ActListener;
import framework.ActObservable;
import framework.Agent;
import framework.AgentJoining;
import framework.Clock;
import framework.Environnement;
import framework.Gui;
import framework.LogObserver;
import framework.Scenario;
import framework.SetupScenario;

public abstract  class AbstractScenario<Context, Actionable, Setup extends SetupScenario> extends Scenario<Context, Actionable, Setup> implements SetupScenario, ActObservable, LogObserver {
	private List<ActListener> listeners;
	protected List<Scenario.AgentSpecies.Component<Context, Actionable, Setup>> agents;
	private int defaultSpeed;
	private boolean agentLogToConsole;
	private List<BufferedWriter> agentLogs;
	
	public AbstractScenario(int defaultSpeed) {
		agents = new ArrayList<Scenario.AgentSpecies.Component<Context, Actionable, Setup>>();
		listeners = new ArrayList<ActListener>();
		agentLogs = new ArrayList<BufferedWriter>();
		
		this.defaultSpeed = defaultSpeed;
		
		this.agentLogToConsole = false;
	}
	
	@Override
	protected void start() {
		super.start();
		
		this.provides().observeAllAgents().addActListener(this.parts().gui().actListener());
	}

	@Override
	protected Clock make_clock() {
		return new Clock_impl(defaultSpeed);
	}

	@Override
	protected abstract Environnement<Context, Actionable> make_env();
	
	protected abstract Agent<Context, Actionable> make_agent(String id);

	@Override
	protected AgentSpecies<Context, Actionable, Setup> make_AgentSpecies(String id) {
		final String uid = id;
		return new AgentSpecies<Context, Actionable, Setup>() {

			@Override
			protected Agent<Context, Actionable> make_agent() {
				return AbstractScenario.this.make_agent(uid);
			}
			
		};
	}

	@Override
	protected AgentJoining<Context, Actionable> make_rjc() {
		return new AgentJoining_impl<Context, Actionable>();
	}

	@Override
	protected Setup make_setup() {
		return (Setup) this;
	}

	@Override
	public Scenario.AgentSpecies.Component<Context, Actionable, Setup> addAgent(Object...parameters) {
		Scenario.AgentSpecies.Component<Context, Actionable, Setup> a = newAgentSpecies(randomUUID());
		updateListeners(a);
		agents.add(a);
		a.log().registerToLog(this);
		
		return a;
	}

	@Override
	public String randomUUID() {
		return UUID.randomUUID().toString();
	}

	@Override
	protected ActObservable make_observeAllAgents() {
		return this;
	}
	
	@Override
	public void removeActListener(ActListener ac) {
		listeners.remove(ac);
		
		for (Scenario.AgentSpecies.Component<Context, Actionable, Setup> species : agents ) {
			species.actObservable().removeActListener(ac);;
		}
	}
	
	@Override
	public void fireAct() {
		for (Scenario.AgentSpecies.Component<Context, Actionable, Setup> species : agents ) {
			species.actObservable().fireAct();
		}
	}
	
	@Override
	public void addActListener(ActListener ac) {
		if (!listeners.contains(ac))
			listeners.add(ac);
		
		for (Scenario.AgentSpecies.Component<Context, Actionable, Setup> species : agents ) {
			species.actObservable().addActListener(ac);
		}
	}
	
	protected void updateListeners(Scenario.AgentSpecies.Component<Context, Actionable, Setup> a) {
		for (ActListener ac : listeners) {
			a.actObservable().addActListener(ac);
		}
	}

	@Override
	protected abstract Gui<Context> make_gui();


	@Override
	public void redirectAgentLogToConsole(boolean isTrue) {
		this.agentLogToConsole = isTrue;
	}

	@Override
	public void redirectAgentLogToFile(String file) {
		BufferedWriter writer = null;
		
        try {
        	String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File logFile = new File(file + timeLog);
            System.out.println(logFile.getCanonicalPath());
            
            writer = new BufferedWriter(new FileWriter(logFile));
        } catch (Exception e) {
            e.printStackTrace();
        }

        agentLogs.add(writer);
	}
	
	@Override
	public void logFired(String log) {
		if(agentLogToConsole){
			System.out.println(log);
		}
		
		for(BufferedWriter w : agentLogs) {
			try {
				w.write(log);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
