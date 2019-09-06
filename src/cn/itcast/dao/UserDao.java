package cn.itcast.dao;

import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 登陆方法
     * @param loginUser 用户名和密码
     * @return  用户全部信息,没有查询到，返回null
     */
    public User login(User loginUser){
        try {
            //1.编写sql
            String sql = "select * from user where username = ? and password = ?";
            //2.调用query方法
            RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

            User user = template.queryForObject(sql,rowMapper,loginUser.getUsername(),loginUser.getPassword());

            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }
}
