package com.baibo.springbootwebrestfulcrud.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class HelloWord {

//    @RequestMapping({"/","/index.html"})
//    public String toIndex()
//    {
//        return "login";
//    }
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
      return "HelloWord";
    }



    @RequestMapping("/success")
    public String success(Map<String,Object> params){
        params.put("hello","你好");
        List<String> list=new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        params.put("users",list);
        return "success";
    }


}
