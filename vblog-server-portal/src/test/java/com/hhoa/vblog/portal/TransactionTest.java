package com.hhoa.vblog.portal;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * TransactionTest.
 *
 * @author hhoa
 * @since 2022/5/31
 **/

@SpringBootTest(classes = BlogPortalApplication.class)
public abstract class TransactionTest {
    @Autowired
    PlatformTransactionManager transactionManager;
    TransactionStatus transactionStatus;

    @BeforeEach
    public void setUp() {
        transactionStatus = transactionManager.getTransaction(new DefaultTransactionAttribute());
    }

    @AfterEach
    public void tearDown() {
        transactionManager.rollback(transactionStatus);
    }
}
