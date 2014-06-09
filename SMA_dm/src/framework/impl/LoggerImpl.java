package framework.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import framework.LogObserver;
import framework.LogQueryService;
import framework.Logger;
import framework.LoggerService;

public class LoggerImpl extends Logger implements LoggerService, LogQueryService{
	private List<String> logs;
	private List<LogObserver> logsObserver;
	
	public LoggerImpl() {
		logs = new ArrayList<String>();
		logsObserver = new ArrayList<LogObserver>();
	}

	@Override
	protected LoggerService make_logger() {
		return this;
	}

	@Override
	protected LogQueryService make_log() {
		return this;
	}

	@Override
	public List<String> fullLog() {
		return logs;
	}

	@Override
	public void registerToLog(LogObserver o) {
		logsObserver.add(o);
	}

	@Override
	public void log(String message) {
		String logMessage = ""; 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.S");
		
		logMessage = format.format(Calendar.getInstance().getTime()) + " : " + message;
		logs.add(logMessage);
		fireLogObserver(logMessage);
	}
	
	private void fireLogObserver(String log) {
		for(LogObserver l : logsObserver) {
			l.logFired(log);
		}
	}

}
