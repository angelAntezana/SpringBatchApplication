package com.batch.steps.chunk.inter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.lang.Nullable;



public interface IItemReaderService<T> extends ItemReader<T> {

    Logger logger = LoggerFactory.getLogger(IItemReaderService.class);


    T catchRead();


    @Override
    @Nullable
    default T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        logger.info("-----------------START READER");
        var item = this.catchRead();
        logger.info("-----------------FINISH READER: {}", item);
        return item;
    }
    
}
