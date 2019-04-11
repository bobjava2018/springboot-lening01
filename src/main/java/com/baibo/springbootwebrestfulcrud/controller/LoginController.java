package com.baibo.springbootwebrestfulcrud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
        private Logger logger= LoggerFactory.getLogger(LoginController.class);
        @PostMapping("/main")
        public String login (String username,
                             String password,
                             Map<String,String> mess, HttpSession session){
             logger.info("---------"+username+","+password);
             if("123456".equals(password.trim())){
                 System.out.println("登录成功了");
                 session.setAttribute("loginName",username);

                 return  "redirect:/main.html";
             }else{
                 System.out.println("登录失败");
                 mess.put("msg","登录失败");
             }
            return "login";
        }

        @RequestMapping("/index")
        public String  toIndex(){

            return "login";
        }

    @RequestMapping("/main.html")
    public String  toIndexMain(){

        return "Dashboard";
    }
}
