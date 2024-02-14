package com.rafaa.web;

import com.rafaa.payload.ScheduleEmailRequest;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
public class EmailJobSchedulerController {
   @Autowired
   private Scheduler scheduler;

   private JobDetail buildJobDetail(ScheduleEmailRequest scheduleEmailRequest){
       JobDataMap jobDataMap = new JobDataMap();

       jobDataMap.put("email", scheduleEmailRequest.getEmail());
       jobDataMap.put("subject", scheduleEmailRequest.getSubject());
       jobDataMap.put("body", scheduleEmailRequest.getBody());

       return JobBuilder.newJob()
               .withIdentity(UUID.randomUUID().toString(), "email-jobs")
               .withDescription("Send Email Job")
               .usingJobData(jobDataMap)
               .storeDurably()
               .build();
   }

  private Trigger buildTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
      return TriggerBuilder.newTrigger()
              .forJob(jobDetail)
              .withIdentity(jobDetail.getKey().getName(),"email-triggers")
              .withDescription("Send Email Trigger")
              .startAt(Date.from(startAt.toInstant()))
              .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
              .build();
  }

}
