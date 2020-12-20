package org.id.bankspringbatch.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class BankTransaction {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private Date transactionDate;
    @Transient
   private String strTransactionDate;

    private String transactionType;

    private double amount;


}
