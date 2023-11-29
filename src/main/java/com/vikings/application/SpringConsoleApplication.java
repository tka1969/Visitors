package com.vikings.application;

import com.vikings.component.TimeRange;
import com.vikings.component.TimeRanges;
import com.vikings.component.Utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringConsoleApplication implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(SpringConsoleApplication.class);

    public static void main(String[] args) {
        logger.info("APPLICATION EXECUTION - INITIATED");
        SpringApplication.run(SpringConsoleApplication.class, args);
        logger.info("APPLICATION EXECUTION - COMPLETED");
    }

		@Override
		public void run(ApplicationArguments args) throws Exception {

      // input file needed at least
      if (args.getSourceArgs().length < 1)
      {
          Usage();
          return;
      }
     
      String  fileName = args.getNonOptionArgs().get(0);
      String  outFileName = "";
      boolean verbose = false;
      boolean timetofile = false;
      int numbers_prints = Integer.MAX_VALUE;
		  
      final Set<String>  optionNames = args.getOptionNames();
      
      for (String option: optionNames) {
      	switch (option.toLowerCase()) {
      		case "fo":
      			if (args.getOptionValues(option).isEmpty() || args.getOptionValues(option).get(0).isEmpty()) {
      				System.out.format("ERROR: Missing output file name\n");
      				Usage();
      				return;
      			}
      			outFileName = args.getOptionValues(option).get(0);
      			timetofile = true;
      			break;
      		case "v":
      			verbose = true;
      			break;
      		case "np":
      			if (args.getOptionValues(option).isEmpty()) {
      				System.out.format("ERROR: Missing number ranges value\n");
      				Usage();
      				return;
      			}
      			numbers_prints = Integer.parseInt(args.getOptionValues(option).get(0));
      			break;
  		  }
      }

		  int[] users = new int[24 * 60 + 1];
      List<String> lines = Files.readAllLines(Paths.get(fileName));
      
      Utils.ParseStartEndTimes(lines, users);
      
      TimeRanges trs = new TimeRanges();
      
      // concatenate used minutes into time intervals with same visitor count
      int timePos = 0;
      int end = 24 * 60 + 1;
      int range_start;
      int visitors = 0;

      while (timePos < end && users[timePos] == 0)
      	timePos++;

      visitors = users[timePos];
      range_start = timePos;

      while (timePos <= end)
      {
          if (visitors != users[timePos])
          {
          		TimeRange  tr = new TimeRange();
              tr.setVisitors(visitors);
              tr.setStartTime(range_start);
              tr.setEndTime(timePos - 1);

              trs.Add(tr);

              while (timePos < end && users[timePos] == 0)
              	timePos++;

              if (timePos >= end)
                  break;

              visitors = users[timePos];
              range_start = timePos;
          }
          
          if ((verbose == true) && (users[timePos] > 0)) {
          	System.out.format("%02d:%02d, %d\n", timePos / 60, timePos % 60, users[timePos]);
          }
          timePos++;
      }

      // output data
      trs.Print(outFileName, numbers_prints, timetofile);
		}

		 // Inform user about usage of this program
		 void Usage()
		 {
			 System.out.println();
			 System.out.println("Usage: visitors [options] <input file>\noptions:");
		
			 System.out.println("  --v   verbose (print progress messages)");
			 System.out.println("  --fo=<output file> Output goes to given file.");
			 System.out.println("  --np=<number ranges> Print number of time ranges by visitors to screen. (default all rages)");
			 System.out.println("  Flags may be either upper or lower case.");
			 System.out.println();
		 }
}

