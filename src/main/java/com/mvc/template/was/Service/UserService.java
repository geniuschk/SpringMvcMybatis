package com.mvc.template.was.Service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.template.was.Dao.UserDao;

@Service
public class UserService {
  @Autowired
  SqlSession sqlSession;
  
  public UserDao selectUser(String uid) {
    return (UserDao) sqlSession.selectOne("mapper.user.selectUser", uid);
  }
  
  public UserDao setUser(UserDao user) {
    int result = sqlSession.update("mapper.user.setUser", user);
    
    return result > 0 ? user : null;
  }
}
