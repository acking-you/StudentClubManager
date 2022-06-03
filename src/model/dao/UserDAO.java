package model.dao;

import model.MybatisUtil;
import model.entity.User;
import model.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import util.MD5Util;
import view.Login;

import java.sql.SQLException;

public class UserDAO {
    static UserDAO instance;
    SqlSession sqlSession;
    UserMapper userMapper;

    private UserDAO(boolean autoCommit) {
        sqlSession = MybatisUtil.getSession(autoCommit);
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    static public UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO(false);
        }
        return instance;
    }

    public int isUserExist(User user) {
        return userMapper.isUserExist(user);
    }

    public void modifyPassword(String password) throws SQLException {
        String username = Login.name_cache;
        String s = MD5Util.stringToMD5(password);
        userMapper.modifyPassword(username, s);
        sqlSession.commit();
    }

}
