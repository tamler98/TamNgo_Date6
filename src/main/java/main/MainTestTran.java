package main;

import configuration.JPAConfig;
import entity.AccountEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import repository.AccountRepository;
import service.AccountServiceTran;

import java.util.Date;
import java.util.Scanner;

public class MainTestTran {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static AccountServiceTran accountServiceTran = context.getBean("accountServiceTran", AccountServiceTran.class);
    public static void main(String[] args) throws Exception {
//        accountServiceTran.transferMoney(1, 2, 100);
//          accountServiceTran.insertNewAccount();
        accountServiceTran.listAllAccount();
//        accountServiceTran.updateAccount();
//        accountServiceTran.deleteAccount();
    }


}