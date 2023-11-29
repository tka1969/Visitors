package com.vikings.component;

import java.util.List;

public final class Utils {

	
	// parses start and end times
	public static void ParseStartEndTimes(List<String> lines, int[] users)
	{
			TimeRange tr = new TimeRange();
		
	    lines.forEach(item -> {
					
	       	String[] times = item.split(",");
	       	String[] start = times[0].split(":");
	       	String[] end = times[1].split(":");
	       
	  	    tr.Clear();
	  	    tr.setVisitors(1);

	  	    tr.setStartHour(Integer.parseInt(start[0]));
	        tr.setStartMinute(Integer.parseInt(start[1]));
	
	        tr.setEndHour(Integer.parseInt(end[0]));
	        tr.setEndMinute(Integer.parseInt(end[1]));
	        
          int startTime = tr.getStartTime();
          int endTime = tr.getEndTime();

          // set used minutes
          while (startTime <= endTime)
          {
              users[startTime]++;

              startTime++;
          }
	        
	    });
	}

}
