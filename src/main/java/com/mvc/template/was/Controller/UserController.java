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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
  
  @RequestMapping(value="/userInfo/{uid}", method=RequestMethod.GET)
  public String requestGetProfile(@PathVariable(value="uid")String uid,
                HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    
    UserDao user = userService.selectUser(uid);

    model.addAttribute("userInfo", user);
    
    return "user";
  }
  
}
