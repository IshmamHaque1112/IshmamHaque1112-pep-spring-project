package com.example.service;
import com.example.entity.*;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    public AccountRepository getAccountRepository(){
        return this.accountRepository;
    }
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
    public Account getAccountById(int accountid){
        Optional<Account> accountOptional = accountRepository.findById(accountid);
        if(accountOptional.isPresent()){
            Account account = accountOptional.get();
            return account;
        }
        else return null;
    }
    public Account getAccountByUsername(String username){
        Optional<Account> accountOptional = accountRepository.findByUserName(username);
        if(accountOptional.isPresent()){
            Account account = accountOptional.get();
            return account;
        }
        else return null;
    }
    public Account login_valid(String username,String password){
        Account returnedaccount=getAccountByUsername(username);
        if(returnedaccount==null){
            return null;
        }
        if(returnedaccount.getPassword().equals(password)){ 
             return returnedaccount;
        }
        return null;
    }
    public Account login_valid(Account account){
        Account returnedaccount=getAccountByUsername(account.getUsername());
        if(returnedaccount==null){
            return null;
        }
        if(returnedaccount.getPassword().equals(account.getPassword())){
            return returnedaccount;
        }
        return null;
    }
    public Account insertAccount(Account account){
        return accountRepository.save(account);
    }
    public Account insertAccount(String username,String password){
        return accountRepository.save(new Account(username,password));
    }
    public Account updateAccountPassword(String username,String password,String newpassword){
        Account returnedaccount=getAccountByUsername(username);
        if(returnedaccount.getPassword()==password){
            returnedaccount.setPassword(newpassword);
            return accountRepository.save(returnedaccount);
        }
        return null;
    }
    public Account updateAccountPassword(Account account){
        Account returnedaccount=getAccountByUsername(account.getUsername());
        if(returnedaccount.getPassword()==account.getPassword()){
            return accountRepository.save(account);
        }
        return null;
    }
    public Account deleteAccount(String username,String password){
        Account returnedaccount=getAccountByUsername(username);
        if(returnedaccount.getPassword()==password){
            accountRepository.deleteByUserName(username);
            return returnedaccount;
        }
        return null;
    }
    public Account deleteAccount(Account account){
        if(getAccountById(account.getAccountId())!=null){
        accountRepository.deleteById(account.getAccountId());
        return account;
        }
        return null;
    }




}
