package cc.sch;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.sybase.jdbc4.jdbc.SybDriver;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.sql.DriverManager;
import java.sql.SQLException;

import static org.quartz.SimpleScheduleBuilder.*;

public class App {
  public static void main(String[] args) {

    try {
      DriverManager.registerDriver(new SybDriver());
      
      // Grab the Scheduler instance from the Factory
      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

      // and start it off
      scheduler.start();

      // define the job and tie it to our HelloJob class
      JobDetail job = newJob(HelloJob.class)
          .withIdentity("job1", "group1")
          .build();

      // Trigger the job to run now, and then repeat every 40 seconds
      Trigger trigger = newTrigger()
          .withIdentity("trigger1", "group1")
          .startNow()
                .withSchedule(simpleSchedule()
                  .withIntervalInSeconds(2)
                  .repeatForever())            
          .build();

      // Tell quartz to schedule the job using our trigger
      scheduler.rescheduleJob(trigger.getKey(), trigger);
      
//      Thread.sleep(13000);
      
//      scheduler.shutdown(true);

    } catch (SchedulerException se) {
      se.printStackTrace();
//    } catch (InterruptedException e) {
//      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
