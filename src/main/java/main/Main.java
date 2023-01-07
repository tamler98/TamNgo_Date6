package main;

import configuration.JPAConfig;
import entity.AccountEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.AccountRepository;
import service.AccountService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static AccountService accountService = context.getBean("accountService", AccountService.class);
    static AccountRepository accountRepository = context.getBean("accountRepository", AccountRepository.class);

    public static void main(String[] args) {
//        createNewAccount();
////        accountService.transferMoney(1, 2, 100);

    }

    public static void createNewAccount() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setOwnerName("Thomas Tamz");
        accountEntity.setBalance(100);
        accountEntity.setAccessTime(new Date());
        accountRepository.save(accountEntity);

        AccountEntity accountEntity1 = new AccountEntity();
        accountEntity1.setOwnerName("Elon Mush");
        accountEntity1.setBalance(200);
        accountEntity1.setAccessTime(new Date());
        accountRepository.save(accountEntity1);
    }
}