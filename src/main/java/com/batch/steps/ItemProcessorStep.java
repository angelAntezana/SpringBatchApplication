//package com.batch.steps;
//
//import com.batch.entity.Person;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//@Slf4j
//public class ItemProcessorStep implements Tasklet {
//
//
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//        log.info("-----------------> START OF PROCESSOR STEP <-----------------");
//
//        List<Person> personList = (List<Person>) chunkContext
//                                        .getStepContext()
//                                        .getStepExecution()
//                                        .getJobExecution()
//                                        .getExecutionContext()
//                                        .get("personList");
//
//        List<Person> personFinalList = personList.stream().map(person -> {
//            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//            person.setInsertionDate(format.format(LocalDateTime.now()));
//            return  person;
//        }).toList();
//
//        chunkContext.getStepContext()
//                    .getStepExecution()
//                    .getJobExecution()
//                    .getExecutionContext()
//                    .put("personList", personFinalList);
//
//        log.info("-----------------> END OF PROCESSOR STEP <-----------------");
//
//
//        return RepeatStatus.FINISHED;
//    }
//}
