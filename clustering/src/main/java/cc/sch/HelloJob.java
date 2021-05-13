package cc.sch;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job {
  
  private final Logger log = LoggerFactory.getLogger(getClass());

  public void execute(JobExecutionContext context) throws JobExecutionException {
    
    log.info("hi job");
    log.info("sleep start");
    try {
      Thread.sleep(15*1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("sleep end");
    
  }

}
