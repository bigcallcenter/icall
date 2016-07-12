package com.bigcallcenter.icall.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.bigcallcenter.icall.domain.MenuInfo;

@Repository
public class MenuDao extends BaseDao {

	
	public List<MenuInfo> getMenuInfoByUser(String loginName) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select c.* from t_sys_user a INNER JOIN t_sys_user_menu b ON a.id = b.user_id INNER JOIN t_sys_menu c on b.menu_id = c.id");
		sql.append(" where a.login_name = '").append(loginName).append("'");
		sql.append(" UNION ALL");
		sql.append(" select g.* from t_sys_user d INNER JOIN t_sys_user_org e ON d.id = e.user_id INNER JOIN t_sys_org_menu f on e.org_id = f.org_id LEFT JOIN t_sys_menu g on f.menu_id and g.id");
		sql.append(" where d.login_name = '").append(loginName).append("'");;
		return this.getJdbcTemplate().query(sql.toString(),new BeanPropertyRowMapper<MenuInfo>(MenuInfo.class));
	}
}
