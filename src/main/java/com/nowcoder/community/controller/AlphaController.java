package com.nowcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @RequestMapping("/hello")
    @ResponseBody  //返回字符串的注解
    public String sayHello() {
        return "Hello SpringBoot";
    }

    //1
    @RequestMapping("/http")
    /*
    最底层的请求与响应
    * */
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据

        //返回相应数据

    }

    /*
    如何获取请求参数：get？post？
    @RequestMapping：与访问路径绑定，可以定义请求方法get or post
    @ResponseBody：与return绑定，显示响应的数据
    * */
    /*
     * Get请求
     * */
    //  请求url类型：/students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(@RequestParam(name = "current", required = false, defaultValue = "1") int current,
                              @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //  请求url类型：/student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }


    /*
     * Post请求
     * */
    //请求类型：post
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {//传参名与请求一致，即可获取参数
        System.out.println(name + ": " + age);
        return "success";
    }


    //
    /*
     *响应HTML数据
     * */
    //传数据MAV给view模板，响应给浏览器
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "清华大学");
        model.addAttribute("age", "112");
        return "/demo/view";
    }

    /*
     * 响应JSON数据（异步请求）
     * */
    //Java对象->JSON对象->JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        Map<String, Object> emp1 = new HashMap<>();
        emp1.put("name", "李四");
        emp1.put("age", 25);
        emp1.put("salary", 8000.00);
        list.add(emp);
        list.add(emp1);
        return list;
    }

}
