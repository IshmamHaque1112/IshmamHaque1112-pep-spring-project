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
        return messageRepository.save(new Message(authorid,message_text,timepost));
    }
    public Message insertMessage(Message message){
        return messageRepository.save(message);
    }
    public Message updateMessage(Message message){
        return messageRepository.save(message);
    }
    public Message deleteMessagebyID(int id){
        Message message=getMessagebyID(id);
        messageRepository.deleteById(id);
        return message;
    }
}
