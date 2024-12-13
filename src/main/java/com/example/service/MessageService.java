package com.example.service;
import com.example.entity.*;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    public MessageRepository getMessageRepository(){
        return this.messageRepository;
    }
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }
    public List<Message> getMessagesByAccountId(int accountid){
        return messageRepository.findByPostedBy(accountid);
    }
    public Message getMessagebyID(int id){
        Optional<Message> messageOptional = messageRepository.findById(id);
        if(messageOptional.isPresent()){
            Message message = messageOptional.get();
            return message;
        }
        else return null;
    }
    
    public Message insertMessage(int authorid,String message_text,Long timepost){
        if(message_text.length()>255) return null;
        if(message_text.length()==0) return null;
        return messageRepository.save(new Message(authorid,message_text,timepost));
    }
    public Message insertMessage(Message message){
        if(message.getMessageText().length()>255) return null;
        if(message.getMessageText().length()==0) return null;
        return messageRepository.save(message);
    }
    public Message updateMessage(Message message){
        if(message.getMessageText().length()>255) return null;
        if(message.getMessageText().length()==0) return null;
        return messageRepository.save(message);
    }
    public Message updateMessage(int messageid,String message_text){
        if(message_text.length()>255) return null;
        if(message_text.length()==0) return null;
        Message message=getMessagebyID(messageid);
        if(message==null) return null;
        message.setMessageText(message_text);
        return messageRepository.save(message);
    }
    public Message deleteMessagebyID(int id){
        Message message=getMessagebyID(id);
        messageRepository.deleteById(id);
        return message;
    }
}
