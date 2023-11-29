package com.vikings.component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TimeRanges {
	private static final Logger logger = LoggerFactory.getLogger(TimeRanges.class);
	

	private List<TimeRange> timerange = new ArrayList<>();
	private List<TimeRange> sortedTimerange = new ArrayList<>();


	public TimeRanges()
	{
	}

	// adds time range sorted by visitors and time range
	public void Add(TimeRange tr)
	{
		timerange.add(tr);
		sortedTimerange.add(tr);
	}

	// output data to outpufile/screen
	public void Print(String outFileName, int numbers_prints, boolean timetofile)
	{
    BufferedWriter writer = null;
		
		try {
    
	    if (timetofile) {
	    	writer = new BufferedWriter(new FileWriter(outFileName, false));
	    }
   
	    Collections.sort(sortedTimerange);
	    
	    writer.append("Sorted times + Unsorted times\n\n\n");
	    writer.append("Sorted times:\n");
	    
	    System.out.println();
	
	    int index = 0;
	
	    for (TimeRange tr : sortedTimerange) {
	    
	    	if (tr.getVisitors() > 0) {
	    	
	        if (index < numbers_prints)
	        {
	        	System.out.format(
	                "%02d:%02d, %02d:%02d, %d\n",
	                tr.getStartHour(),
	                tr.getStartMinute(),
	                tr.getEndHour(),
	                tr.getEndMinute(),
	                tr.getVisitors()
	        			);
	        }
	
	        if (timetofile)
	        {
	        	writer.append(String.format(
	                "%02d:%02d, %02d:%02d, %d\n",
	                tr.getStartHour(),
	                tr.getStartMinute(),
	                tr.getEndHour(),
	                tr.getEndMinute(),
	                tr.getVisitors()
	                )
	        			);
	        }
    		}
        index++;
	    }
	
	    if (timetofile)
	    {
	    		writer.append("\n\n");
	    		writer.append("Unsorted times:\n");
	
	        for(TimeRange tr : timerange) {
	        	writer.append(String.format(
	                "%02d:%02d, %02d:%02d, %d\n",
	                tr.getStartHour(),
	                tr.getStartMinute(),
	                tr.getEndHour(),
	                tr.getEndMinute(),
	                tr.getVisitors()
	                )
	        			);
	        }
	    }
	    if (timetofile && writer != null) {
	    	writer.close();
	    }
	    
		}
	  catch (IOException excp) {
	  	logger.error("{}\n", excp);
	  }
	}
}

