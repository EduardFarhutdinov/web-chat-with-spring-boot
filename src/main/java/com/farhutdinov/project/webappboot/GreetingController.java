package com.farhutdinov.project.webappboot;

import com.farhutdinov.project.webappboot.domain.Message;
import com.farhutdinov.project.webappboot.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String,Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/")
    public String homePage(Map<String,Object> model){
        Iterable<Message> allMessages = messageRepo.findAll();
        model.put("allMessages",allMessages);
        return "home";
    }

    @PostMapping("/")
    public String addMessage(@RequestParam String messageText,@RequestParam String tag,
                             Map<String,Object> model){
        if(!messageText.equals("") && !tag.equals("")) {
           Message message = new Message(messageText , tag);
            messageRepo.save(message);
        }else {
            System.out.println("Error!");
        }
        Iterable<Message> messages = messageRepo.findAll();
        model.put("allMessages",messages);

        return "home";
    }
    @PostMapping("find")
    public String findMessageByTag(@RequestParam String tag,
                                   Map<String,Object> model){
        Iterable<Message> messages;
        if(tag != null && !tag.isEmpty()) {
            messages = messageRepo.findByTag(tag);
        }else {
            messages = messageRepo.findAll();
        }
        model.put("allMessages",messages);
        return "home";
    }
}
