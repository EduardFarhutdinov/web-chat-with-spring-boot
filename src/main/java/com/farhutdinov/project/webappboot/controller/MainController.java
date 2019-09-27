package com.farhutdinov.project.webappboot.controller;

import com.farhutdinov.project.webappboot.domain.Message;
import com.farhutdinov.project.webappboot.domain.User;
import com.farhutdinov.project.webappboot.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String homePage(@RequestParam(required = false,defaultValue = "") String tag, Map<String,Object> model){
        Iterable<Message> allMessages = messageRepo.findAll();

        if(tag != null && !tag.isEmpty()) {
            allMessages = messageRepo.findByTag(tag);
        }else {
            allMessages = messageRepo.findAll();
        }

        model.put("allMessages",allMessages);
        model.put("tag",tag);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(
            @AuthenticationPrincipal User user,
                @RequestParam String messageText,
                @RequestParam String tag,
                Map<String,Object> model){
        if(!messageText.equals("") && messageText != null) {
           Message message = new Message(messageText,tag,user);
            messageRepo.save(message);
        }else {
            model.put("error","message is not empty!");
            System.out.println("Error!");
        }
        Iterable<Message> messages = messageRepo.findAll();
        model.put("allMessages",messages);

        return "main";
    }

//    @PostMapping("find")
//    public String findMessageByTag(@RequestParam String tag,
//                                   Map<String,Object> model){
//        Iterable<Message> messages;
//        if(tag != null && !tag.isEmpty()) {
//            messages = messageRepo.findByTag(tag);
//        }else {
//            messages = messageRepo.findAll();
//        }
//        model.put("allMessages",messages);
//        return "main";
//    }
}
