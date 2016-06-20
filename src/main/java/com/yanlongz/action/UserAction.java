package com.yanlongz.action;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.yanlongz.domain.User;
import com.yanlongz.service.UserService;
import com.yanlongz.dto.ResultData;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;
/**
 * Created by Allen1990 on 2016/6/20.
 */
@Controller
@RequestMapping("/users")
public class UserAction {
//    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    //AJAX JSON
    @RequestMapping(value="/{userId}",
            method = RequestMethod.POST,
            produces = {"applicaiton/json;charset=UTF-8"})
    @ResponseBody
    public ResultData<User> user(@PathVariable("userId") int userId){
        ResultData<User> result;
        User user=userService.getUserById(userId);
        try{
            result=new ResultData<User>(true,user);
        }catch(Exception e){
            result=new ResultData<User>(false,e.getMessage());
        }
        return result;//返回json数据
    }

    //ModalAndView
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String users(Model model){
        List<User> userList=userService.getUserList();
        model.addAttribute("userList",userList);
        return "users";//springmvc中配置的路径+users.后缀
    }
}
