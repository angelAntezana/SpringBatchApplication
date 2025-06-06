//package com.batch.steps;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.util.Enumeration;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipFile;
//
//@Slf4j
//public class ItemDecompressStep implements Tasklet {
//    @Autowired
//    private ResourceLoader resourceLoader;
//
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
//        log.info("-----------------> START OF DECOMPRESS STEP <-----------------");
//
//        Resource resource = resourceLoader.getResource("classpath:files/person.zip");
//        String filePath = resource.getFile().getAbsolutePath();
//
//        ZipFile zipFile = new ZipFile(filePath);
//        File destDir = new File(resource.getFile().getParent(), "destination");
//
//        if (!destDir.exists()) {
//            destDir.mkdir();
//        }
//
//        Enumeration<? extends ZipEntry> entries = zipFile.entries();
//
//        while (entries.hasMoreElements()) {
//            ZipEntry zipEntry = entries.nextElement();
//            File file = new File(destDir, zipEntry.getName());
//
//            if(file.isDirectory()) {
//                file.mkdirs();
//            } else  {
//                InputStream inputStream = zipFile.getInputStream(zipEntry);
//                FileOutputStream outputStream = new FileOutputStream(file);
//
//                byte[] buffer = new byte[1024];
//                int length;
//
//                while ((length = inputStream.read(buffer)) > 0) {
//                    outputStream.write(buffer, 0, length);
//                }
//
//                outputStream.close();
//                inputStream.close();
//            }
//        }
//
//        zipFile.close();
//
//        log.info("-----------------> END OF DECOMPRESS STEP <-----------------");
//
//        return RepeatStatus.FINISHED;
//    }
//}
