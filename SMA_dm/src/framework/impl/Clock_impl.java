package framework.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import framework.Clock;
import framework.SpeedRegulation;
import framework.Tick;

public class Clock_impl extends Clock implements Tick, SpeedRegulation, Runnable{
	private Map<String, Integer> agentToken;
	private Map<String, Semaphore> waitingThreads;
	
	private ScheduledFuture<?> tickthread = null;
	private int currentSpeed;
	private boolean isFullSpeed;
	
	public Clock_impl(int speed) {
		agentToken = new ConcurrentHashMap<String, Integer>();
		waitingThreads = new ConcurrentHashMap<String, Semaphore>();
		
		currentSpeed = speed;
		isFullSpeed = false;
	}
	
	@Override
	protected Tick make_tick() {
		return this;
	}

	@Override
	protected SpeedRegulation make_speed() {
		return this;
	}

	@Override
	public void pause() {
		//TODO : find better way than cancel. A real oause from current token attribution owuld be cool. ! of deadlock if paused when accessing synchronized data 
		if(tickthread != null){
			tickthread.cancel(false);
		}
		tickthread = null;
	}

	@Override
	public void play() {
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		tickthread = exec.scheduleAtFixedRate(this, 0, currentSpeed, TimeUnit.MILLISECONDS);
	}

	@Override
	public void step() {
		run();
	}

	@Override
	public void setSpeed(int speed) {
		currentSpeed = speed;
		tickthread.cancel(false);
		play();
		//TODO : make test for setspeed
	}

	@Override
	public void setFullSpeed(boolean isFullSpeed) {
		//TODO : Implement
		//this.isFullSpeed = isFullSpeed;
	}

	@Override
	public void getTick(String uid) {
		//TODO synchronize access 
		if(!agentToken.containsKey(uid)) {
			agentToken.put(uid, 0);
		}
		
		int tokencount = agentToken.get(uid);
		
		while(tokencount == 0){
			Semaphore l = new Semaphore(0);
			waitingThreads.put(uid, l);
			try {
				l.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tokencount = agentToken.get(uid);
		}
		
		//TODO : token count can change in the meantime, don't use var tokencount for minus. agentToken = monitor?
		agentToken.put(uid, tokencount - 1);
	}

	@Override
	public void run() {	
		try{
		for (Entry<String, Integer> entry : agentToken.entrySet()) {
		    String uid = entry.getKey();
		    int tokencount = entry.getValue();
		    
		    agentToken.put(uid, tokencount + 1);
		    
		    if(tokencount == 0 && waitingThreads.containsKey(uid)) {
		    	waitingThreads.get(uid).release();
		    	waitingThreads.remove(uid);
		    }
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
