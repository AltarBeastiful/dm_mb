package framework.impl;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class Clock_ImplTest {

	@Test
	public void shouldBeStoppedAtInit(){
		Clock_impl clock = new Clock_impl(1);
		
		StubAgent a = new StubAgent("1", clock);
		new Thread(a).start();
		
		assertEquals(0, a.nbTickGiven);
	}
	
	@Test
	public void shouldAllowoRunEveryXXMs() throws InterruptedException {
		Clock_impl clock = new Clock_impl(50);
		
		StubAgent a = new StubAgent("1", clock);
		ExecutorService pool = Executors.newSingleThreadExecutor();
		pool.submit(a);

		assertEquals(0, a.nbTickGiven);
		
		clock.play();
		
		//Allow the thread to restart and execute rest of function
		Thread.sleep(1);
		
		assertEquals(1, a.nbTickGiven);	
		
		pool.submit(a);
		
		// Thread is blocked waiting for next timer
		assertEquals(1, a.nbTickGiven);	
		
		Thread.sleep(51);
		
		//Second tick happened and waked up the thread
		assertEquals(2, a.nbTickGiven);	

	}
	
	@Test
	public void shouldPauseAndRestartExecution() throws InterruptedException {
		Clock_impl clock = new Clock_impl(50);
		
		StubAgent a = new StubAgent("1", clock);
		ExecutorService pool = Executors.newSingleThreadExecutor();
		pool.submit(a);
		
		assertEquals(0, a.nbTickGiven);

		clock.play();

		Thread.sleep(50);

		assertEquals(1, a.nbTickGiven);
		
		clock.pause();
		
		pool.submit(a);
		Thread.sleep(150);
		
		//Clock didn't tick because in pause
		assertEquals(1, a.nbTickGiven);
		
		clock.play();
		Thread.sleep(1);

		assertEquals(2, a.nbTickGiven);
	}
	
	private class StubAgent implements Runnable{
		private String uid;
		private Clock_impl clock;
		private int nbTickGiven;
		
		public StubAgent(String uid, Clock_impl c) {
			this.clock = c;
			this.uid = uid;
			nbTickGiven = 0;
		}
		
		public void run() {
			clock.getTick(uid);
			nbTickGiven++;
		}
	}

}
