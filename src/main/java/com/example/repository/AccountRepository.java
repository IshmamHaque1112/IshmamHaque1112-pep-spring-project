package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Account;
import java.util.*;
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    Account deleteByUsername(String username);
    Account deleteById(int id);

}
