package com.baibo.springbootwebrestfulcrud.controller;

import com.baibo.springbootwebrestfulcrud.dao.DepartmentDao;
import com.baibo.springbootwebrestfulcrud.dao.EmployeeDao;
import com.baibo.springbootwebrestfulcrud.entities.Department;
import com.baibo.springbootwebrestfulcrud.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

@Controller
public class LoginController {
        private Logger logger= LoggerFactory.getLogger(LoginController.class);

        @Autowired
        EmployeeDao employeeDao;
        @Autowired
        DepartmentDao departmentDao;
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

        @GetMapping("/emps")
        public String list(Map<String,Object> model){


            Collection<Employee> lsit=employeeDao.getAll();

            model.put("emps",lsit);
            return "emp/list";
        }
        @GetMapping("/emp")
    public String toAddPage(Map<String,Object> model){
        Collection<Department> collection=departmentDao.getDepartments();
        model.put("depts",collection);

        return "emp/add";

    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") String  id, ModelMap model){
        Integer  ID=Integer.valueOf(id);
         Employee employee=employeeDao.getEmpById(ID);
        Collection<Department> collection=departmentDao.getDepartments();
        model.put("depts",collection);
        model.put("emp",employee);
        return "emp/add";

    }
        @PostMapping("/emps")
        public String add(Employee employee){
            logger.info("名字"+employee.getLastName());
            System.out.println(employee);
            employeeDao.save(employee);
          return "redirect:/emps";
        }


    @PutMapping("/emps")
    public String edit(Employee employee){
        logger.info("名字"+employee.getLastName());
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
   @DeleteMapping("/emps/{id}")
    public String delete(@PathVariable("id") String id){
       Integer  ID=Integer.valueOf(id);

         System.out.println("要删除了+++++++++++++++++++++++++++id:"+id);
        employeeDao.deleteEmpById(ID);
        return "redirect:/emps";
    }
    @RequestMapping("/main.html")
    public String  toIndexMain(){

        return "Dashboard";
    }
}
