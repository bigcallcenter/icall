package com.bigcallcenter.icall.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.bigcallcenter.icall.domain.User;
@Repository
public class UserDao extends BaseDao {

	private Logger logger = Logger.getLogger(UserDao.class);
	/**
	 * 根据登录名查找用户，登录名可以是手机号、微信平台用户标识
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName)
	{
		User user = null;
        try {
            String sql = "SELECT * FROM t_sys_User WHERE login_name= ? ";
            user = this.getJdbcTemplate().queryForObject(sql, new Object[]{loginName}, 
                    new BeanPropertyRowMapper<User>(User.class));
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("根据根据登录名查找用户Dao异常",e);
        }
        
        return user;
	}
	
	public List<User> getAllusers() throws Exception{
		String sql = "select * from t_sys_user";
		return this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<User>(User.class));
	}
}
