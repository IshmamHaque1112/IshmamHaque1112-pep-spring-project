package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Account;
import java.util.*;
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUserName(String username);
    Account deleteByUserName(String username);
    Account deleteById(int id);

}
