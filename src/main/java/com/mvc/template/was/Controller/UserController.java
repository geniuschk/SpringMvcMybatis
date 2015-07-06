package com.mvc.template.was.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.template.was.Service.UserService;
import com.mvc.template.was.Dao.UserDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
  
  @Autowired
  UserService userService;
  
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);
  
  /**
   * Simply selects the home view to render by returning its name.
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    logger.info("Welcome home! The client locale is {}.", locale);
    
    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);    
    String formattedDate = dateFormat.format(date);    
    model.addAttribute("serverTime", formattedDate );
    
    return "home";
  }
  
  // ModelandView 리턴
  @RequestMapping(value="/users/{uid}", method=RequestMethod.GET)
  public String getUser(@PathVariable(value="uid")String uid,
                HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    
    UserDao user = userService.selectUser(uid);
    model.addAttribute("userInfo", user);
    
    return "user";  
  }
    
  // Json 리턴 (request header - Accept : application/json)
  @RequestMapping(produces="application/json;charset=UTF-8", value="/users/{uid}", method=RequestMethod.GET)
  public @ResponseBody String json_getUser(@PathVariable(value="uid")String uid,
                HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    
    UserDao user = userService.selectUser(uid);

    return user.toJSON(true);
  }
      
  // Xml 리턴 (request header - Accept : application/atom+xml)
  @RequestMapping(produces="application/atom+xml;charset=UTF-8", value="/users/{uid}", method=RequestMethod.GET)
  public @ResponseBody String xml_getUser(@PathVariable(value="uid")String uid,
                HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    
    UserDao user = userService.selectUser(uid);
    //Object > xml > Object 하면, Timestamp Createdate 타입 생성안됨. 확인 필요.
    //System.out.println(user.fromXml(user.toXml()));
    
    return user.toXml();
  }

  @RequestMapping(value="/users/{uid}/edit", method=RequestMethod.GET)
  public String setUserPage(@PathVariable(value="uid")String uid,
                HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    
    UserDao user = userService.selectUser(uid);
    model.addAttribute("userInfo", user);
    
    return "userEdit";
  }

  
  @RequestMapping(value="/users/{uid}", method=RequestMethod.POST)
  public String setUser(@PathVariable(value="uid")String uid,
                @ModelAttribute("user") UserDao user,
                HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

    user.setId(uid);
    System.out.println(user.toJSON(true));
    UserDao result = userService.setUser(user);
    model.addAttribute("userInfo", result);
    
    return "redirect:/users/" + uid;
  }
}
