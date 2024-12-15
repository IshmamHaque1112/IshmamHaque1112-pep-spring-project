package com.example.controller;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.*;
import com.example.service.*;
import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    @Autowired 
    private AccountService accountService;
    @Autowired
    private MessageService messageService; 
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        List<Account> accounts=new ArrayList<Account>();
        accounts=accountService.getAllAccounts();
        if(accounts.size()==0) return ResponseEntity.status(200).build(); 
        else return ResponseEntity.status(200).body(accountService.getAllAccounts());
    }
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> messages=new ArrayList<Message>();
        messages=messageService.getAllMessages();
        if(messages.size()==0) return ResponseEntity.status(200).build(); 
        else return ResponseEntity.status(200).body(messageService.getAllMessages());
    }
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessagebyID(@PathVariable int message_id){
        if(messageService.getMessagebyID(message_id)==null){
            return ResponseEntity.status(200).build();
        }
        else return ResponseEntity.status(200).body(messageService.getMessagebyID(message_id));
    }
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getMessagesbyAccountID(@PathVariable int account_id){
        if(accountService.getAccountById(account_id)==null){
            List<Message> nulllist=new ArrayList<Message>(); 
            return ResponseEntity.status(200).body(nulllist);
        }
        else return ResponseEntity.status(200).body(messageService.getMessagesByAccountId(account_id));
    }
    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message){
        if((accountService.getAccountById(message.getPostedBy())==null)||(message.getMessageText().length()==0)||(message.getMessageText().length()>255)){
            return ResponseEntity.status(400).build();
        }
        else return ResponseEntity.status(200).body(messageService.insertMessage(message));
    }
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessagebyID(@PathVariable int message_id){
        if(messageService.getMessagebyID(message_id)!=null){
          messageService.deleteMessagebyID(message_id);
          return ResponseEntity.status(200).body(1);
        }
        else return ResponseEntity.status(200).build();
    }
    @DeleteMapping("/accounts")
    public ResponseEntity<Account> deleteAccount(@RequestBody Account account){
        if(accountService.getAccountById(account.getAccountId())==null){
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(200).body(accountService.deleteAccount(account));
    }
    @PostMapping("/login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account){
        if(accountService.login_valid(account)!=null){
            return ResponseEntity.status(200).body(accountService.login_valid(account));
        }
        return ResponseEntity.status(401).build();
    }
    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account){
        if(accountService.getAccountByUsername(account.getUsername())!=null){
            return ResponseEntity.status(409).build();
        }
        else if((account.getUsername().length()>3)&&(account.getPassword().length()>3)){
            return ResponseEntity.status(200).body(accountService.insertAccount(account));

        }
        return ResponseEntity.status(409).build();
    }
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@RequestBody Message message,@PathVariable int message_id){
        if(messageService.getMessagebyID(message_id)==null){
           return ResponseEntity.status(400).build();
        }
        else if((message.getMessageText().length()!=0)&&(message.getMessageText().length()<256)){
            messageService.updateMessage(message_id, message.getMessageText());
            return ResponseEntity.status(200).body(1);
        }
        else return ResponseEntity.status(400).build(); 
    }


    


    

}
