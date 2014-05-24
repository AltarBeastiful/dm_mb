package framework.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import framework.Clock;
import framework.SpeedRegulation;
import framework.Tick;

public class Clock_impl extends Clock implements Tick, SpeedRegulation, Runnable{
	private Map<String, Integer> agentToken;
	private Map<String, Semaphore> waitingThreads;
	
	private ScheduledFuture<?> tickthread;
	private int currentSpeed;
	private boolean isFullSpeed;
	
	public Clock_impl(int speed) {
		agentToken = new HashMap<String, Integer>();
		waitingThreads = new HashMap<String, Semaphore>();
		
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
		tickthread.cancel(false);
	}

	@Override
	public void play() {
		ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
		this.tickthread = exec.scheduleWithFixedDelay(this, 0, currentSpeed, TimeUnit.MILLISECONDS);
	}

	@Override
	public void step() {
		run();
	}

	@Override
	public void setSpeed(int speed) {
		currentSpeed = speed;
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
