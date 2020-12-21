package org.id.bankspringbatch;

import com.sun.xml.bind.v2.runtime.output.StAXExStreamWriterOutput;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class JobRestController {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;
    @Autowired
    SpringBatchConfig springBatchConfig;
  @GetMapping("/startJob")
    public BatchStatus load() throws Exception{

      Map<String, JobParameter>params=new HashMap<>();

      params.put("time",new JobParameter(System.currentTimeMillis()));

      JobParameters jobParameters=new JobParameters(params);

      JobExecution jobExecution=jobLauncher.run(springBatchConfig.bankJob(),jobParameters);

      while(jobExecution.isRunning()){
          System.out.println(".....................");
      }
         return jobExecution.getStatus();
    }
}
