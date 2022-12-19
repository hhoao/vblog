package com.hhoa.vblog.admin;


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

@SpringBootTest(classes = BlogAdminApplication.class)
public abstract class TransactionTest {
    public Long testUserId = 1L;
    public String testRegisterMail = "huanghaohhoa@163.com";
    public String testUsername = "test";
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
