package service;

import configuration.JPAConfig;
import entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AccountRepository;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


@Service
public class AccountServiceTran {
    @Autowired
    static ApplicationContext context = new AnnotationConfigApplicationContext(JPAConfig.class);
    static AccountRepository accountRepository = context.getBean("accountRepository", AccountRepository.class);

    @Transactional(rollbackFor = Exception.class)
    public void transferMoney(int sourceAccountId, int  targetAccountId, double amount) throws Exception {
        AccountEntity sourceAccount = accountRepository.findById(sourceAccountId).get();
        AccountEntity targetAccount = accountRepository.findById(targetAccountId).get();

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        accountRepository.save(sourceAccount);
        if (sourceAccount.getBalance()<0) {
            throw new Exception("amount to transfer more than balance.");
        }
        targetAccount.setBalance(targetAccount.getBalance() + amount);
        accountRepository.save(targetAccount);
    }

    public void updateAccount() throws Exception {
        accountRepository.updateAccountHasAcceptDateEqualNow();
        System.out.println("Success");
    }

    public void deleteAccount() throws Exception {
        accountRepository.deleteAccountNotHasBalance();
    }

    public void listAllAccount() {
        List<AccountEntity> accountEntityList = accountRepository.findAll();
        for (AccountEntity accountEntity:accountEntityList) {
            System.out.println(accountEntity.toString());
        }
    }

    public void insertNewAccount() {
        Scanner sc = new Scanner(System.in);
        AccountEntity accountEntity = new AccountEntity();
        System.out.print("Owner Name: ");
        accountEntity.setOwnerName(sc.nextLine());
        System.out.print("Balance: ");
        accountEntity.setBalance(sc.nextDouble());
        accountEntity.setAccessTime(new Date());
        accountRepository.save(accountEntity);
    }
}
