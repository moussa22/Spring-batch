package org.id.bankspringbatch;

import org.id.bankspringbatch.dao.BankTransaction;
//import org.id.bankspringbatch.dao.BankTransactionRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BankTransactionItemWriter implements ItemWriter<BankTransaction> {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Value(("${udemy.rabbitmq.exchange}"))
    private String exchange ;
    @Value(("${udemy.rabbitmq.routingkey}"))
    private String routingkey;
   /* @Autowired
    private BankTransactionRepository bankTransactionRepository;*/
    @Override
    public void write(List<? extends BankTransaction> list) throws Exception {

       // bankTransactionRepository.saveAll(list);

        amqpTemplate.convertAndSend(exchange,routingkey,list);
    }
}
