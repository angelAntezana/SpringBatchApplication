//package com.batch.steps;
//
//import com.batch.entity.Person;
//import com.batch.service.IPersonService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//@Slf4j
//public class ItemWriterStep implements Tasklet {
//
//    @Autowired
//    private IPersonService personService;
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
//        log.info("-----------------> START OF WRITING STEP <-----------------");
//
//        List<Person> personList = (List<Person>) chunkContext
//                .getStepContext()
//                .getStepExecution()
//                .getJobExecution()
//                .getExecutionContext()
//                .get("personList");
//
//        personList.forEach(person -> {
//            if (person != null) {
//                log.info("Data of person: ");
//                log.info(person.toString());
//            }
//        });
//
//        personService.saveAll(personList);
//
//
//        log.info("-----------------> END OF WRITING STEP <-----------------");
//
//        return RepeatStatus.FINISHED;
//    }
//}
