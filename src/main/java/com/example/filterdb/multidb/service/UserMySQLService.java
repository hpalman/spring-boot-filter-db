package com.example.filterdb.multidb.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSession;
//import com.example.mybatisdemo.model.UserMySQL;
//import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.example.filterdb.multidb.model.UserLog;
import com.example.filterdb.multidb.model.UserMySQL;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserMySQLService {

    private final SqlSession sqlSession;

    public UserMySQLService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public UserMySQL findById(Long id) {
        return sqlSession.selectOne("UserMapperMySQL.findById", id);
    }

    public List<UserMySQL> findAll() {
        return sqlSession.selectList("UserMapperMySQL.findAll");
    }

    public void save(UserMySQL user) {
    	log.info("■ save I.");
    	if ( user == null ) {
    		user = new UserMySQL();
    		user.setName("h.palman");
    		user.setEmail("hpalman@gmail.com");
    	}
        if (user.getId() == null) {
        	log.info("■ insert");
            sqlSession.insert("UserMapperMySQL.insert", user);
        } else {
        	log.info("■ update");
            sqlSession.update("UserMapperMySQL.update", user);
        }
    	log.info("■ save O.");
    }

    public void insert(UserMySQL user) {
    	log.info("■ insert I.");
        sqlSession.insert("UserMapperMySQL.insertUser", user);
    	log.info("■ insert O.");
    }
    
    public void log(String gid, String url) {
    	log.info("■ log I.");
    	//if ( user == null ) {
    	//	user = new UserMySQL();
    	//	user.setName("h.palman");
    	//	user.setEmail("hpalman@gmail.com");
    	//}
        //if (user.getId() == null) {
        //	log.info("■ insert");
        //    sqlSession.insert("UserMapperMySQL.insert", user);
        //} else {
        //	log.info("■ update");
        //    sqlSession.update("UserMapperMySQL.update", user);
        //}
    	UserLog userLog = new UserLog(gid, url);    	
        sqlSession.insert("UserMapperMySQL.logInsert", userLog);
    	
    	log.info("■ log O.");
    }
    
    public void delete(Long id) {
        sqlSession.delete("UserMapperMySQL.delete", id);
    }
}
